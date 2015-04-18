<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$username = $_POST['username'];
$password = $_POST['password'];
$name = $_POST['name'];
$email = $_POST['email'];
$MD5passwd = MD5($password);

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);
$jsonReply = array();


if(mysqli_num_rows($row)) {
    $response = "Fail: username " . $username . " taken.";
    $jsonReply[result] = "usernameInUse";
} else {
	$query = "INSERT INTO User (username, password, Name, email) VALUES ('$username', '$MD5passwd', '$name', '$email')";
	if($sql->query($query)){
		$response = "Success: " . $name . " registered with username '" . $username . "' and email '" . $email . "'";
		$jsonReply[result] = "Success";
	} else {
		$response = "Fail: Registration failed.";
		$jsonReply[result] = "Fail";
	}
}


$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>