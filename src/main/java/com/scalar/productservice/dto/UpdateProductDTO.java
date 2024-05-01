package com.scalar.productservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
    private String title;
    private Long price;
    private String description;
    private String image;
    private String category;
}
