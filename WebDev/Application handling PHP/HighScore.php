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
$highscoreRows = $sql->query($query);
$jsonReply = array();

while($entry = $highscoreRows->fetch_assoc())
{
    $level = $entry['level'];
    $ID = $entry['ID'];
	$query = "SELECT username FROM User WHERE ID='$ID'";
	$rows = $sql->query($query);
	
	if(mysqli_num_rows($rows)) {
		$user = $rows->fetch_assoc();
		$username = $user['username'];
		$log->lwrite("Getting score for user ".$username);
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

	$jsonReply[$username] = $score;
}


header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>