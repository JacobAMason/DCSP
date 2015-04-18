<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$username = $_POST['username'];
$password = $_POST['password'];
$MD5passwd = MD5($password);

$query = "SELECT Name,ID,username,email FROM User WHERE username='$username' AND password='$MD5passwd'";
$row = $sql->query($query);
$jsonReply = array();

if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $jsonReply = $entry;
    $jsonReply[result] = "Success";
	$response = "Success: " . $entry['Name'];
} else {
	$jsonReply[result] = "Fail";
    $response = "Fail: on username: '" . $username . "' and password: '" . $password . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>