package com.scalar.productservice.services;

import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product getProductById(Long id);
    public Product postProduct(String title, String description, Long price,String image);
    public List<Product> getAllProducts();
    public Product deleteProduct(Long id);
    public Product updateProduct(Long id, String title, String description, Long price,String image);

    public List<Catagory> getAllCatagories();

    List<Product> getCatagoriesByCatagory(String catagory);
}
