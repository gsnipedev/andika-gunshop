package com.netheve.andikagunshop.common.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "web")
@Data
public class WebProperty {
    private String adminPanelUrl;
}
