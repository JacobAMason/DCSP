<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$ID = $_POST['ID'];
$score = $_POST['score'];
$level = $_POST['level'];

$log->lwrite("ID:" . $ID . " Score: " . $score . " Level: " . $level);

$query = "SELECT score FROM Scores WHERE ID='$ID' AND level='$level'";
$row = $sql->query($query);
$jsonReply = array();

$checkGlobalHighScore = False;

if(mysqli_num_rows($row)) {

	$entry = $row->fetch_assoc();
	$previousScore = $entry['score'];
	
	if ($score < $previousScore) {
		// New personal high score
	    $query = "UPDATE Scores SET score='$score' WHERE ID='$ID' AND level='$level'";
		$row = $sql->query($query);
		if ($row) {
			$log->lwrite("Success: score inserted into existing row");
			$jsonReply[] = "New Personal Highscore " . $score . " over " . $previousScore;
			$checkGlobalHighScore = True;
		} else {
			$log->lwrite("Fail: existing row couldn't be updated");
			$jsonReply[] = "Fail: existing row couldn't be updated";
		}
	} else {
		$log->lwrite("Low score ignored");
		$jsonReply[] = "Low score ignored";
	}

} else {
	// No existing scores for this player exist. Let's make a new entry for this player.
	$query = "INSERT INTO Scores (ID, level, score) VALUES ('$ID', '$level', '$score')";
	if($sql->query($query)){
		$log->lwrite("Success: First score for this user");
		$jsonReply[] = "First score for this user";
		$checkGlobalHighScore = True;
	} else {
		$log->lwrite("Fail: Couldn't create first score for this user");
		$jsonReply[] = "Fail: Couldn't create first score for this user";
	}
}

if ($checkGlobalHighScore) {
	// Get the ID of the user who has the global high score at this level
	$query = "SELECT ID FROM HighScores WHERE level='$level'";
	$row = $sql->query($query);
	if(mysqli_num_rows($row)) {
		$entry = $row->fetch_assoc();
		$globalHSID = $entry['ID'];
		
		// Get the actual score
		$query = "SELECT score FROM Scores WHERE ID='$globalHSID' AND level='$level'";
		$row = $sql->query($query);
		
		// if the global high score ID is -1, there has yet to be a user with this highscore.
		if(mysqli_num_rows($row) || $globalHSID == -1) {
			$entry = $row->fetch_assoc();
			$globalHighScore = $entry['score'];
			
			if ($score < $globalHighScore) {
				// Update global high score to point at this user's ID
			    $query = "UPDATE HighScores SET ID='$ID' WHERE level='$level'";
				$row = $sql->query($query);
				if ($row) {
					$log->lwrite("Success: New global High Score");
					$jsonReply[] = "New Global Highscore";
				} else {
					$log->lwrite("Fail: existing Highscore row couldn't be updated");
					$jsonReply[] = "Fail: existing Highscore row couldn't be updated";
				}
			} else {
				$jsonReply[] = "Not a new global highscore";
			}
	
		} else {
			$jsonReply[] = "Couldn't retrive global highscore on ID " . $globalHSID;
		}
		
	} else {
		// New Highscore
		$query = "INSERT INTO Highscores (ID, level) VALUES ('$ID', '$level')";
		if($sql->query($query)){
			$log->lwrite("Success: First highscore for this level");
			$jsonReply[] = "Success: First highscore for this level";
			$checkGlobalHighScore = True;
		} else {
			$log->lwrite("Fail: Couldn't create first highscore for this level");
			$jsonReply[] = "Fail: Couldn't create first highscore for this level";
		}
	}
}


header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>