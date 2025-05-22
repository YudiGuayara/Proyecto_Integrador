CREATE DATABASE Db_Usuarios CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE Db_Usuarios;

CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE,
    descripcion TEXT
);

INSERT INTO Roles (nombre, descripcion) VALUES 
('Administrador', 'Rol con acceso total al sistema'),
('Usuario', 'Rol con permisos limitados');

CREATE TABLE Perfiles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    primer_nombre VARCHAR(50) NOT NULL,
    segundo_nombre VARCHAR(50),
    primer_apellido VARCHAR(50) NOT NULL,
    segundo_apellido VARCHAR(50),
    tipo_documento VARCHAR(30) NOT NULL CHECK (tipo_documento IN ('Cédula de ciudadanía', 'Tarjeta de extranjería', 'Pasaporte')),
    numero_documento VARCHAR(20) UNIQUE NOT NULL,
    celular VARCHAR(15),
    fecha_expedicion DATE NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL
);

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    id_perfil INT NOT NULL,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_perfil) REFERENCES Perfiles(id) ON DELETE CASCADE,
    FOREIGN KEY (id_rol) REFERENCES Roles(id) ON DELETE CASCADE,
    INDEX (id_perfil),
    INDEX (id_rol)
);
