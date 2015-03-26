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
        $message = "<h1>Login Succesful</h1><br>". 
        "<a href= http://pluto.cse.msstate.edu/~dcsp01/index.html>Click Here to return to the main page</a>";
        
} else {
    $response = "Fail: on username: '" . $username . "' and password: '" . $password . "'";
    $message = "<h1>Login Failed</h1><br>" .
        "<a href= http://pluto.cse.msstate.edu/~dcsp01/index.html>Click Here to return to Login</a>";
}

echo $message;
$log->lwrite($response);
$log->lclose();    
?>
</body>
</html>