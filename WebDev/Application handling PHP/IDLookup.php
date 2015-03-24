<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$ID = $_POST['ID'];

$query = "SELECT username FROM User WHERE ID='$ID'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $jsonReply = $entry;
    $jsonReply['result'] = "Success";
	$response = "Success: " . $entry['username'];
} else {
	$jsonReply['result'] = "Fail";
    $response = "Fail: on ID: '" . $ID . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>