<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);
$jsonReply = array();

$ID = $_POST['ID'];
$score = $_POST['score'];
$level = $_POST['level'];
$seed = $_POST['seed'];
$toUsername = $_POST['toUsername'];

$query = "SELECT ID FROM User WHERE username='$toUsername'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $toID = $entry['ID'];

    $query = "INSERT IGNORE INTO Challenges SET ID='$toID', FromID='$ID', ChallengeSeed='$seed', Level='$level', Score='$score'";
    $row = $sql->query($query);

    if($row) {
        $jsonReply[result] = "Success";
        $response = "Success: " . $ID;
    } else {
        $jsonReply[result] = "Fail";
        $response = "Fail: " . $ID;
    }

} else {
    $jsonReply[result] = "Fail";
    $response = "Failed to find ID for username: '" . $toUsername . "'";
}



$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>