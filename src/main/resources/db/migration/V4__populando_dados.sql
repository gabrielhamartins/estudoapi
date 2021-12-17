INSERT INTO tb_usuario (id, nome, email, cpf, nascimento) VALUES (1, "Ronaldo Nazário", "rnld@fifa.com", "29060013093", "1976-09-22");
INSERT INTO tb_usuario (id, nome, email, cpf, nascimento) VALUES (2, "Edson Nascimento", "pele@fifa.com", "57958796064", "1940-10-23");
INSERT INTO tb_usuario (id, nome, email, cpf, nascimento) VALUES (3, "Cristiano Aveiro", "cr07@fifa.com", "25111629033", "1985-02-05");

INSERT INTO tb_veiculo (id, marca, modelo, ano) VALUES (1, "Alfa Romeo", "145 Elegant 2.0 16V", "1998-1");
INSERT INTO tb_veiculo (id, marca, modelo, ano) VALUES (2, "Maserati", "430 2.0 V6", "1994-1");
INSERT INTO tb_veiculo (id, marca, modelo, ano) VALUES (3, "Toyota", "Corolla LE 2.2 16V", "1996-1");

INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (1, 1);
INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (1, 2);
INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (2, 1);
INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (3, 3);
INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (3, 2);
INSERT INTO usuarios_veiculos(usuario_id, veiculo_id) VALUES (3, 1);