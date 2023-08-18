package me.masprogtechs.apisales.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.masprogtechs.apisales.domain.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDto(Product entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
        this.active = entity.getActive();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdateAt();
    }

}
