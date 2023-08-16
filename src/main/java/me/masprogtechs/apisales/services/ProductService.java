package me.masprogtechs.apisales.services;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import me.masprogtechs.apisales.domain.repositories.ProductRepository;
import me.masprogtechs.apisales.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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




}
