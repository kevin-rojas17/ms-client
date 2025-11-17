package com.bank.client.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    /**
     * El identificador de negocio (NO es el 'id' de la BD).
     * Este es el 'codigoUnico' del requisito.
     */
    private String uniqueCode;

    /**
     * Nombres del cliente.
     */
    private String firstName;

    /**
     * Apellidos del cliente.
     */
    private String lastName;

    /**
     * Tipo de documento (DNI, PASSPORT, etc.)
     */
    private String documentType;

    /**
     * Número del documento.
     */
    private String documentNumber;
    
}
