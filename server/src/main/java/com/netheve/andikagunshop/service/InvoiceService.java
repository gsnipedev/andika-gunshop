package com.netheve.andikagunshop.service;

import com.netheve.andikagunshop.entity.InvoiceEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InvoiceService {
    List<InvoiceEntity> index(Pageable pageable);
    InvoiceEntity show(Long id);
}
