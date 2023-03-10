package io.github.rodolfoMeneguetti.controller;

import io.github.rodolfoMeneguetti.exception.ApiErrors;
import io.github.rodolfoMeneguetti.exception.RegraNegocioException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemError = ex.getMessage();
        return new ApiErrors(mensagemError);
    }


}
