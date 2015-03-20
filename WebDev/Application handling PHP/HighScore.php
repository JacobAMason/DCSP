<?PHP
/*
Retrieve all the highscore information
*/
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$query = "SELECT * FROM HighScores";
$rows = $sql->query($query);
$jsonReply = array();

if(mysqli_num_rows($rows)) {
    $entry = $rows->fetch_assoc();
    foreach ($entry as $level=>$ID) {
    	$query = "SELECT Name FROM User WHERE ID='$ID'";
		$rows = $sql->query($query);
		
		if(mysqli_num_rows($rows)) {
			$user = $rows->fetch_assoc();
			$name = $user['Name'];
			$log->lwrite("Getting score for user ".$name);
		} else {
			$log->lwrite("Fail: There is no matching entry in User for ID ". $ID);
		}

		$query = "SELECT `$level` FROM Scores WHERE ID='$ID'";
		$rows = $sql->query($query);
		
		if(mysqli_num_rows($rows)) {
			$scores = $rows->fetch_assoc();
			$score = $scores[$level];
		} else {
			$log->lwrite("Fail: There is no matching entry in Scores for ID ". $ID);
		}


    	$jsonReply[$name] = $score;
    }
	$log->lwrite("Success");
} else {
    $log->lwrite("Fail");
}


header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>