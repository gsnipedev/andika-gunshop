package com.netheve.andikagunshop.common.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "localstorage")
@Data
public class StorageProperty {
    private String uploadDir;
}
