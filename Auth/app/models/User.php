<?php
require_once __DIR__ . '/../../config/database.php';

class User {
    private static $pdo;

    public static function init() {
        global $pdo;
        self::$pdo = $pdo;
    }

    public static function register($primerNombre, $segundoNombre, $primerApellido, $segundoApellido, $tipoDocumento, $numeroDocumento, $celular, $fechaExpedicion, $email, $password, $rol) {
        if (!self::$pdo) {
            self::init();
        }

        try {
            self::$pdo->beginTransaction();

            // Verificar si el correo ya existe
            $stmt = self::$pdo->prepare("SELECT COUNT(*) FROM Usuarios WHERE correo = ?");
            $stmt->execute([$email]);
            if ($stmt->fetchColumn() > 0) {
                throw new Exception("❌ Error: El correo ya está registrado.");
            }

            // Encriptar contraseña
            $hashedPassword = password_hash($password, PASSWORD_BCRYPT);

            // Insertar en Perfiles
            $stmt = self::$pdo->prepare("INSERT INTO Perfiles (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, tipo_documento, numero_documento, celular, fecha_expedicion, correo, contraseña) 
                                         VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            $stmt->execute([$primerNombre, $segundoNombre, $primerApellido, $segundoApellido, $tipoDocumento, $numeroDocumento, $celular, $fechaExpedicion, $email, $hashedPassword]);
            $perfilId = self::$pdo->lastInsertId();

            // Obtener el ID del rol
            $stmt = self::$pdo->prepare("SELECT id FROM Roles WHERE nombre = ?");
            $stmt->execute([$rol]);
            $rolId = $stmt->fetchColumn();

            if (!$rolId) {
                throw new Exception("❌ Error: El rol no existe.");
            }

            // Insertar en Usuarios
            $stmt = self::$pdo->prepare("INSERT INTO Usuarios (correo, contraseña, id_perfil, id_rol) VALUES (?, ?, ?, ?)");
            $stmt->execute([$email, $hashedPassword, $perfilId, $rolId]);

            self::$pdo->commit();
            return true;

        } catch (PDOException $e) {
            self::$pdo->rollBack();
            error_log("Error en registro: " . $e->getMessage());
            return false;
        } catch (Exception $e) {
            self::$pdo->rollBack();
            error_log("Error: " . $e->getMessage());
            return false;
        }
    }
}

?>
