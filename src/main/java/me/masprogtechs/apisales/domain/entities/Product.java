package me.masprogtechs.apisales.domain.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updateAt;

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
