package org.example.b9routeridemanager.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();
    }
}
