package com.netheve.andikagunshop.service.impl;

import com.netheve.andikagunshop.common.configuration.property.StorageProperty;
import com.netheve.andikagunshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path fileStorageLocation;

    @Autowired
    public StorageServiceImpl(StorageProperty fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create the directory where the upload files will be saved.", ex);
        }
    }

    public void store(MultipartFile file, String fileName) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be null");
        }

        try {
            if (fileName.contains("..")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry! File name which contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IO Exception was thrown");
        }
    }

    @Override
    public byte[] get(String filePath) {
        try {
            Path target = this.fileStorageLocation.resolve(filePath);
            return Files.readAllBytes(target);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Download Failed");
        }
    }
}

