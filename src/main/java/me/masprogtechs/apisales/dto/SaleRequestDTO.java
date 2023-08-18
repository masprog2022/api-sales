package me.masprogtechs.apisales.dto;

import lombok.Data;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SaleRequestDTO {

    private List<SaleItemDTO> items;
    private BigDecimal amountPaid;
    private PaymentEnum payment;

}
