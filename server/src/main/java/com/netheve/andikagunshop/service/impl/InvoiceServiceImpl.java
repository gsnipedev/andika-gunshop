package com.netheve.andikagunshop.service.impl;

import com.netheve.andikagunshop.entity.InvoiceEntity;
import com.netheve.andikagunshop.repository.InvoiceRepository;
import com.netheve.andikagunshop.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceEntity> index(Pageable pageable) {
        return invoiceRepository.findAllBy(pageable);
    }

    @Override
    public InvoiceEntity show(Long id) {
        return invoiceRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found")
        );
    }
}
