package com.bank.client.infrastructure.outbound.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("clients")
public class ClientEntity {
    @Id
    private Long id;

    @Column("unique_code")
    private String uniqueCode;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("document_type")
    private String documentType;

    @Column("document_number")
    private String documentNumber;
}
