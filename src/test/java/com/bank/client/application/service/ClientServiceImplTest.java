package com.bank.client.application.service;

import com.bank.client.domain.exception.ClientNotFoundException;
import com.bank.client.domain.model.Client;
import com.bank.client.domain.port.out.ClientRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class ClientServiceImplTest {

    private ClientRepositoryPort repository;
    private ClientServiceImpl service;

    @BeforeEach
    void setup() {
        repository = mock(ClientRepositoryPort.class);
        service = new ClientServiceImpl(repository);
    }

    @Test
    void getClientByUniqueCode_WhenClientExists_ReturnsClient() {
        String uniqueCode = "ABC123";

        Client mockClient = Client.builder()
                .uniqueCode(uniqueCode)
                .firstName("Juan")
                .lastName("Perez")
                .documentType("DNI")
                .documentNumber("12345678")
                .build();

        when(repository.findByUniqueCode(uniqueCode))
                .thenReturn(Mono.just(mockClient));

        StepVerifier.create(service.getClientByUniqueCode(uniqueCode))
                .expectNextMatches(client ->
                        client.getUniqueCode().equals(uniqueCode)
                                && client.getFirstName().equals("Juan"))
                .verifyComplete();

        verify(repository, times(1)).findByUniqueCode(uniqueCode);
    }

    @Test
    void getClientByUniqueCode_WhenClientDoesNotExist_ThrowsNotFound() {
        String uniqueCode = "NOTFOUND";

        when(repository.findByUniqueCode(uniqueCode))
                .thenReturn(Mono.empty());

        StepVerifier.create(service.getClientByUniqueCode(uniqueCode))
                .expectError(ClientNotFoundException.class)
                .verify();

        verify(repository, times(1)).findByUniqueCode(uniqueCode);
    }

    @Test
    void getClientByUniqueCode_WhenUniqueCodeIsInvalid_ThrowsIllegalArgument() {
        StepVerifier.create(service.getClientByUniqueCode(""))
                .expectError(IllegalArgumentException.class)
                .verify();

        verify(repository, never()).findByUniqueCode(anyString());
    }
}
