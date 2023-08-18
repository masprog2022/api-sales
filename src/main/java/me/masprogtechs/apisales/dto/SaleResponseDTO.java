package me.masprogtechs.apisales.dto;

import lombok.Data;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleResponseDTO {
    private Long id;
    private List<SaleItemResponseDTO> items;
    private BigDecimal totalAmount;
    private BigDecimal amountPaid;
    private BigDecimal difference;
    private PaymentEnum payment;
}
