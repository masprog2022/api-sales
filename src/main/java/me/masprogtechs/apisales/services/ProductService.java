package me.masprogtechs.apisales.services;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import jakarta.transaction.Transactional;
import me.masprogtechs.apisales.domain.entities.Product;
import me.masprogtechs.apisales.domain.repositories.ProductRepository;
import me.masprogtechs.apisales.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ProductRepository productRepository;


   public Page<ProductDto> findAllProduct(Pageable pageable){
     return productRepository.findAll(pageable)
             .map((product -> DozerBeanMapperBuilder.buildDefault()
                     .map(product, ProductDto.class))); // converte pag<Product> para pga<pageproductdto>
   }
    @Transactional
   public Page<ProductDto> findAllPaged(Pageable pageable){
       Page<Product> list = productRepository.findAll(pageable);
        Page<ProductDto> productDtoPage = list.map(ProductDto::new);
       return productDtoPage;
   }

   public List<ProductDto> findAllActive(){
       return productRepository.findByActive(true).stream()
               .map(product -> modelMapper.map(product, ProductDto.class))
               .collect(Collectors.toList());
   }

   public ProductDto save(ProductDto productDto){
       Product product = productRepository.save(modelMapper.map(productDto, Product.class));
       return modelMapper.map(product, ProductDto.class);
   }

    public Optional<ProductDto> findByName(String name){
        return productRepository.findByName(name)
                .map(product -> modelMapper.map(product, ProductDto.class));
    }



}
