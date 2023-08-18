package me.masprogtechs.apisales.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.masprogtechs.apisales.domain.enums.PaymentEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Valor total obrigatório")
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @NotNull(message = "Valor Pago obrigatório")
    private BigDecimal amountPaid;

    @Column(nullable = false)
    @NotNull(message = "Troco obrigatório")
    private BigDecimal difference;

    @NotNull(message = "Forma de pagamento obrigatório")
    private PaymentEnum payment;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private List<SaleItem> items = new ArrayList<>();

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
    @PreUpdate
    protected void preUpdate(){
        this.updateAt = LocalDateTime.now();
    }
}
