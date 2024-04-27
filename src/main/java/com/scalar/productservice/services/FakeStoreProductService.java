package com.scalar.productservice.services;

import com.scalar.productservice.dto.CreateProductDto;
import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate ;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id){



        FakeStoreProductDto fakeStoreProductDto = restTemplate.
                                                            getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);


        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product postProduct(String title, String description, Long price,String image) {

        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle(title);
        createProductDto.setImage(image);
        createProductDto.setDescription(description);
        createProductDto.setPrice(price);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.
               postForEntity("https://fakestoreapi.com/products" ,createProductDto ,FakeStoreProductDto.class);
//   without response entity     FakeStoreProductDto fakeStoreProductDto = restTemplate.
//                postForObject("https://fakestoreapi.com/products/" ,createProductDto ,FakeStoreProductDto.class);

       return responseEntity.getBody().toProduct();


    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] response = restTemplate.
                getForObject("https://fakestoreapi.com/products" , FakeStoreProductDto[].class);
        for(FakeStoreProductDto fakeStoreProductDto : response){
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public Product deleteProduct(Long id) {
        ResponseEntity<FakeStoreProductDto> result =
                restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                        HttpMethod.DELETE,null, FakeStoreProductDto.class, id);
        return result.getBody().toProduct();
    }


    //}
}
