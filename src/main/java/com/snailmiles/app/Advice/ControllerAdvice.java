package com.snailmiles.app.Advice;

import com.snailmiles.app.Config.SecurityConfig;
import com.snailmiles.app.Exceptions.*;
import com.snailmiles.app.Exceptions.categories.CategoriesException;
import com.snailmiles.app.Exceptions.expiredApiKey.ExpiredApiKeyException;
import com.snailmiles.app.Exceptions.expiredApiKey.NewApiResponse;
import com.snailmiles.app.Models.User;
import com.snailmiles.app.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Calendar;

@RestControllerAdvice
@AllArgsConstructor
public class ControllerAdvice {
    private final UserRepository userRepository;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidApiKeyException.class)
    public ErrorResponse handleInvalidKey(InvalidApiKeyException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public ErrorResponse handleAccountNotFoundException(AccountNotFoundException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterServiceException.class)
    public ErrorResponse handleRegisterServiceException(RegisterServiceException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResetPasswordServiceException.class)
    public ErrorResponse handleResetPasswordServiceException(ResetPasswordServiceException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ErrorResponse handleAccountAlreadyExistsException(AccountAlreadyExistsException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoriesException.class)
    public ErrorResponse handleCategoriesException(CategoriesException ex){
        return ErrorResponse.builder().withMessage(ex.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExpiredApiKeyException.class)
    public NewApiResponse handleExpiredApiKeyException(ExpiredApiKeyException ex){
        String token = SecurityConfig.generateNewToken();
        User u = ex.getUser();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);
        u.setTokenExpiration(calendar.getTime());
        u.setToken(token);
        userRepository.save(u);
        return NewApiResponse.builder().withToken(token).build();
    }

}
