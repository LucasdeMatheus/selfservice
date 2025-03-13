package com.project.selfservice.domain.product;

import com.project.selfservice.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<ProductDTO> findByCategory(Category category, Pageable pageable);


    // Query para filtrar produtos pelo nome e pela categoria
    @Query("SELECT p FROM Product p WHERE " +
            "(LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) AND " +
            "(p.category = :category OR :category IS NULL)")
    Page<ProductDTO> searchByNameAndCategory(@Param("name") String name,
                                          @Param("category") Category category,
                                          Pageable pageable);

    Page<ProductDTO> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
