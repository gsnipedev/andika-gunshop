package com.netheve.andikagunshop.controller;

import com.netheve.andikagunshop.entity.InvoiceEntity;
import com.netheve.andikagunshop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    private ResponseEntity<List<InvoiceEntity>> index(Pageable pageable){
        return ResponseEntity.ok(invoiceService.index(pageable));
    }

    @GetMapping("/{id}")
    private ResponseEntity<InvoiceEntity> show(@PathVariable Long id){
        return ResponseEntity.ok(invoiceService.show(id));
    }
}
