package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

@Query(
	   "SELECT p FROM Product p " +
       "WHERE (:type IS NULL OR p.type LIKE %:type%) " + //ο χρήστης μπορεί να βρεί το προϊόν ακόμη και αν δεν το γράψει ακριβώς όπως πρέπει(π.χ.Lap αντί για Laptop)
	   "AND (:brand IS NULL OR p.brand LIKE %:brand%) " +
	   "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
       "AND (:maxPrice IS NULL OR p.price <= :maxPrice)")
        List<Product> search(@Param("type") String type, @Param("brand") String brand, @Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);
}