package me.masprogtechs.apisales.domain.repositories;

import jakarta.transaction.Transactional;
import me.masprogtechs.apisales.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByName(String name);
   List<Product> findByActive(boolean active);
    Optional<Product> findByNameAndActive(String name, Boolean active);
    Optional<Product> findByIdAndActive(Long id, Boolean active);
    //Page<Product> findAllByActive(Pageable pageable, boolean active);

    long count();
    @Query("SELECT COUNT(*) FROM Product WHERE active = true")
    long countProductActive();

     @Modifying(clearAutomatically = true)
     @Transactional
     @Query("UPDATE Product r SET r.active = false WHERE r.id = :productId")
     void deactivate(@Param("productId") Long productId);



}
