<!DOCTYPE HTML>


<html>
<body>
<?PHP

include 'Logging.php';
$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['user'];
$password = $_POST['pass'];
$MD5passwd = MD5($password);

$row = $sql->query("SELECT Name FROM User WHERE username='$username' AND password='$MD5passwd'");

if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
	$response = "Success: " . $entry['Name'];
} else {
    $response = "Fail: on username: '" . $username . "' and password: '" . $password . "'";
}

$log->lwrite($response);

$log->lclose();    
?>
</body>
</html>