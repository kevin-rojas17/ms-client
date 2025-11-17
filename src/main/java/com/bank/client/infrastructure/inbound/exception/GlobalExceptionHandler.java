package com.bank.client.infrastructure.inbound.exception;

import com.bank.client.domain.exception.ClientNotFoundException;
import lombok.extern.slf4j.Slf4j; // <--- 1. Asegúrate de tener esto o el Logger manual
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j // <--- 2. Anotación para habilitar 'log'
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public Mono<ErrorResponseDTO> handleClientNotFound(ClientNotFoundException ex) {

        // --- ESTA ES LA LÍNEA QUE TE FALTA ---
        log.warn("Excepción controlada: Cliente no encontrado. Mensaje: {}", ex.getMessage());
        // -------------------------------------

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .code("CLIENT_NOT_FOUND")
                .message(ex.getMessage())
                .traceId(MDC.get("traceId")) // Ojo: En WebFlux puro MDC a veces se pierde sin configuración extra
                .timestamp(LocalDateTime.now())
                .build();
        return Mono.just(error);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponseDTO>> handleGeneralError(Exception ex) {
        // También es buena práctica loguear el error general grave
        log.error("Error inesperado en el sistema", ex);

        return Mono.just(
                ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ErrorResponseDTO.builder()
                                .code("INTERNAL_SERVER_ERROR")
                                .message("An unexpected error occurred: " + ex.getMessage())
                                .traceId(MDC.get("traceId"))
                                .timestamp(LocalDateTime.now())
                                .build())
        );
    }
}