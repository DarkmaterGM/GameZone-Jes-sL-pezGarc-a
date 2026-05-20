-- Creación de la base de datos GameZone
DROP DATABASE IF EXISTS GameZone;
CREATE DATABASE GameZone
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE GameZone;

CREATE TABLE IF NOT EXISTS `CATEGORIA` (
    `id` VARCHAR(9)  NOT NULL,
    `Nombre` VARCHAR(40) NOT NULL,
    `Descripcion` VARCHAR(60) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `EMPLEADO` (
    `id` INT(9) NOT NULL,
    `Nombre` VARCHAR(15) NOT NULL,
    `Apellido1` VARCHAR(15)  NOT NULL,
    `Apellido2` VARCHAR(15),
    `Fecha_contratacion` DATE NOT NULL,
    `Cargo` VARCHAR(255) NOT NULL,
    `anios_experiencia` INT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `CLIENTE` (
    `DNI` VARCHAR(9) NOT NULL,
    `Nombre` VARCHAR(15) NOT NULL,
    `Apellido1` VARCHAR(15) NOT NULL,
    `Apellido2` VARCHAR(15),
    `Telefono` INT(9),
    `Email` VARCHAR(30),
    `Direccion` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`DNI`)
);

CREATE TABLE IF NOT EXISTS `PRODUCTO` (
    `id` VARCHAR(9) NOT NULL,
    `Nombre` VARCHAR(30) NOT NULL,
    `Plataforma` VARCHAR(30) NOT NULL,
    `id_categoria` VARCHAR(9) NOT NULL,
    `Precio` DECIMAL(7, 2) NOT NULL,
    `Stock` INT NOT NULL DEFAULT 0,
    `Fecha_lanzamiento` DATE NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `VENTA` (
    `id` INT(9) NOT NULL,
    `Fecha_compra` DATE NOT NULL,
    `id_cliente` VARCHAR(9) NOT NULL,    
    `id_empleado` INT(9) NOT NULL,       
    `Importe` DECIMAL(7, 2) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `DETALLE_VENTA` (
    `id_venta`        INT(9) NOT NULL,
    `id_producto`     VARCHAR(9) NOT NULL,    
    `Cantidad`        INT NOT NULL,
    `Precio_unitario` DECIMAL(7, 2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (`id_venta`, `id_producto`)   
);


-- Claves foráneas

ALTER TABLE `PRODUCTO`
    ADD CONSTRAINT `fk_PRODUCTO_id_categoria`
    FOREIGN KEY (`id_categoria`) REFERENCES `CATEGORIA` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

ALTER TABLE `VENTA`
    ADD CONSTRAINT `fk_VENTA_id_cliente`
    FOREIGN KEY (`id_cliente`) REFERENCES `CLIENTE` (`DNI`)
    ON DELETE RESTRICT   
    ON UPDATE CASCADE;

ALTER TABLE `VENTA`
    ADD CONSTRAINT `fk_VENTA_id_empleado`
    FOREIGN KEY (`id_empleado`) REFERENCES `EMPLEADO` (`id`)
    ON DELETE RESTRICT   
    ON UPDATE CASCADE;

ALTER TABLE `DETALLE_VENTA`
    ADD CONSTRAINT `fk_DETALLE_VENTA_id_venta`
    FOREIGN KEY (`id_venta`) REFERENCES `VENTA` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE `DETALLE_VENTA`
    ADD CONSTRAINT `fk_DETALLE_VENTA_id_producto`
    FOREIGN KEY (`id_producto`) REFERENCES `PRODUCTO` (`id`)   -- ✅ FK restaurada
    ON DELETE RESTRICT
    ON UPDATE CASCADE;