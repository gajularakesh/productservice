package com.scalar.productservice.services;

import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import com.scalar.productservice.repository.CatagoryRepository;
import com.scalar.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//in service we can mention name of service so that while bean creation of ProductService we know which class we
// want to implement
@Service("dataBaseProductService")
public class DataBaseProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatagoryRepository catagoryRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product =  productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product postProduct(String title, String description, double price, String image ,String catagoryTitle) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        Catagory finalCatagory = catagoryRepository.findByTitle(catagoryTitle);
        if(finalCatagory == null) {
            Catagory catagory = new Catagory();
            catagory.setTitle(catagoryTitle);
            finalCatagory = catagory;
//            catagoryRepository.save(finalCatagory);
        }
        product.setCatagory(finalCatagory);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProduct(Long id) {
        Product p = productRepository.findById(id).get();
        productRepository.deleteById(id);
        return p;
    }

    @Override
    public Product updateProduct(Long id, String title, String description, double price, String image,String catagoryTitle) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        Catagory catagory = new Catagory();
        catagory.setTitle(catagoryTitle);
        product.setCatagory(catagory);
        return productRepository.save(product);
    }

    @Override
    public List<Catagory> getAllCatagories() {
        return catagoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCatagory(String catagoryTitle) {
        Catagory catagory = catagoryRepository.findByTitle(catagoryTitle);
        if(catagory == null) {
            return null;
        }
        return catagory.getProducts();
    }
}
