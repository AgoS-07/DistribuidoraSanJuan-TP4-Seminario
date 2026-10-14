DROP DATABASE distribuidora_san_juan;

CREATE DATABASE distribuidora_san_juan;

USE distribuidora_san_juan;

CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contraseña VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

CREATE TABLE proveedor (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    razonSocial VARCHAR(100) NOT NULL,
    cuit VARCHAR(20) NOT NULL,
    telefono VARCHAR(30),
    email VARCHAR(100),
    direccion VARCHAR(150)
);

CREATE TABLE producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    stockMinimo INT NOT NULL DEFAULT 0,
    idProveedor INT NOT NULL,
    FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor)
);

CREATE TABLE movimiento (
    idMovimiento INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipoMovimiento VARCHAR(20) NOT NULL,
    idUsuario INT NOT NULL,
    FOREIGN KEY (idUsuario) REFERENCES usuario(idUsuario)
);

CREATE TABLE detalle_movimiento (
    idDetalle INT AUTO_INCREMENT PRIMARY KEY,
    idMovimiento INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    FOREIGN KEY (idMovimiento) REFERENCES movimiento(idMovimiento),
    FOREIGN KEY (idProducto) REFERENCES producto(idProducto)
);

CREATE TABLE reposicion (
    idReposicion INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    estado VARCHAR(30) NOT NULL,
    cantidad INT NOT NULL,
    idProducto INT NOT NULL,
    idProveedor INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES producto(idProducto),
    FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor)
);
USE distribuidora_san_juan;

SHOW TABLES;
USE distribuidora_san_juan;

INSERT INTO usuario (nombre, apellido, nombreUsuario, contraseña, rol)
VALUES
('Juan', 'Perez', 'jperez', '1234', 'Administrador'),
('Carlos', 'Gomez', 'cgomez', '1234', 'Deposito'),
('Laura', 'Diaz', 'ldiaz', '1234', 'Compras');

INSERT INTO proveedor (razonSocial, cuit, telefono, email, direccion)
VALUES
('Alimentos del Oeste', '30-12345678-9', '264-5551111', 'contacto@alimentos.com', 'Av. San Martin 123'),
('Distribuidora Cuyo', '30-98765432-1', '264-5552222', 'ventas@cuyo.com', 'Calle Mendoza 456');

INSERT INTO producto (nombre, descripcion, precio, stock, stockMinimo, idProveedor)
VALUES
('Arroz 1kg', 'Arroz blanco largo fino', 1500.00, 50, 10, 1),
('Aceite 900ml', 'Aceite de girasol', 2200.00, 30, 8, 1),
('Fideos 500g', 'Fideos secos', 1200.00, 5, 10, 2);

SELECT * FROM producto;

INSERT INTO movimiento (fecha, tipoMovimiento, idUsuario)
VALUES (NOW(), 'ENTRADA', 2);

INSERT INTO detalle_movimiento (idMovimiento, idProducto, cantidad)
VALUES (LAST_INSERT_ID(), 3, 20);

UPDATE producto
SET stock = stock + 20
WHERE idProducto = 3;

SELECT * FROM producto
WHERE idProducto = 3;

INSERT INTO movimiento (fecha, tipoMovimiento, idUsuario)
VALUES (NOW(), 'SALIDA', 2);

INSERT INTO detalle_movimiento (idMovimiento, idProducto, cantidad)
VALUES (LAST_INSERT_ID(), 3, 5);

UPDATE producto
SET stock = stock - 5
WHERE idProducto = 3;

SELECT * FROM producto
WHERE idProducto = 3;

SELECT idProducto, nombre, stock, stockMinimo
FROM producto
WHERE stock < stockMinimo;

UPDATE producto
SET stock = 5
WHERE idProducto = 3;

SELECT idProducto, nombre, stock, stockMinimo
FROM producto
WHERE stock < stockMinimo;

INSERT INTO reposicion (fecha, estado, cantidad, idProducto, idProveedor)
VALUES (NOW(), 'PENDIENTE', 20, 3, 2);

SELECT * FROM reposicion;