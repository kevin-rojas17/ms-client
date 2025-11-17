package com.bank.client.infrastructure.inbound.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String code;      // Ej: "CLIENT_NOT_FOUND"
    private String message;   // Ej: "Client not found with uniqueCode: 123"
    private String traceId;
    private LocalDateTime timestamp;
}
