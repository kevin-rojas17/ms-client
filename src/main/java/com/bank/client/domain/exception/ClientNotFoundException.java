package com.bank.client.domain.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
    /**
     * Método helper para crear la excepción de forma estándar.
     */
    // prueba para git v1
    public static ClientNotFoundException withUniqueCode(String uniqueCode) {
        return new ClientNotFoundException("Client not found with uniqueCode: " + uniqueCode);
    }
}
