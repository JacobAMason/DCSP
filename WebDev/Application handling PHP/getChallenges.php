<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);
$jsonReply = array();

$ID = $_POST['ID'];

$query = "SELECT * FROM Challenges WHERE ID='$ID'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
	$jsonEntries = array();
	$jsonReply[result] = "Success";
	$response = "Success: " . $ID;
	
	while($entry = $row->fetch_assoc()) {

		$usernameQuery = "SELECT username FROM User WHERE ID='$entry[FromID]'";
        $userRow = $sql->query($usernameQuery);

        if(mysqli_num_rows($userRow)) {
            $userEntry = $userRow->fetch_assoc();
            $entry[challenger] = $userEntry[username];
        } else {
        	$jsonReply[result] = "Fail";
            $response = "Failed to find username for ID: '" . $ID . "'";
        }


	    $jsonEntries[] = $entry;
	}
	$jsonReply[challengeResultsArray] = $jsonEntries;

} else {
	$jsonReply[result] = "Null";
	$response = "Found no Challenges for ID: " . $ID;
}


$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>