package me.masprogtechs.apisales.dto;

import me.masprogtechs.apisales.domain.entities.Sale;
import me.masprogtechs.apisales.domain.entities.SaleItem;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SaleDTO {

    private Long id;
    private List<SaleItemResponseDTO> items;
    private BigDecimal totalAmount;
    private BigDecimal amountPaid;
    private BigDecimal difference;
    private PaymentEnum payment;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public SaleDTO(){}

    public SaleDTO(Long id, List<SaleItemResponseDTO> items, BigDecimal totalAmount,
                   BigDecimal amountPaid, BigDecimal difference, PaymentEnum payment,
                   LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.items = items;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.difference = difference;
        this.payment = payment;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public SaleDTO(Sale entity) {
        this.id = entity.getId();
        this.items = mapSaleItemsToDTOs(entity.getItems());
        this.totalAmount = entity.getTotalAmount();
        this.amountPaid = entity.getAmountPaid();
        this.difference = entity.getDifference();
        this.payment = entity.getPayment();
        this.createdAt = entity.getCreatedAt();
        this.updateAt = entity.getUpdateAt();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SaleItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<SaleItemResponseDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public PaymentEnum getPayment() {
        return payment;
    }

    public void setPayment(PaymentEnum payment) {
        this.payment = payment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    private List<SaleItemResponseDTO> mapSaleItemsToDTOs(List<SaleItem> saleItems) {
        return saleItems.stream()
                .map(this::mapSaleItemToDTO)
                .collect(Collectors.toList());
    }

    private SaleItemResponseDTO mapSaleItemToDTO(SaleItem saleItem) {
        SaleItemResponseDTO dto = new SaleItemResponseDTO();
        dto.setProductId(saleItem.getProduct().getId());
        dto.setProductName(saleItem.getProduct().getName());
        dto.setPrice(saleItem.getProduct().getPrice());
        dto.setQuantity(saleItem.getQuantity());
        dto.setSubtotal(saleItem.getSubtotal());
        dto.setCreatedAt(saleItem.getProduct().getCreatedAt());
        dto.setUpdatedAt(saleItem.getProduct().getUpdateAt());
        return dto;
    }
}
