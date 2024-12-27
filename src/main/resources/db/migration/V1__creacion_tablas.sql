CREATE TABLE usuario(
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        login VARCHAR(100) NOT NULL,
                        clave VARCHAR(300) NOT NULL
);
CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        autor_id BIGINT NOT NULL,
                        curso VARCHAR(255) NOT NULL,
                        FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

