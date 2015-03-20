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

$query = "SELECT `$level` FROM Scores WHERE ID='$ID'";
$row = $sql->query($query);
$jsonReply = array();

$checkGlobalHighScore = False;

if(mysqli_num_rows($row)) {

	$entry = $row->fetch_assoc();
	$previousScore = $entry[$level];
	
	if ($score > $previousScore) {
		// New personal high score
	    $query = "UPDATE Scores SET `$level`='$score' WHERE ID='$ID'";
		$row = $sql->query($query);
		if ($row) {
			$response = "Success: score inserted into existing row";
			$jsonReply[] = "New Personal Highscore " . $score . "  " . $previousScore;
			$checkGlobalHighScore = True;
		} else {
			$response = "Fail: existing row couldn't be updated";
			$jsonReply[] = "Err1";
		}
	} else {
		$response = "Low score ignored";
		$jsonReply[] = "Low score ignored";
	}

} else {
	// No existing scores for this player exist. Let's make a new entry for this player.
	$query = "INSERT INTO Scores (ID, `$level`) VALUES ('$ID', '$score')";
	if($sql->query($query)){
		$response = "Success: New ID row created to insert score";
		$jsonReply[] = "First score for this user";
		$checkGlobalHighScore = True;
	} else {
		$response = "Fail: Couldn't create missing ID row";
		$jsonReply[] = "fail " . $level;
	}
}

if ($$checkGlobalHighScore) {
	// Get the ID of the user who has the global high score at this level
	$query = "SELECT `$level` FROM HighScores";
	$row = $sql->query($query);
	if(mysqli_num_rows($row)) {
		$entry = $row->fetch_assoc();
		$globalHSID = $entry[$level];
		
		// Get the actual score
		$query = "SELECT `$level` FROM Scores WHERE ID='$globalHSID'";
		$row = $sql->query($query);
		
		// if the global high score ID is -1, there has yet to be a user with this highscore.
		if(mysqli_num_rows($row) || $globalHSID == -1) {
			$entry = $row->fetch_assoc();
			$globalHighScore = $entry[$level];
			
			if ($score > $globalHighScore) {
				// Update global high score to point at this user's ID
			    $query = "UPDATE HighScores SET `$level`=$ID";
				$row = $sql->query($query);
				if ($row) {
					$response = "Success: New global High Score";
					$jsonReply[] = "New Global Highscore";
				} else {
					$response = "Fail: existing row couldn't be updated";
					$jsonReply[] = "Err2";
				}
			} else {
				$jsonReply[] = "Not a new global highscore";
			}
	
		} else {
			$jsonReply[] = "Couldn't retrive global highscore on ID " . $globalHSID;
		}
		
	} else {
		$jsonReply[] = "Couldn't retrive global highscore";
	}
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>