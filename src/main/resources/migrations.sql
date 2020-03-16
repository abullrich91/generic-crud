CREATE database abullrichdb;

USE abullrichdb;

CREATE TABLE store (
    id int not null AUTO_INCREMENT,
    direccion text not null,
    latitud int not null,
    longitud int not null,
    primary key (id));