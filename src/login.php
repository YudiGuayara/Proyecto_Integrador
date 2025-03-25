<?php
$conn = new mysqli('localhost', 'root', '', 'login_system');
if ($conn->connect_error) { die('Error de conexión: ' . $conn->connect_error); }

$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT * FROM users WHERE username='$username'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    if (password_verify($password, $row['password'])) {
        echo "<script>window.location='dashboard.html';</script>";
    } else {
        echo "<script>alert('Contraseña incorrecta'); window.location='index.html';</script>";
    }
} else {
    echo "<script>alert('Usuario no encontrado'); window.location='index.html';</script>";
}

$conn->close();
?>