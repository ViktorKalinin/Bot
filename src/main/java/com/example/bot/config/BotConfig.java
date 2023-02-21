package com.example.bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Setter
@Configuration
@ConfigurationProperties(ignoreUnknownFields = false, prefix = "bot")
public class BotConfig{
        private String name;
        private String token;
}
