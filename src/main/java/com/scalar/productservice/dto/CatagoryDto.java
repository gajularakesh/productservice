package com.scalar.productservice.dto;

import com.scalar.productservice.models.Catagory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatagoryDto {
    private String title;

    public Catagory toCatagory(){
        Catagory catagory = new Catagory();
        catagory.setTitle(title);
        return catagory;
    }
}

