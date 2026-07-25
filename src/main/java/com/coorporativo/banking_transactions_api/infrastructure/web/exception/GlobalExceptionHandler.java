package com.coorporativo.banking_transactions_api.infrastructure.web.exception;


import com.coorporativo.banking_transactions_api.domain.exception.AccountNotFoundException;
import com.coorporativo.banking_transactions_api.domain.exception.InsufficientResourcesException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage>  handleAccountNotFound(AccountNotFoundException ex, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                "No encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                Collections.singletonList("El recurso de cuenta solicitado no existe en nuestros registros")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientResourcesException.class)
    public ResponseEntity<ErrorMessage> handleInsufficientBalance(InsufficientResourcesException ex, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud incorrecta",
                request.getRequestURI(), // Pasamos el Path al puesto 3
                ex.getMessage(),          // Pasamos el Message al puesto 4
                Collections.singletonList("Operación rechazada debido a fondos insuficientes.")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorMessage> handleGeneralException(Exception ex, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                request.getRequestURI(), // Pasamos el Path al puesto 3
                "Ocurrió un error interno inesperado", // Pasamos el Message al puesto 4
                Collections.singletonList(ex.getMessage())

        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
