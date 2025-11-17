package com.bank.client.infrastructure.outbound.repository.r2dbc;

import com.bank.client.domain.model.Client;
import com.bank.client.domain.port.out.ClientRepositoryPort;
import com.bank.client.infrastructure.outbound.repository.SpringDataClientRepository;
import com.bank.client.infrastructure.outbound.repository.mapper.ClientDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepositoryPort {
    private final SpringDataClientRepository springRepository;
    private final ClientDbMapper mapper;
    @Override
    public Mono<Client> findByUniqueCode(String uniqueCode) {
        return springRepository.findByUniqueCode(uniqueCode)
                .map(mapper::toDomain);
    }
}
