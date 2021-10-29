DROP DATABASE IF EXISTS Sistema;

CREATE DATABASE Sistema;

USE Sistema

CREATE TABLE Usuario(
    id BIGINT NOT NULL auto_increment, 
    username VARCHAR(64) NOT NULL UNIQUE, 
    password VARCHAR(64) NOT NULL, 
    cpf VARCHAR(15), 
    nome VARCHAR(64), 
    telefone VARCHAR(11), 
    sexo VARCHAR(1), 
    datanasc VARCHAR(20),
    descricao VARCHAR(256), 
    role VARCHAR(20) NOT NULL, 
    PRIMARY KEY (id)
);

CREATE TABLE Carro(
    id BIGINT NOT NULL auto_increment,
    placa VARCHAR(10) NOT NULL UNIQUE,
    modelo VARCHAR(64),
    chassi VARCHAR(32) NOT NULL UNIQUE,
    ano INTEGER,
    km INTEGER,
    descricaocarro VARCHAR(256),
    valor FLOAT,
    PRIMARY KEY (id)
);

CREATE TABLE Proposta(
    id BIGINT NOT NULL auto_increment,
    valorproposta FLOAT,
    condicoes VARCHAR(256),
    dataatual VARCHAR(100),
    statusproposta VARCHAR(64),
    PRIMARY KEY (id)
);
