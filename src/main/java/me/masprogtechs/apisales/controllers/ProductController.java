package me.masprogtechs.apisales.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import me.masprogtechs.apisales.dto.ProductDto;
import me.masprogtechs.apisales.exception.ResourceNotFoundException;
import me.masprogtechs.apisales.services.ProductService;
import me.masprogtechs.apisales.util.MensagConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Produto", description = "Endpoints para gerenciar produtos")
public class ProductController {

    @Autowired
    private ProductService productService;
    /*@GetMapping
    @Operation(summary = "Listar todos os produtos com paginação", description = "Listar todos os produtos com paginação",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public Page<ProductDto> findAllProductPagination(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
                                                     @RequestParam(value = "size", defaultValue = "5") Integer tamanhoPagina){
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);
        return productService.findAllProduct(pageRequest);

    }
  */
    @GetMapping
    @Operation(summary = "Listar todos os produtos com paginação", description = "Listar todos os produtos com paginação",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable){
        Page<ProductDto> list = productService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("name/{name}")
    @Operation(summary = "Buscar qualquer produto pelo nome", description = "Buscar qualquer produto pelo nome",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductDto> findByName(@PathVariable String name) throws
            ResourceNotFoundException {
        ProductDto productDto = productService.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(MensagConstant.PRODUTO_NAO_ENCONTRADO + name));
        return ResponseEntity.ok(productDto);
    }
    @PostMapping
    @Operation(summary = "Salvar um produto", description = "Salvar um produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto){
        return productService.save(productDto);
    }

    @GetMapping("all")
    @Operation(summary = "Listar todos produtos activos", description = "Listar todos produtos activos",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public List<ProductDto> findAllActive(){
        return productService.findAllActive();
    }

    @GetMapping("active/{id}")
    @Operation(summary = "Buscar produto activo pelo ID", description = "Buscar produto activo pelo ID",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductDto> findByIdAndActive(@PathVariable Integer id) throws ResourceNotFoundException {
        ProductDto productDto = productService.findIdAndActive(id)
                .orElseThrow(() -> new ResourceNotFoundException((MensagConstant.PRODUTO_NAO_ENCONTRADO + id)));
        return ResponseEntity.ok().body(productDto);
    }

    @GetMapping("name/active/{name}")
    @Operation(summary = "Buscar produto activo pelo nome", description = "Buscar produto activo pelo nome",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductDto> findByNameAndActive(@PathVariable String name) throws ResourceNotFoundException {
        ProductDto productDto = productService.findByNameAndActive(name)
                .orElseThrow(() -> new ResourceNotFoundException((MensagConstant.PRODUTO_NAO_ENCONTRADO + name)));
        return ResponseEntity.ok().body(productDto);
    }


    @PutMapping("{id}")
    @Operation(summary = "Actualizar produto", description = "Actualizar produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id") Long id,
                                                    @Valid @RequestBody ProductDto productDto) throws ResourceNotFoundException {
        productService.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        MensagConstant.PRODUTO_NAO_ENCONTRADO + productDto.getId()
                ));
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PutMapping("deactivate/{id}")
    @Operation(summary = "Desactivar um produto", description = "Desactivar um produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    @ResponseStatus(HttpStatus.CREATED)
    public void deactivate(@Valid @RequestBody ProductDto productDto){
        productService.deactivate(productDto.getId());
    }

    @GetMapping("count")
    @Operation(summary = "Retornar a quantidade de produto", description = "Retornar a quantidade de produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public long countProducts(){
        return productService.countProducts();
    }

    @GetMapping("count/active")
    @Operation(summary = "Retornar a quantidade de produto activos", description = "Retornar a quantidade de produto d activos",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public long countProductActive(){
        return productService.countProductActive();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um produto pelo ID", description = "Deletar um produto pelo ID",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public Map<String, Boolean> deleteById(@PathVariable Long id){
        ProductDto productDto = productService.findIdAndActive(id)
                .orElseThrow(() -> new ResourceNotFoundException(MensagConstant.PRODUTO_NAO_ENCONTRADO + id));
        productService.delete(productDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



/*
    @PutMapping("{id}")
    @Operation(summary = "Actualizar produto", description = "Actualizar produto",
            tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<ProductDto> update(@PathVariable @Valid Long id, @RequestBody ProductDto productDto ){
        productDto = productService.update(id, productDto);
        return ResponseEntity.ok().body(productDto);
    }
    */

}
