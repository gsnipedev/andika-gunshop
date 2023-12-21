package com.netheve.andikagunshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file, String fileName);

    byte[] get(String fileName);
}
