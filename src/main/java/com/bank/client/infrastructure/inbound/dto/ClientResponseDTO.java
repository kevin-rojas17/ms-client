package com.bank.client.infrastructure.inbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {
    // Estos nombres determinarán los campos del JSON.
    // Si necesitas que el JSON salga en español (ej: "nombres"),
    // puedes usar @JsonProperty("nombres") sobre el campo.

    private String uniqueCode;
    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;
}
