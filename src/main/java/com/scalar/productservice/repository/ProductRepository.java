package com.scalar.productservice.repository;

import com.scalar.productservice.models.Product;
import com.scalar.productservice.projections.ProductwithTitleAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    Product findById(long id);

    List<Product> findAll();

    Product deleteById(long id);

    @Query("select p from Product p where p.catagory.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") long id);

    @Query(value = "select * from Product  where title = :title and id = :id" , nativeQuery = true)
    Product getProductWithSomeSpecificTitleAndId(@Param("title") String title, @Param("id") long id);

    @Query("select p.id ,p.title from Product p where p.catagory.title = :title and p.id = :id")
    ProductwithTitleAndId getProductTitleAndId(@Param("title") String title, @Param("id") long id);

    Page<Product> findByTitleContaining(String title, Pageable pageable);


}
