package com.bank.client.infrastructure.inbound.mapper;

import com.bank.client.domain.model.Client;
import com.bank.client.infrastructure.inbound.dto.ClientResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientApiMapper {
    /**
     * Convierte del Modelo de Dominio al DTO de respuesta.
     */
    ClientResponseDTO toResponse(Client domainModel);
}
