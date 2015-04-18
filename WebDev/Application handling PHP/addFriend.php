<?PHP
include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);
$jsonReply = array();

$friender = $_POST['friender'];
$friendee = $_POST['friendee'];


$query = "SELECT ID FROM User WHERE username='$friendee'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
    $friendeeID = $entry['ID'];
    
    $query = "INSERT INTO Friends (Friender, Friendee) VALUES ('$friender', '$friendeeID')";
    $row = $sql->query($query);


    if($row) {
        $jsonReply[result] = "Success";
        $response = "Success: " . $friender;
    } else {
        $jsonReply[result] = "Duplicate";
        $response = "Fail: Already friends with " . $friendee;
    }
} else {
    $jsonReply[result] = "NotFound";
    $response = "Fail: Couldn't find user '" . $friendee . "'";
}


$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($jsonReply, JSON_NUMERIC_CHECK);

$log->lclose();
?>