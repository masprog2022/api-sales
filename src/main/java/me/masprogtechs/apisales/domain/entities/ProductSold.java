package me.masprogtechs.apisales.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import me.masprogtechs.apisales.domain.repositories.ProductRepository;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_product_sold")
public class ProductSold implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    @Column(nullable = false)
    @NotNull(message = "Preço obrigatório")
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull(message = "Preço total obrigatório")
    private BigDecimal priceTotal;

    @Column(nullable = false)
    @NotNull(message = "quantidade obrigatório")
    private int quantity;

    public ProductSold() {}

    public ProductSold(Long id, Product product, BigDecimal price, BigDecimal priceTotal, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.priceTotal = priceTotal;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void calcularPrecoTotal(){
        this.price = this.price.multiply(new BigDecimal(this.quantity));
    }


}
