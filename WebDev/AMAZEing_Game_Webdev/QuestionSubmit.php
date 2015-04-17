<?php
session_start();
if(!isset($_SESSION['username'])) {
    header('Location: index.html');
}

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$answer = $_POST['question'];

if(strlen($answer) > 1000) {
	echo "<h1>Question Submission Failed!</h1><br>";
    echo "<h4>The question is longer than 1000 characters</h4><br>"; 
    die('<a href = "SecurityQuestion.php">Return to question page</a>');
}

$username = $_SESSION['username'];

$query = "INSERT INTO Question SET password ='$password' WHERE username='$username'";

if($sql->query($query) === TRUE)
