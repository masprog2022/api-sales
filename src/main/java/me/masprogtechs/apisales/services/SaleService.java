package me.masprogtechs.apisales.services;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import jakarta.transaction.Transactional;
import me.masprogtechs.apisales.domain.entities.Product;
import me.masprogtechs.apisales.domain.entities.Sale;
import me.masprogtechs.apisales.domain.entities.SaleItem;
import me.masprogtechs.apisales.domain.repositories.ProductRepository;
import me.masprogtechs.apisales.domain.repositories.SaleRepository;
import me.masprogtechs.apisales.dto.*;
import me.masprogtechs.apisales.exception.ResourceNotFoundException;
import me.masprogtechs.apisales.util.MensagConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;



    public List<SaleResponseDTO> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(sale -> modelMapper.map(sale, SaleResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Page<SaleResponseDTO> getAllSales(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Sale> sales = saleRepository.findAll(pageable);

        return sales.map(sale -> modelMapper.map(sale, SaleResponseDTO.class));
    }

    @Transactional
    public Page<SaleDTO> findAllPaged(Pageable pageable){
        Page<Sale> list = saleRepository.findAll(pageable);
        Page<SaleDTO> salepageable = list.map(SaleDTO::new);
        return salepageable;
    }


    public SaleResponseDTO createSale(SaleRequestDTO saleRequest) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal difference;
        List<SaleItem> items = new ArrayList<>();

        Sale sale = new Sale();
        sale.setPayment(saleRequest.getPayment());  // Define a forma de pagamento

        for (SaleItemDTO itemRequest : saleRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(MensagConstant.PRODUTO_NAO_ENCONTRADO));

            int quantity = itemRequest.getQuantity();
            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(subtotal);

            SaleItem item = new SaleItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setSubtotal(subtotal);
            item.setSale(sale);  // Associa o item à venda

            items.add(item);
        }

        difference = saleRequest.getAmountPaid().subtract(totalAmount);

        sale.setItems(items);
        sale.setTotalAmount(totalAmount);
        sale.setAmountPaid(saleRequest.getAmountPaid());
        sale.setDifference(difference);

        Sale savedSale = saleRepository.save(sale);

        return mapToSaleResponseDTO(savedSale);
    }
    private SaleResponseDTO mapToSaleResponseDTO(Sale sale){
        SaleResponseDTO responseDTO = new SaleResponseDTO();
        responseDTO.setId(sale.getId());
        responseDTO.setTotalAmount(sale.getTotalAmount());
        responseDTO.setAmountPaid(sale.getAmountPaid());
        responseDTO.setDifference(sale.getDifference());
        responseDTO.setPayment(sale.getPayment());

        List<SaleItemResponseDTO> itemResponseDTOs = new ArrayList<>();
        for (SaleItem item : sale.getItems()) {
            SaleItemResponseDTO itemResponseDTO = new SaleItemResponseDTO();
            itemResponseDTO.setProductId(item.getProduct().getId());
            itemResponseDTO.setProductName(item.getProduct().getName());
            itemResponseDTO.setPrice(item.getProduct().getPrice());
            itemResponseDTO.setQuantity(item.getQuantity());
            itemResponseDTO.setSubtotal(item.getSubtotal());
            itemResponseDTOs.add(itemResponseDTO);
        }
        responseDTO.setItems(itemResponseDTOs);

        return responseDTO;
    }


}
