<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$ID = $_POST['ID'];

$query = "SELECT * FROM Challenges WHERE ID='$ID'";
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
	$response = "Found no Challenges for ID: " . $ID;
}


$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>