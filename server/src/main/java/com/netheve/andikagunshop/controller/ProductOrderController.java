package com.netheve.andikagunshop.controller;

import com.netheve.andikagunshop.dto.order.CreateOrderDto;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.entity.InvoiceEntity;
import com.netheve.andikagunshop.service.AccountService;
import com.netheve.andikagunshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class ProductOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    private ResponseEntity<InvoiceEntity> placeOrder(
            @RequestBody CreateOrderDto dto,
            @RequestHeader("Authorization") String bearerToken
    ) {
        AccountEntity account = accountService.getByToken(bearerToken.replace("Bearer ", ""));
        return ResponseEntity.ok(orderService.create(account, dto));
    }

    @PatchMapping("/approve/{id}")
    private ResponseEntity<InvoiceEntity> approveOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.approve(id));
    }

    @DeleteMapping("/cancel/{id}")
    private ResponseEntity<InvoiceEntity> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancel(id));
    }
}
