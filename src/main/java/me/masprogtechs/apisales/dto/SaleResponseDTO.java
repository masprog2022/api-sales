package me.masprogtechs.apisales.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.masprogtechs.apisales.domain.entities.SaleItem;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongConsumer;

@Data
public class SaleResponseDTO {
    private Long id;
    private List<SaleItemResponseDTO> items;
    private BigDecimal totalAmount;
    private BigDecimal amountPaid;
    private BigDecimal difference;
    private PaymentEnum payment;



}
