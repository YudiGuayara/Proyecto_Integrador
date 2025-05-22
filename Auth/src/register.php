<?php
$conn = new mysqli('localhost', 'root', '', 'login_system');
if ($conn->connect_error) { die('Error de conexión: ' . $conn->connect_error); }

$username = $_POST['username'];
$email = $_POST['email'];
$password = password_hash($_POST['password'], PASSWORD_BCRYPT);

$sql = "INSERT INTO users (username, email, password) VALUES ('$username', '$email', '$password')";

if ($conn->query($sql) === TRUE) {
    echo "<script>alert('Usuario registrado exitosamente'); window.location='index.html';</script>";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>