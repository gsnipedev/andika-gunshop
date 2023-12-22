package com.netheve.andikagunshop.service.impl;

import com.netheve.andikagunshop.dto.product.CreateProductDto;
import com.netheve.andikagunshop.dto.product.UpdateProductDto;
import com.netheve.andikagunshop.entity.ProductEntity;
import com.netheve.andikagunshop.repository.ProductRepository;
import com.netheve.andikagunshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> index(Pageable pageable) {
        return productRepository.findAllBy(pageable);
    }

    @Override
    public ProductEntity store(CreateProductDto dto) {
        ProductEntity product = ProductEntity.builder()
                .name(dto.getName())
                .type(dto.getType())
                .price(dto.getPrice())
                .imageUrl(dto.getImageName())
                .serialNumber("NO-SERIAL-NUMBER")
                .description(dto.getDescription())
                .build();

        return productRepository.save(product);
    }

    @Override
    public ProductEntity show(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );
    }

    @Override
    public ProductEntity update(Long id, UpdateProductDto dto) {
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageName());
        product.setType(dto.getType());
        product.setPrice(dto.getPrice());

        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
