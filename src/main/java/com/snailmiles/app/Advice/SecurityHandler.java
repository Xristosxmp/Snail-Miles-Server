package com.snailmiles.app.Advice;

import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.Exceptions.InvalidApiKeyException;
import com.snailmiles.app.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@AllArgsConstructor
public class SecurityHandler implements HandlerInterceptor {

    @Value("${private.token}")
    private String token;

    private final UserRepository userRepository;

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getHeader("API-KEY") == null) throw new InvalidApiKeyException("Unauthorized");
        if(!request.getHeader("API-KEY").equals(token)) throw new InvalidApiKeyException("Unauthorized");

        if (handler instanceof org.springframework.web.method.HandlerMethod handlerMethod) {
            if (handlerMethod.getMethod().isAnnotationPresent(SkipSecurity.class)
                    || handlerMethod.getBeanType().isAnnotationPresent(SkipSecurity.class)) {
                log.info(request.getRequestURI());
                return true;
            }
        }




        if(request.getHeader("Authorization") == null) throw new InvalidApiKeyException("Unauthorized Authorization");
        if(userRepository.findByToken(request.getHeader("Authorization")) == null)
        {
            throw new InvalidApiKeyException("Invalid/Expired Token Session");
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}