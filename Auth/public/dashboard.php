<?php
session_start();
require_once '../config/database.php';

if (!isset($_SESSION['user_id'])) { 
    echo "No has iniciado sesión.";
    exit();
}
// Consultar el primer nombre desde la tabla Perfiles
global $pdo;
$stmt = $pdo->prepare("SELECT primer_nombre FROM perfiles WHERE id = :user_id");
$stmt->bindParam(':user_id', $_SESSION['user_id'], PDO::PARAM_INT);
$stmt->execute();
$perfil = $stmt->fetch(PDO::FETCH_ASSOC);

if ($perfil) {
    echo "Bienvenid@, " . htmlspecialchars($perfil['primer_nombre']);
} else {
    echo "No se encontró el perfil.";
}

// Consultar el nombre del rol desde la tabla 
global $pdo;
$stmt = $pdo->prepare("SELECT id_rol FROM usuarios WHERE id_perfil = :user_id");
$stmt->bindParam(':user_id', $_SESSION['user_id'], PDO::PARAM_INT);
$stmt->execute();
$perfil = $stmt->fetch(PDO::FETCH_ASSOC);
// Asignar el nombre del rol según id_rol
if ($perfil) {
    $rol_nombre = ($perfil['id_rol'] == 1) ? "Admin" : "Usuario normal";
}
?>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AquaBalance - Inicio</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <div class="logo">AquaBalance</div>
        <nav>
            <ul>
                <li><a href="agregar_recurso.php">Agregar Recurso</a></li>
                <li><a href="contaminantes.php">Contaminantes</a></li>
                <li><a href="generar_alerta.php">Generar Alerta</a></li>
            </ul>
        </nav>
        <div class="user-icon">
            <i class="fas fa-user-circle"></i>
            <span><?php echo $_SESSION['user_email']; ?></span>
            <ul class="dropdown">
                <li><a href="perfil.php">Perfil</a></li>
                <li><a href="logout.php">Cerrar Sesión</a></li>
            </ul>
        </div>
    </header>
    
    <main>
        <section class="welcome">
            <h1>Bienvenido, <?php 
            echo $_SESSION['user_email'];
            ?>
            <p>Has iniciado sesión como <strong><?php echo $rol_nombre; ?></strong>.</p>
        </section>

        <section class="info">
            <h2>¿Qué es AquaBalance?</h2>
            <p>AquaBalance es una plataforma diseñada para el seguimiento de la calidad del agua, gestión de recursos hídricos y generación de alertas ante posibles contaminaciones.</p>
        </section>
    </main>
    
    <footer>
        <p>Contacto: contacto@aquabalance.com | Tel: +57 300 123 4567</p>
        <p>&copy; 2025 AquaBalance. Todos los derechos reservados.</p>
    </footer>
</body>
</html>