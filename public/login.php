<?php
session_start();
require_once '../config/database.php'; // Asegúrate de que este archivo conecta correctamente a la BD

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $email = trim($_POST["email"]);
    $password = trim($_POST["password"]);

    if (!empty($email) && !empty($password)) {
        try {
            global $pdo;
            $stmt = $pdo->prepare("SELECT id_perfil, correo, contraseña FROM Usuarios WHERE correo = :email");
            $stmt->bindParam(':email', $email, PDO::PARAM_STR);
            $stmt->execute();
            $user = $stmt->fetch(PDO::FETCH_ASSOC);

            if ($user && password_verify($password, $user['contraseña'])) {
                // Guardar datos del usuario en sesión
                $_SESSION['user_id'] = $user['id_perfil'];
                $_SESSION['user_email'] = $user['correo'];

                // Redirigir al dashboard
                header("Location: dashboard.php");
                exit();
            } else {
                header("Location: login.html?error=" . urlencode("Correo o contraseña incorrectos."));
                exit();
            }
        } catch (PDOException $e) {
            header("Location: login.html?error=" . urlencode("Error de base de datos."));
            exit();
        }
    } else {
        header("Location: login.html?error=" . urlencode("Todos los campos son obligatorios."));
        exit();
    }
}
?>
