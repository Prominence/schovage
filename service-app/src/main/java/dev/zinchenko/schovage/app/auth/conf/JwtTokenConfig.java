package dev.zinchenko.schovage.app.auth.conf;

import dev.zinchenko.schovage.app.auth.properties.JwtTokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtTokenProperties.class)
public class JwtTokenConfig {
}
