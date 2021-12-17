CREATE TABLE tb_perfil(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_user(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil_id BIGINT NOT NULL,
    FOREIGN KEY (perfil_id) REFERENCES tb_perfil(id)
);

insert into tb_perfil(id, nome) values (1, "ADMIN");
insert into tb_perfil(id, nome) values (2, "USER");

insert into tb_user(id, email, nome, senha, perfil_id) values (1, "admin@email.com", "Administrador","$2a$12$HQuG8YtuVFEAff6i.Kgm6OB1QmrVQSmNCsijpSwK3R6PxR.nTZEKK", 1);
insert into tb_user(id, email, nome, senha, perfil_id) values (2, "user@email.com", "Usuário","$2a$12$ND8nC5lhfT8SnUbHcWW4vOE2eCDRSdmaYsAz8dnsqZ.TxLqHyabhS", 2);
