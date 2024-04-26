package com.erc.handler;

import com.erc.exception.ProductNotFoundException;
import com.erc.model.Error;
//import org.zalando.problem.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public Error handlerProductNotFound(ProductNotFoundException exceptionHandler) {
        return Error
                .builder()
                .status("FAIL")
                .message(exceptionHandler.getMessage())
                .code(HttpStatus.NOT_FOUND)
                .build();

    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ProblemDetail handlerProductNotFoundProblemDetail(ProductNotFoundException exceptionHandler) {
//        return ProblemDetail
//
//
//    }
}
