<?php
require_once 'config/database.php';

class AuthController {
    public function login($username, $password) {
        global $pdo;
        $stmt = $pdo->prepare("SELECT * FROM users WHERE username = ?");
        $stmt->execute([$username]);
        $user = $stmt->fetch();

        if ($user && password_verify($password, $user['password'])) {
            session_start();
            $_SESSION['user'] = $user;
            header("Location: /public/dashboard.php");
        } else {
            echo "Credenciales incorrectas";
        }
    }
}
?>
