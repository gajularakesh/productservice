package com.scalar.productservice.services;

import com.scalar.productservice.dto.CatagoryDto;
import com.scalar.productservice.dto.CreateProductDto;
import com.scalar.productservice.dto.FakeStoreProductDto;
import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate ;

    private RedisTemplate<String,Object> redisTemplate ;

    FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String,Object> redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id){

    Product productFromRedis = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
    if(productFromRedis != null){
        return productFromRedis;
    }


        FakeStoreProductDto fakeStoreProductDto = restTemplate.
                                                            getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        redisTemplate.opsForValue().set(String.valueOf(String.valueOf(fakeStoreProductDto.getId())), fakeStoreProductDto.toProduct());
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product postProduct(String title, String description, double price,String image,String catagory ) {

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

    @Override
    public Product updateProduct(Long id, String title, String description, double price, String image,String catagory) {
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle(title);
        createProductDto.setImage(image);
        createProductDto.setDescription(description);
        createProductDto.setPrice(price);
        createProductDto.setCategory(catagory);
        FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",createProductDto,FakeStoreProductDto.class,id);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Catagory> getAllCatagories() {
        List<Catagory> catagories = new ArrayList<>();
        CatagoryDto[] response = restTemplate.
                getForObject("https://fakestoreapi.com/products/categories" , CatagoryDto[].class);
        for(CatagoryDto catagoryDto : response){
            catagories.add(catagoryDto.toCatagory());
        }
        return catagories;
    }

    @Override
    public List<Product> getProductsByCatagory(String catagory) {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] response = restTemplate.
                getForObject("https://fakestoreapi.com/products/category/{specific}" , FakeStoreProductDto[].class,catagory);
        for(FakeStoreProductDto fakeStoreProductDto : response){
            products.add(fakeStoreProductDto.toProduct());
            fakeStoreProductDto.getCatagory().setTitle(catagory);
        }
        return products;
    }
}
