<?php
session_start();

// Verificar si el usuario ha iniciado sesión
if (!isset($_SESSION['user'])) {
    header("Location: login.php"); // Redirigir a login si no hay sesión activa
    exit();
}

$user = $_SESSION['user']; // Obtener los datos del usuario desde la sesión
?>