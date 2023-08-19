package me.masprogtechs.apisales.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.masprogtechs.apisales.domain.entities.SaleItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SaleItemResponseDTO {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
