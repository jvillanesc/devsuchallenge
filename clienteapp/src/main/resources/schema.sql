CREATE TABLE IF NOT EXISTS persona
(id_persona INT NOT NULL AUTO_INCREMENT,
nombre VARCHAR(30) NOT NULL,
genero CHAR(1) NOT NULL,
edad INT NOT NULL,
identificacion CHAR(8) NOT NULL,
direccion VARCHAR(255) NOT NULL,
telefono VARCHAR(11) NOT NULL,
PRIMARY KEY (id_persona));

CREATE TABLE IF NOT EXISTS cliente
(id_cliente INT NOT NULL AUTO_INCREMENT,
id_persona VARCHAR(30) NOT NULL,
contrasena VARCHAR(30) NOT NULL,
estado INT NOT NULL,
PRIMARY KEY (id_cliente));

ALTER TABLE cliente
ADD FOREIGN KEY (id_persona)
REFERENCES persona(id_persona);