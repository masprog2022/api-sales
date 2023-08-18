package me.masprogtechs.apisales.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;
import org.aspectj.bridge.IMessage;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_sale")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Valor total obrigatório")
    private BigDecimal amount;

    @Column(nullable = false)
    @NotNull(message = "Valor Pago obrigatório")
    private BigDecimal amountPaid;

    @Column(nullable = false)
    @NotNull(message = "Troco obrigatório")
    private BigDecimal difference;

    @NotNull(message = "Forma de pagamento obrigatório")
    private PaymentEnum payment;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ProductSold> productSold;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updateAt;

    @PrePersist
    protected void prePersist(){
        if(this.createdAt == null)
            createdAt = LocalDateTime.now();
        if(this.updateAt == null)
            updateAt = LocalDateTime.now();
    }

    public Sale() {
    }

    public Sale(Long id, BigDecimal amount, BigDecimal amountPaid, BigDecimal difference,
                PaymentEnum payment, List<ProductSold> productSold,
                LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.amount = amount;
        this.amountPaid = amountPaid;
        this.difference = difference;
        this.payment = payment;
        this.productSold = productSold;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public List<ProductSold> getProductSold() {
        return productSold;
    }

    public void setProductSold(List<ProductSold> productSold) {
        this.productSold = productSold;
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

    @PreUpdate
    protected void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }

    //O resultado final é que esse método itera
    // sobre a lista de produtos vendidos (productSoldList)
    // e acumula o preço de cada produto no valor total pago
    // (amountPaid).
    public void calcularValorTotal(){
        for (ProductSold productSold : this.productSold){
            this.amountPaid = this.amountPaid.add(productSold.getPrice());
        }
    }



}
