<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['username'];
$password = $_POST['password'];
$MD5passwd = MD5($password);

$query = "SELECT Name FROM User WHERE username='$username' AND password='$MD5passwd'";
$row = $sql->query($query);
$jsonReply = array();

if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $jsonReply = $entry;
	$response = "Success: " . $entry['Name'];
} else {
    $response = "Fail: on username: '" . $username . "' and password: '" . $password . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>