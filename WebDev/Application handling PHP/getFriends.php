<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$friender = $_POST['friender'];

$query = "SELECT Friendee FROM Friends WHERE Friender='$friender'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
	$jsonEntries = array();
	$jsonReply[result] = "Success";
	$log->lwrite("Success: " . $friender);
	
	while($entry = $row->fetch_assoc()) {
		$friendee = $entry['Friendee'];
		$query = "SELECT username FROM User WHERE ID='$friendee'";
		$usernameRow = $sql->query($query);
		if(mysqli_num_rows($usernameRow)) {
			$usernameEntry = $usernameRow->fetch_assoc();
			$username = $usernameEntry['username'];
	    	$jsonEntries[] = $username;
	    }
	}
	$jsonReply[friendResultsArray] = $jsonEntries;
	
} else {
	$jsonReply[result] = "Null";
	$log->lwrite("Found no Friends for ID: " . $friender);
}

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>