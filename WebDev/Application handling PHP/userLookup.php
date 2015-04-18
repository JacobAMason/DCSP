<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);
$jsonReply = array();

$username = $_POST['username'];

$query = "SELECT ID FROM User WHERE username='$username'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $jsonReply = $entry;
    $jsonReply[result] = "Success";
	$response = "Success: " . $entry['ID'];
} else {
	$jsonReply[result] = "Fail";
    $response = "Fail: on username: '" . $username . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>