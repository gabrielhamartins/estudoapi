create table usuarios_veiculos(
usuario_id bigint not null,
veiculo_id bigint not null,
primary key (usuario_id, veiculo_id)
);