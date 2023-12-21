package com.netheve.andikagunshop.dto.product;

import com.netheve.andikagunshop.entity.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductDto {
    private String name;
    private String description;
    private ProductType type;
    private String imageName;
}
