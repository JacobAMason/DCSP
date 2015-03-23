<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);
$jsonReply = array();

$ID = $_POST['ID'];
$score = $_POST['score'];
$level = $_POST['level'];
$seed = $_POST['seed'];
$toID = $_POST['toID'];

$query = "INSERT IGNORE INTO Challenges SET ID='$toID', FromID='$ID', ChallengeSeed='$seed', Level='$level', Score='$score'";
$row = $sql->query($query);

if($row) {
	$entry = $row->fetch_assoc();
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