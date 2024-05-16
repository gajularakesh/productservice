package com.scalar.productservice;

import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import com.scalar.productservice.projections.ProductwithTitleAndId;
import com.scalar.productservice.repository.CatagoryRepository;
import com.scalar.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductServiceApplicationTests {

//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CatagoryRepository CatagoryRepository;
//    @Autowired
//    private CatagoryRepository catagoryRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void getProductWithASpecificTitleAndId(){
//
//        Product p = productRepository.getProductWithASpecificTitleAndId("HouseHold",157);
//        System.out.println(p.getTitle());
//    }
//
//    @Test
//    void getProductIdAndTitle(){
//        ProductwithTitleAndId p = productRepository.getProductTitleAndId("HouseHold",157);
//        System.out.println(p.getTitle());
//        System.out.println(p.getId());
//    }
//
////    @Test
////    void getProductByCategoryTitle(){
////        Catagory catagory = catagoryRepository.findByTitle("HouseHold");
////        System.out.println(catagory.getTitle());
////        //System.out.println(catagory.getProducts());
////    }

}
