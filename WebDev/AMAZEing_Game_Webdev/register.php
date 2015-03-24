<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['user'];
$password = $_POST['pass'];
$name = $_POST['name'];
$email = $_POST['email'];
$MD5passwd = MD5($password);

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $response = "Fail: username " . $username . " taken.";
} 
else {
	$query = "INSERT INTO User (username, password, Name, email) VALUES ('$username', '$MD5passwd', '$name', '$email')";
	if($sql->query($query)){
		$response = "Success: " . $name . " registered with username '" . $username . "' and email '" . $email . "'";
	} else {
		$response = "Fail: Registration failed.";
	}
}


$log->lwrite($response);
$log->lclose();
?>