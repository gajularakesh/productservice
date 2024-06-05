package com.scalar.productservice.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Catagory extends BaseModel implements Serializable {

    private String title;

    @OneToMany(mappedBy = "catagory",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> products;


}
