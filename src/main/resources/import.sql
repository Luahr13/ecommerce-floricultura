-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

insert into estado (nome, sigla) values('Tocantins', 'TO');
insert into estado (nome, sigla) values('Goiás', 'GO');
insert into estado (nome, sigla) values('Distrito Federal', 'DF');
insert into estado (nome, sigla) values('São Paulo', 'SP');

insert into municipio (nome, id_estado) values('Palmas', 1);
insert into municipio (nome, id_estado) values('Goiania', 2);
insert into municipio (nome, id_estado) values('Brasília', 3);
insert into municipio (nome, id_estado) values('São Paulo', 4);

insert into endereco (bairro, numero, complemento, cep, id_Municipio) values('Norte', '13', '05', '11111-111', 1);
insert into endereco (bairro, numero, complemento, cep, id_Municipio) values('Centro', '10', '01', '22222-222', 2);
insert into endereco (bairro, numero, complemento, cep, id_Municipio) values('Norte', '13', '05', '11111-222', 1);
insert into endereco (bairro, numero, complemento, cep, id_Municipio) values('Norte', '13', '05', '11111-333', 3);

insert into telefone (codigoArea, numero) values('63', '(63) 11111-1111');
insert into telefone (codigoArea, numero) values('63', '(63) 22222-2222');
insert into telefone (codigoArea, numero) values('62', '(62) 33333-3333');
insert into telefone (codigoArea, numero) values('11', '(62) 44444-4444');

insert into cliente (nome, cpf, sexo, id_telefone, id_endereco) values('Jânio', '11111111111-11', 'MASCULINO', 1, 1);
insert into cliente (nome, cpf, sexo, id_telefone, id_endereco) values('Luahr', '22222222222-22', 'MASCULINO', 2, 2);
insert into cliente (nome, cpf, sexo, id_telefone, id_endereco) values('Jânio', '33333333333-33', 'MASCULINO', 3, 3);
insert into cliente (nome, cpf, sexo, id_telefone, id_endereco) values('Leandra', '44444444444-44', 'FEMININO', 1, 1);
