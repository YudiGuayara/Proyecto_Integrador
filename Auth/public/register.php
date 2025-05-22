<?php
require_once '../config/database.php';
require_once '../app/models/User.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $primerNombre = trim($_POST["primer_nombre"]);
    $segundoNombre = trim($_POST["segundo_nombre"]);
    $primerApellido = trim($_POST["primer_apellido"]);
    $segundoApellido = trim($_POST["segundo_apellido"]);
    $tipoDocumento = trim($_POST["tipo_documento"]);
    $numeroDocumento = trim($_POST["numero_documento"]);
    $celular = trim($_POST["celular"]);
    $fechaExpedicion = trim($_POST["fecha_expedicion"]);
    $email = trim($_POST["email"]);
    $password = trim($_POST["password"]);
    $passwordConfirm = trim($_POST["password_confirm"]);
    $rol = trim($_POST["rol"]);

    if (!empty($primerNombre) && !empty($primerApellido) && !empty($tipoDocumento) && 
        !empty($numeroDocumento) && !empty($fechaExpedicion) && !empty($email) && 
        !empty($password) && !empty($rol)) {

        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            die("❌ Error: Correo electrónico inválido.");
        }

        if ($password !== $passwordConfirm) {
            die("❌ Error: Las contraseñas no coinciden.");
        }

        if (User::register($primerNombre, $segundoNombre, $primerApellido, $segundoApellido, 
                           $tipoDocumento, $numeroDocumento, $celular, $fechaExpedicion, 
                           $email, $password, $rol)) {
            header("Location: login.html?success=1");
            exit();
        } else {
            echo "❌ Error al registrar usuario.";
        }
    } else {
        echo "⚠️ Todos los campos obligatorios deben estar completos.";
    }
}
?>