package com.snailmiles.app.Config;

import com.snailmiles.app.Advice.SecurityHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final SecurityHandler securityHandler;

    @Override public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityHandler);
    }
}
