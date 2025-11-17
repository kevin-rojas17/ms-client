package com.bank.client.infrastructure.outbound.repository.mapper;

import com.bank.client.domain.model.Client;
import com.bank.client.infrastructure.outbound.repository.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientDbMapper {
    Client toDomain(ClientEntity entity);

    @Mapping(target = "id", ignore = true)
    ClientEntity toEntity(Client domain);
}
