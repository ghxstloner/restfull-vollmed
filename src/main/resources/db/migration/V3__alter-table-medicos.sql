ALTER TABLE medicos ADD activo tinyint DEFAULT 1;
UPDATE medicos set activo = 1;