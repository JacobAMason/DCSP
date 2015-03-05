<?PHP
include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['username'];
$password = $_POST['password'];
$MD5passwd = MD5($password);

$query = "SELECT * FROM User WHERE username='$username' AND password='$MD5passwd'";
$result = $sql->query($query);

if(mysqli_num_rows($result)) {
    $entry = $result->fetch_assoc();    
    $response = "Login Success: " . $entry['firstname'];
} else {
    $response = "Login Fail: on username: '" . $username . "' and password: '" . $MD5passwd . "'";
}

$response = "Received: " . $response;
$log->lwrite($response);

header('Content-Type: application/json');
echo json_encode($response);

$log->lclose();
?>