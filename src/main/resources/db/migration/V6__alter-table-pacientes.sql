ALTER TABLE pacientes ADD activo tinyint DEFAULT 1;
UPDATE pacientes set activo = 1;