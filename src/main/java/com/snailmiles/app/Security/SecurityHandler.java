package com.snailmiles.app.Security;

import com.snailmiles.app.Annotations.SkipSecurity;
import com.snailmiles.app.Exceptions.expiredApiKey.ExpiredApiKeyException;
import com.snailmiles.app.Exceptions.InvalidApiKeyException;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

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

        User user = userRepository.findByToken(request.getHeader("Authorization"));
        Date now = new Date();
        Date token_expiration = user.getTokenExpiration();

        log.info(now.toString());
        log.info(token_expiration.toString());
        if (token_expiration == null || now.after(token_expiration)) {
            log.info("Expired Token Session");
            throw new ExpiredApiKeyException("Expired Token Session", user);
        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}