package com.netheve.andikagunshop.service.impl;

import com.netheve.andikagunshop.dto.order.CreateOrderDto;
import com.netheve.andikagunshop.dto.order.OrderItem;
import com.netheve.andikagunshop.entity.AccountEntity;
import com.netheve.andikagunshop.entity.CustomerOrderEntity;
import com.netheve.andikagunshop.entity.InvoiceEntity;
import com.netheve.andikagunshop.entity.ProductEntity;
import com.netheve.andikagunshop.entity.enums.InvoiceStatus;
import com.netheve.andikagunshop.repository.InvoiceRepository;
import com.netheve.andikagunshop.repository.ProductRepository;
import com.netheve.andikagunshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public InvoiceEntity create(AccountEntity account, CreateOrderDto dto) {
        Set<CustomerOrderEntity> orders = new HashSet<>();

        for (OrderItem item : dto.getItems()) {
            ProductEntity product = productRepository.findById(item.getItemId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")
            );

            CustomerOrderEntity customerOrder = CustomerOrderEntity.builder()
                    .product(product)
                    .quantity(item.getQuantity())
                    .build();

            orders.add(customerOrder);
        }

        InvoiceEntity invoice = InvoiceEntity.builder()
                .customerOrders(orders)
                .status(InvoiceStatus.UNPAID)
                .account(account)
                .build();

        return invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceEntity approve(Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found")
        );

        invoice.setStatus(InvoiceStatus.PAID);

        return invoiceRepository.save(invoice);
    }

    @Override
    public InvoiceEntity cancel(Long id) {
        InvoiceEntity invoice = invoiceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found")
        );

        invoice.setStatus(InvoiceStatus.CANCEL);

        return invoiceRepository.save(invoice);
    }
}
