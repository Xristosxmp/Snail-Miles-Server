//package com.snailmiles.app.Advice;
//
//import com.snailmiles.app.Repositories.AuthTokenRepository;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//
//@ControllerAdvice
//@Slf4j
//@AllArgsConstructor
//public class GlobalResponseHeaderAdvice implements ResponseBodyAdvice<Object> {
//
//    private final AuthTokenRepository authTokenRepository;
//
//    @Value("${private.token}")
//    private String privateToken;
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    private static class AUTHORIZATION_RESPONSE {private int status;private String message;}
//
//    @Override public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
//    }
//
//    @Override public Object beforeBodyWrite(
//            Object body,
//            MethodParameter returnType,
//            MediaType selectedContentType,
//            Class selectedConverterType,
//            ServerHttpRequest request,
//            ServerHttpResponse response) {
//
//        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
//        String agent = request.getHeaders().getFirst(HttpHeaders.USER_AGENT);
//        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//        String authentication = request.getHeaders().getFirst("Authentication");
//        log.info(request.getURI().toString() + " called");
//
//        if(!request.getURI().toString().contains("/generate-token"))
//        if(authTokenRepository.findByToken(authentication) == null){
//            log.info("401 Unauthorized on authentication " + request.getURI());
//            return new AUTHORIZATION_RESPONSE(401,"401 Unauthorized Token");
//        }
//
//        if(!agent.equals("SnailMiles/1.0.0")){
//            log.info("401 Unauthorized on Agent" + request.getURI());
//            return new AUTHORIZATION_RESPONSE(401,"401 Unauthorized Agent");
//        }
//
//        if(authHeader == null || !authHeader.contains("Bearer")){
//            log.info("401 Unauthorized on Missing authHeader or doesnt contain Bearer" + request.getURI());
//            return new AUTHORIZATION_RESPONSE(401,"401 Unauthorized Bearer");
//        }
//
//
//
//
//            String token = authHeader.substring(7);
//            String encodedToken = Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
//            String encodedPrivateToken = Base64.getEncoder().encodeToString(privateToken.getBytes(StandardCharsets.UTF_8));
//
//            if (encodedToken.equals(encodedPrivateToken)) {
//                return body;
//            } else {
//                response.setStatusCode(HttpStatus.GATEWAY_TIMEOUT);
//                log.info("401 Unauthorized " + request.getURI());
//                return new AUTHORIZATION_RESPONSE(401,"401 Unauthorized Wrong Bearer");
//            }
//
//    }
//}
