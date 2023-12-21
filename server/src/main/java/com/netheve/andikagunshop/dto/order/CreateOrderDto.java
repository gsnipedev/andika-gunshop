package com.netheve.andikagunshop.dto.order;

import lombok.Data;

import java.util.Set;

@Data
public class CreateOrderDto {
    private Set<OrderItem> items;
}
