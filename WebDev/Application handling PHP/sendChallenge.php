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
$seed = $_POST['seed'];
$toID = $_POST['toID'];


$query = "INSERT INTO Challenges (ID, FromID, ChallengeSeed, Level, Score) VALUES ('$toID', '$ID', '$seed', '$level', '$score')";
$row = $sql->query($query);
$jsonReply = array();

if($row) {
    $jsonReply['result'] = "Success";
	$response = "Success: " . $entry['ID'];
} else {
	$jsonReply['result'] = "Fail";
    $response = "Fail: on username: '" . $username . "'";
}

$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>