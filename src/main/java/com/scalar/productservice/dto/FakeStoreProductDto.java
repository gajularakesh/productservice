package com.scalar.productservice.dto;

import com.scalar.productservice.models.Catagory;
import com.scalar.productservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductDto {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private String image;

    private Catagory catagory;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        Catagory catagory = new Catagory();
        product.setCatagory(catagory);
        product.setDescription(description);
        product.setTitle(title);
        product.setPrice(price);
        product.setImageUrl(image);

        return product;
    }


}
