package com.netheve.andikagunshop.service;

import com.netheve.andikagunshop.dto.product.CreateProductDto;
import com.netheve.andikagunshop.dto.product.UpdateProductDto;
import com.netheve.andikagunshop.entity.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductEntity> index(Pageable pageable);

    ProductEntity store(CreateProductDto dto);

    ProductEntity show(Long id);

    ProductEntity update(Long id, UpdateProductDto dto);

    void delete(Long id);
}
