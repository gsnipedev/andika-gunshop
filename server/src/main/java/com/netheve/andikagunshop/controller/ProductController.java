package com.netheve.andikagunshop.controller;

import com.netheve.andikagunshop.dto.product.CreateProductDto;
import com.netheve.andikagunshop.dto.product.UpdateProductDto;
import com.netheve.andikagunshop.entity.ProductEntity;
import com.netheve.andikagunshop.entity.enums.ProductType;
import com.netheve.andikagunshop.service.ProductService;
import com.netheve.andikagunshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

    @GetMapping
    private ResponseEntity<List<ProductEntity>> index(Pageable pageable) {
        return ResponseEntity.ok(productService.index(pageable));
    }

    @PostMapping
    private ResponseEntity<?> create(
            @RequestParam MultipartFile productImage,
            @RequestParam String name,
            @RequestParam ProductType type,
            @RequestParam String description) {

        String fileName = UUID.randomUUID() + productImage.getOriginalFilename();

        storageService.store(productImage, fileName);

        CreateProductDto dto = CreateProductDto.builder()
                .name(name)
                .description(description)
                .type(type)
                .imageName(fileName)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.store(dto));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(productService.show(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(
            @RequestParam MultipartFile productImage,
            @RequestParam String name,
            @RequestParam ProductType type,
            @RequestParam String description,
            @PathVariable Long id) {

        String fileName = UUID.randomUUID() + productImage.getOriginalFilename();

        storageService.store(productImage, fileName);

        UpdateProductDto dto = UpdateProductDto.builder()
                .name(name)
                .description(description)
                .type(type)
                .imageName(fileName)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
