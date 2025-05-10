package com.snailmiles.app.Advice;

import com.snailmiles.app.Exceptions.InvalidApiKeyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class SecurityHandler implements HandlerInterceptor {

    @Value("${private.token}")
    private String token;

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader("API-KEY") == null) throw new InvalidApiKeyException("Unauthorized");
        if(!request.getHeader("API-KEY").equals(token)) throw new InvalidApiKeyException("Unauthorized");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}