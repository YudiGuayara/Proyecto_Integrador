<?php
require_once '../config/database.php';
require_once '../app/models/User.php';

function testRegistroUsuario() {
    $resultado = User::register(
        "Juan", "Carlos", "Pérez", "Gómez",
        "Cédula de ciudadanía", "123456789", "3123456789",
        "2022-01-01", "juan@example.com", "Password123", "Usuario"
    );

    if ($resultado) {
        echo "✅ Prueba de registro exitosa.";
    } else {
        echo "❌ Error en el registro.";
    }
}

// Ejecutar la prueba
testRegistroUsuario();
?>
