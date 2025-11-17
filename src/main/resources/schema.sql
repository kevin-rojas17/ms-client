-- 1. Crear la tabla 'clients' si no existe
CREATE TABLE IF NOT EXISTS clients (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       unique_code VARCHAR(50) NOT NULL UNIQUE, -- BK: Debe ser único
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    document_type VARCHAR(20) NOT NULL,
    document_number VARCHAR(20) NOT NULL
    );

-- 2. Insertar datos de prueba (Seed Data)
-- Usamos 'INSERT IGNORE' para que no falle si reinicias la app y los datos ya existen.

INSERT IGNORE INTO clients (unique_code, first_name, last_name, document_type, document_number)
VALUES ('C-1001', 'Juan', 'Perez', 'DNI', '12345678');

INSERT IGNORE INTO clients (unique_code, first_name, last_name, document_type, document_number)
VALUES ('C-1002', 'Maria', 'Gomez', 'CE', '987654321');

INSERT IGNORE INTO clients (unique_code, first_name, last_name, document_type, document_number)
VALUES ('C-1003', 'Carlos', 'Lopez', 'RUC', '20123456789');