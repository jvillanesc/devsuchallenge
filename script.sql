
--MSV Cliente

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

--MSV Cliente

CREATE TABLE IF NOT EXISTS cuenta
(id_cuenta INT NOT NULL AUTO_INCREMENT,
nro_cuenta CHAR(6) NOT NULL,
tipo_cuenta CHAR(1) NOT NULL,
saldo_inicial NUMBER NOT NULL,
identificacion_cliente CHAR(8) NOT NULL,
nombre_cliente VARCHAR(30) NOT NULL,
estado INT,
PRIMARY KEY (id_cuenta));

CREATE TABLE IF NOT EXISTS movimiento
(id_movimiento INT NOT NULL AUTO_INCREMENT,
fecha TIMESTAMP NOT NULL,
tipo_movimiento CHAR(1) NOT NULL,
saldo_inicial NUMBER NOT NULL,
monto NUMBER NOT NULL,
saldo_disponible NUMBER NOT NULL,
id_cuenta INT NOT NULL,
PRIMARY KEY (id_movimiento));

ALTER TABLE movimiento
ADD FOREIGN KEY (id_cuenta)
REFERENCES cuenta(id_cuenta);
