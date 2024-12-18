package com.eta.controller.advice;

import com.eta.commons.exceptions.ExpenseIdMismatchException;
import com.eta.commons.exceptions.ExpenseNotFoundException;
import com.eta.commons.exceptions.ExpenseTypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.eta.commons.utils.ErrorResponse;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExpenseControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handleException(ExpenseNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("Expense-404");
        errorResponse.setErrorMessage("Expense Not found!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleException(ExpenseIdMismatchException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("Expense-400");
        errorResponse.setErrorMessage("Expense ID in the path is not same as the ID of Expense!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleException(MethodArgumentNotValidException ex){

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("Expense-400");
        errorResponse.setErrorMessage("Expense is Not Valid!");
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setFieldErrors(ex.getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleException(ExpenseTypeMismatchException ex){
        ex.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("Expense-400");
        errorResponse.setErrorMessage("Expense ID type mismatch!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        ex.printStackTrace();
        errorResponse.setErrorCode("Expense-500");
        errorResponse.setErrorMessage("A Server Exception occurred!");
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
