package com.netheve.andikagunshop.dto.product;

import com.netheve.andikagunshop.entity.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductDto {
    private String name;
    private int price;
    private String description;
    private ProductType type;
    private String imageName;
}
