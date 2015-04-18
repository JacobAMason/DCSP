<?PHP
/*
Retrieve all the highscore information
*/
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$query = "SELECT * FROM HighScores";
$highscoreRows = $sql->query($query);
$jsonReply = array();

while($entry = $highscoreRows->fetch_assoc())
{
	// This line will only need to be run once
	$jsonReply[result] = "Success";

    $level = $entry[level];
    $ID = $entry[ID];
	$query = "SELECT username FROM User WHERE ID='$ID'";
	$rows = $sql->query($query);
	
	if(mysqli_num_rows($rows)) {
		$user = $rows->fetch_assoc();
		$username = $user[username];
		$log->lwrite("Getting score for user ".$username);
	} else {
		$log->lwrite("Fail: There is no matching entry in User for ID ". $ID);
	}

	$query = "SELECT score FROM Scores WHERE ID='$ID' AND level='$level'";
	$rows = $sql->query($query);
	
	if(mysqli_num_rows($rows)) {
		$userEntry = $rows->fetch_assoc();
		$score = $userEntry[score];
	} else {
		$log->lwrite("Fail: There is no matching entry in Scores for ID ". $ID);
	}

	$jsonReply[$level] = array($username, $score);
}


header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>