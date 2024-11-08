package dev.zinchenko.schovage.spring.starter;

import dev.zinchenko.schovage.core.conf.StorageSettings;
import dev.zinchenko.schovage.core.service.StorageService;
import dev.zinchenko.schovage.core.service.StorageServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchovageAutoconfiguration {

    @Bean
    @ConfigurationProperties("dev.zinchenko.schovage")
    public StorageSettings storageSettings() {
        return new StorageSettings();
    }

    @Bean
    public StorageService schovageService(StorageSettings storageSettings) {
        return new StorageServiceImpl(storageSettings);
    }
}
