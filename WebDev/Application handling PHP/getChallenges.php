<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$ID = $_POST['ID'];

$query = "SELECT * FROM Challenges WHERE ID='$ID''";
$row = $sql->query($query);

if($row) {
	$entry = $row->fetch_assoc();
    $jsonReply = $entry;
	$response = "Success: " . $entry['ID'];
} else {
	$jsonReply['result'] = "Fail";
    $response = "Fail: on ID: '" . $ID  . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>