package me.masprogtechs.apisales.domain.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres!")
    @NotNull(message = "O Nome do Producto é obrigatório")
    @NotEmpty(message = "Nome do Producto Não deve ser vazio")
    private String name;


    @Column(nullable = false)
    @NotNull(message = "O Preço é obrigatório")
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean active;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/mm/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonFormat(pattern = "dd/mm/yyyy HH:mm:ss")
    private LocalDateTime updateAt;

    public Product(){}

    public Product(Long id, String name, BigDecimal price, Boolean active,
                   LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.active = active;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(active, product.active) && Objects.equals(createdAt, product.createdAt) && Objects.equals(updateAt, product.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, active, createdAt, updateAt);
    }

    @PrePersist
    protected void prePersist(){
        if(Objects.isNull(active))
            active = true;
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
