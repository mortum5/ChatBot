package com.gonchar.chatBot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 *
 *
 * @author Andrey Gonchar
 * @since 0.1
 */
@Configuration
@Data
@PropertySource(value = "classpath:vk.properties")
@ConfigurationProperties(prefix = "vk.api")
public class VkApiConfigurationProperties {
    @NotBlank
    private Map<String, String> properties;

}
