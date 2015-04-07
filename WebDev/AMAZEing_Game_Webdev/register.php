<!DOCTYPE HTML>


<html>
<head>
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

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = $_POST['user'];
$password = $_POST['pass'];
$repassword = $_POST['passdup'];
$name = $_POST['name'];
$email = $_POST['email'];
$reEmail = $_POST['emaildup'];
$MD5passwd = MD5($password);

if($password != $repassword) {
    echo "<h1>Registration Failed!</h1>". "Passwords do not match<br>"; 
    die("<a href = http://pluto.cse.msstate.edu/~dcsp01/register.html>Return to registration page</a>");
    
}

if($username == null || $password == null || $repassword == null || $name == null || $email == null || $reEmail == null) {
    echo "<h1>Registration Failed!</h1><br>"; 
    die("<a href = http://pluto.cse.msstate.edu/~dcsp01/register.html>Return to registration page</a>");
}

if($email != $reEmail) {
    echo "<h1>Registration Failed!</h1>". "Emails do not match<br>";
    die("<a href = http://pluto.cse.msstate.edu/~dcsp01/register.html>Return to registration page</a>");
}

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $message = "<h1>Registration Failed!</h1>" . "Username already taken<br>";
    $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/register.html>Return to registration page</a>";
    $response = "Fail: username " . $username . " taken.";
} 
else {
	$query = "INSERT INTO User (username, password, Name, email) VALUES ('$username', '$MD5passwd', '$name', '$email')";
	if($sql->query($query)){
		$response = "Success: " . $name . " registered with username '" . $username . "' and email '" . $email . "'";
                $message = "<h1>Registration Successful!</h1><br>";
                $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/index.html>Click here to return to main page</a>";
	} else {
		$response = "Fail: Registration failed.";
                $message = "<h1>Registration Failed!</h1><br>";
                $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/register.html>Return to registration page</a>";
	}
}

echo $message;
echo $link;
$log->lwrite($response);
$log->lclose();
?>
</body>
</html>