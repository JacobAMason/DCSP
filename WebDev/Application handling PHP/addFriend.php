<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$friender = $_POST['friender'];
$friendee = $_POST['friendee']

$query = "INSERT INTO Friends (Friender, Friendee) VALUES ('$friender', '$friendee')";
$row = $sql->query($query);


if($row) {
    $jsonReply[result] = "Success";
	$response = "Success: " . $ID;
} else {
	$jsonReply[result] = "Fail";
    $response = "Fail: " . $ID;
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>