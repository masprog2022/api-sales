package me.masprogtechs.apisales.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    @NotNull(message = "quantidade obrigatório")
    private int quantity;

    @Column(nullable = false)
    @NotNull(message = "Subtotal obrigatório")
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;  // Adicione esta associação bidirecional


}
