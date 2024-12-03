CREATE TABLE perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE usuario_perfil (
                                usuario_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (usuario_id, perfil_id),
                                FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                                FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(255) NOT NULL,
                       categoria VARCHAR(255) NOT NULL
);

CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensaje TEXT NOT NULL,
                        fecha_creacion TIMESTAMP NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        autor_id BIGINT,
                        curso_id BIGINT,
                        FOREIGN KEY (autor_id) REFERENCES usuario(id),
                        FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           fecha_creacion TIMESTAMP NOT NULL,
                           solucion BOOLEAN NOT NULL,
                           autor_id BIGINT,
                           topico_id BIGINT,
                           FOREIGN KEY (autor_id) REFERENCES usuario(id),
                           FOREIGN KEY (topico_id) REFERENCES topico(id)
);