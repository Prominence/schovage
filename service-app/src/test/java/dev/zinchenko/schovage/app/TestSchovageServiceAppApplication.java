package dev.zinchenko.schovage.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestSchovageServiceAppApplication {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresqlContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgresql:latest"));
    }

    public static void main(String[] args) {
        SpringApplication.from(SchovageServiceAppApplication::main).with(TestSchovageServiceAppApplication.class).run(args);
    }

}
