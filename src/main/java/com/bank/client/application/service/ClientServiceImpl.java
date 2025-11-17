package com.bank.client.application.service;

import com.bank.client.domain.exception.ClientNotFoundException;
import com.bank.client.domain.model.Client;
import com.bank.client.domain.port.in.ClientUseCase;
import com.bank.client.domain.port.out.ClientRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/* * Esta clase es POJO (Plain Old Java Object).
* No usamos @Service de Spring aquí para mantener la arquitectura pura.
* La inyectaremos luego usando una clase de Configuración en 'infrastructure'.
*/

@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Mono<Client> getClientByUniqueCode(String uniqueCode) {
        log.info("Iniciando búsqueda de cliente con uniqueCode: {}", uniqueCode);
        // 1. Validaciones de negocio (Java Puro)
        if (uniqueCode == null || uniqueCode.isBlank()) {
            return Mono.error(new IllegalArgumentException("El uniqueCode es requerido"));
        }

        // 2. Llamada al puerto de salida (Repositorio)
        return clientRepositoryPort.findByUniqueCode(uniqueCode)
                // 3. Si lo encuentra, hacemos un log
                .doOnNext(client -> log.info("Cliente encontrado: {} {}", client))
                // 4. Si NO lo encuentra, lanzamos nuestra excepción de dominio
                .switchIfEmpty(Mono.error(ClientNotFoundException.withUniqueCode(uniqueCode)));
    }
}
