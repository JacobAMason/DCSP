<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$ID = $_POST['ID'];

$log->lwrite("ID:" . $ID);

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