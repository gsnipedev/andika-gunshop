package com.netheve.andikagunshop.service;

import com.netheve.andikagunshop.dto.order.CreateOrderDto;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.entity.InvoiceEntity;

public interface OrderService {
    InvoiceEntity create(AccountEntity account, CreateOrderDto dto);

    InvoiceEntity approve(Long id);

    InvoiceEntity cancel(Long id);
}
