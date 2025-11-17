package com.bank.client.domain.port.in;

import com.bank.client.domain.model.Client;
import reactor.core.publisher.Mono;

public interface ClientUseCase {
    /**
     * Caso de Uso: Obtener un cliente por su código único de negocio.
     *
     * @param uniqueCode El código de negocio del cliente.
     * @return Un Mono que emite el Cliente si se encuentra.
     */
    Mono<Client> getClientByUniqueCode(String uniqueCode);
}
