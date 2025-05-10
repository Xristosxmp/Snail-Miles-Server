package com.snailmiles.app.Advice;

import com.snailmiles.app.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidApiKeyException.class)
    public String handleInvalidKey(){
        return "Unauthorized";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorizedException(){
        return "Unauthorized";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public String handleAccountNotFoundException(AccountNotFoundException ex){
        return "Ο λογαριασμός δεν βρέθηκε κατά την ανανέωση πόντων";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RegisterServiceException.class)
    public String handleRegisterServiceException(RegisterServiceException ex){
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResetPasswordServiceException.class)
    public String handleResetPasswordServiceException(ResetPasswordServiceException ex){
        return ex.getMessage();
    }


}
