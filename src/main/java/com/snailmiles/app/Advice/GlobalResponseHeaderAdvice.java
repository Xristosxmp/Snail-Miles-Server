package com.snailmiles.app.Advice;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ControllerAdvice
@Slf4j
public class GlobalResponseHeaderAdvice implements ResponseBodyAdvice<Object> {

    @Value("${private.token}")
    private String privateToken;

    @Getter
    @Setter
    private static class AUTHORIZATION_RESPONSE {
        private int status;
        private String message;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true; // Apply to all controllers
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        // Add the UTF-8 Content-Type header globally
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix

            // Encode both token and privateToken to Base64
            String encodedToken = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
            String encodedPrivateToken = Base64.getEncoder().encodeToString(privateToken.getBytes(StandardCharsets.UTF_8));

            if (encodedToken.equals(encodedPrivateToken)) {
                return body;
            } else {
                response.setStatusCode(HttpStatus.GATEWAY_TIMEOUT);
                AUTHORIZATION_RESPONSE errorResponse = new AUTHORIZATION_RESPONSE();
                errorResponse.setStatus(401);
                errorResponse.setMessage("401 Unauthorized");
                log.info("401 Unauthorized " + request.getURI());
                return errorResponse;
            }
        } else {
            // No Authorization header or wrong format
            response.setStatusCode(HttpStatus.GATEWAY_TIMEOUT);

            AUTHORIZATION_RESPONSE errorResponse = new AUTHORIZATION_RESPONSE();
            errorResponse.setStatus(401);
            errorResponse.setMessage("401 Unauthorized");
            log.info("401 Unauthorized MISSING " + request.getURI());

            return errorResponse;
        }
    }
}
