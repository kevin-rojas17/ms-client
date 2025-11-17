package com.bank.client.infrastructure.inbound.exception;

import com.bank.client.domain.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
    * Captura la excepción de dominio ClientNotFoundException.
    * Devuelve un HTTP 404 (NOT_FOUND).
    */
    @ExceptionHandler(ClientNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleClientNotFound(ClientNotFoundException ex) {

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND) // 404
                        .body(ErrorResponseDTO.builder()
                                .code("CLIENT_NOT_FOUND")
                                .message(ex.getMessage()) // El mensaje que pusimos en el dominio
                                .timestamp(LocalDateTime.now())
                                .build())
        );
    }

    /**
     * Captura cualquier otra excepción no controlada (Generico).
     * Devuelve un HTTP 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleGeneralError(Exception ex) {
        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                        .body(ErrorResponseDTO.builder()
                                .code("INTERNAL_SERVER_ERROR")
                                .message("An unexpected error occurred: " + ex.getMessage())
                                .timestamp(LocalDateTime.now())
                                .build())
        );
    }
}
