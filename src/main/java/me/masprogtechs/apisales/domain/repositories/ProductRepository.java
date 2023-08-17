package me.masprogtechs.apisales.domain.repositories;

import me.masprogtechs.apisales.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
   List<Product> findByActive(boolean active);
    Optional<Product> findByNameAndActive(String name, Boolean active);
    Optional<Product> findByIdAndActive(Long id, Boolean active);
    Page<Product> findAllByActive(Pageable pageable, boolean active);





}
