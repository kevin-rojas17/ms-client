package com.bank.client.infrastructure.inbound.controller;

import com.bank.client.domain.port.in.ClientUseCase;
import com.bank.client.infrastructure.inbound.dto.ClientResponseDTO;
import com.bank.client.infrastructure.inbound.mapper.ClientApiMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Tag(name = "Client Info", description = "Endpoints for client information")
public class ClientController {
    private final ClientUseCase clientUseCase;

    private final ClientApiMapper mapper;

    @GetMapping("/{uniqueCode}")
    @Operation(summary = "Get client by unique code")
    public Mono<ResponseEntity<ClientResponseDTO>> getClient(@PathVariable String uniqueCode) {

        return clientUseCase.getClientByUniqueCode(uniqueCode)
                // Convertimos Modelo -> DTO
                .map(mapper::toResponse)
                // Envolvemos en ResponseEntity OK (200)
                .map(ResponseEntity::ok);

        // Nota: El manejo de errores (404 Not Found) se suele hacer
        // con un @ControllerAdvice global, capturando la excepcion
        // ClientNotFoundException que creamos en el dominio.
    }
}
