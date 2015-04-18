<?PHP
session_start();
?>
<!DOCTYPE HTML>


<html>
<head>
<title>Login Confirmation</title>
<style>
body{
	background-color:black;
}
a, h1{
	text-align:center;
	color: white;
	display: block;
	margin: 0 auto;
}
</style>
</head>
<body>
<?PHP

include 'Logging.php';
$log = new Logging();

$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$username = $_POST['user'];
$password = $_POST['pass'];
$MD5passwd = MD5($password);

$row = $sql->query("SELECT Name FROM User WHERE username='$username' AND password='$MD5passwd'");
$result = $row->fetch_object(); 
$_SESSION["username"] = $username;
$_SESSION["name"] = $result->Name;

if(mysqli_num_rows($row)) {
    $entry = $row->fetch_assoc();
	$response = "Success: " . $entry['Name'];
        $message = "<h1>Login Succesful</h1><br>". 
        "<a href= loginSuccess.php>Click here to return to the main page</a>";
        
} else {
    $response = "Fail: on username: '" . $username . "' and password: '" . $password . "'";
    $message = "<h1>Login Failed</h1><br>" .
        "<a href= index.html>Click here to return to login</a>";
}

echo $message;
$log->lwrite($response);
$log->lclose();    
?>
</body>
</html>