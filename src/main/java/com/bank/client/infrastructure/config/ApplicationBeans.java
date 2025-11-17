package com.bank.client.infrastructure.config;

import com.bank.client.application.service.ClientServiceImpl;
import com.bank.client.domain.port.in.ClientUseCase;
import com.bank.client.domain.port.out.ClientRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeans {
    /**
     * Aquí creamos el Bean del Caso de Uso (Application Layer).
     * Inyectamos el puerto de salida (Repository) que ya tenemos implementado
     * en la capa de infraestructura.
     */
    @Bean
    public ClientUseCase clientUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new ClientServiceImpl(clientRepositoryPort);
    }
}
