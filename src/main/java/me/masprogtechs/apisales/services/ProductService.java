package me.masprogtechs.apisales.services;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import me.masprogtechs.apisales.domain.entities.Product;
import me.masprogtechs.apisales.domain.repositories.ProductRepository;
import me.masprogtechs.apisales.dto.ProductDto;
import me.masprogtechs.apisales.exception.ResourceNotFoundException;
import me.masprogtechs.apisales.util.MensagConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
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

    public Optional<ProductDto> findIdAndActive(long id){
       return productRepository.findByIdAndActive(id, true)
               .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Optional<ProductDto> findByNameAndActive(String name){
       return productRepository.findByNameAndActive(name, true)
               .map(product -> modelMapper.map(product, ProductDto.class));
    }

   public Optional<ProductDto> findById(long id){
       return productRepository.findById(id)
               .map(product -> modelMapper.map(product, ProductDto.class));
   }

   public void deactivate(@Valid Long id){
       productRepository.deactivate(id);
   }

   public long countProducts(){
       return productRepository.count();
   }

   public long countProductActive(){
       return productRepository.countProductActive();
   }


   @Transactional
   public ProductDto update(long id, ProductDto productDto){
       try {
             Product entity = productRepository.getOne(id);
             entity.setName(productDto.getName());
             entity.setPrice(productDto.getPrice());
             return new ProductDto(entity);
       }catch (EntityNotFoundException e){
           throw new ResourceNotFoundException(MensagConstant.PRODUTO_NAO_ENCONTRADO + id);
       }
   }



}
