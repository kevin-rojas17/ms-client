package com.bank.client.infrastructure.outbound.repository;

import com.bank.client.infrastructure.outbound.repository.entity.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SpringDataClientRepository extends R2dbcRepository<ClientEntity, Long> {
    // Query Method: Spring genera "SELECT * FROM clients WHERE unique_code = ?"
    Mono<ClientEntity> findByUniqueCode(String uniqueCode);
}
