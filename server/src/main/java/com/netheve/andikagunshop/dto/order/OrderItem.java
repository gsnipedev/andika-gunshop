package com.netheve.andikagunshop.dto.order;

import lombok.Data;

@Data
public class OrderItem {
    private Long itemId;
    private int quantity;
}
