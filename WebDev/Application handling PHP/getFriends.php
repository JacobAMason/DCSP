<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$friender = $_POST['friender'];

$query = "SELECT Friendee FROM Friends WHERE Friender='$friender')";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
	$jsonEntries = array();
	$jsonReply[result] = "Success";
	$response = "Success: " . $ID;
	
	while($entry = $row->fetch_assoc()) {
	    $jsonEntries[] = $entry;
	}		
	$jsonReply[resultsArray] = $jsonEntries;
	
} else {
	$jsonReply[result] = "Null";
	$response = "Found no Friends for ID: " . $entry['ID'];
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>