package me.masprogtechs.apisales.controllers;

import me.masprogtechs.apisales.dto.ProductDto;
import me.masprogtechs.apisales.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> findAllProductPagination(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
                                                     @RequestParam(value = "size", defaultValue = "5") Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return productService.findAllProduct(pageRequest);

    }
}
