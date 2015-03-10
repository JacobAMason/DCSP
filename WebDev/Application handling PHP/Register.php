<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['username'];
$password = $_POST['password'];
$name = $_POST['name']
$email = $_POST['email']
$MD5passwd = MD5($password);

$query = "SELECT * FROM User WHERE lower(username)='lower($username)'";
$row = $sql->query($query);
$jsonReply = array();

if(mysqli_num_rows($row)) {
    $response = "Fail: username " . $username . " taken.";
} else {
	$query = "INSERT INTO User ('username', 'password', 'Name', 'email', 'hasChallenge')
			   VALUES ('$username', '$password', '$name', '$email', 0)";
	if($sql->query($query)){
		$response = "Success: " . $name . " registered with username '" . $username . "' and email '" . $email . "'";
	} else {
		$response = "Fail: Registration failed.";
	}
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>