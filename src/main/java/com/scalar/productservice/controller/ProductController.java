package com.scalar.productservice.controller;

import com.scalar.productservice.dto.CreateProductDto;
import com.scalar.productservice.dto.ExectionDto;
import com.scalar.productservice.dto.UpdateProductDTO;
import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import com.scalar.productservice.services.FakeStoreProductService;
import com.scalar.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
public class ProductController {

    ProductService productService;

   ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody CreateProductDto createProductDto){
       return productService.postProduct(
               createProductDto.getTitle(),
               createProductDto.getDescription(),
               createProductDto.getPrice(),
               createProductDto.getImage()
       );

    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
       return productService.getAllProducts();

    }

    @DeleteMapping("/deleteProduct/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @PutMapping("/putProduct/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductDTO updateProductDto){
        return productService.postProduct(
                updateProductDto.getTitle(),
                updateProductDto.getDescription(),
                updateProductDto.getPrice(),
                updateProductDto.getImage()
        );

    }

//    @ExceptionHandler
//    public ResponseEntity<ExectionDto> handleException(Exception ex){
//
//       return new  ResponseEntity<ExectionDto>(new ExectionDto("Server Goes Down please try After some time"), HttpStatusCode.valueOf(405));
//
//    }


    @GetMapping("/catagories")
    public List<Catagory> getAllCatagories() {
        return productService.getAllCatagories();

    }
    @GetMapping("/catagories/{catagory}")
    public List<Product> getCatagoriesByCatagory(@PathVariable("catagory") String catagory) {
        return productService.getCatagoriesByCatagory(catagory);

    }



}
