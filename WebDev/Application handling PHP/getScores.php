<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$ID = $_POST['ID'];

$log->lwrite("ID:" . $ID . " Score: " . $score . " Level: " . $level);

$query = "SELECT level,score FROM Scores WHERE ID='$ID'";
$row = $sql->query($query);
$jsonReply = array();
$scoreTupleArray = array();

while ($entry = $row->fetch_assoc()) {
	$scoreTupleArray[] = array('score' => $entry['score'], 'level' => $entry['level']);
}
$jsonReply[scoreTupleArray] = $scoreTupleArray;

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>