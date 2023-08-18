package me.masprogtechs.apisales.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleItemResponseDTO {
    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;
}
