package com.bank.client.domain.port.out;

import com.bank.client.domain.model.Client;
import reactor.core.publisher.Mono;

public interface ClientRepositoryPort {
    /**
     * Contrato: Buscar un cliente por su código único de negocio.
     *
     * @param uniqueCode El código de negocio.
     * @return Un Mono que emite el Cliente si se encuentra, o vacío si no.
     */
    Mono<Client> findByUniqueCode(String uniqueCode);
}
