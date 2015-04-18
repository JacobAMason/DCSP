<!DOCTYPE HTML>


<html>
<head>
<title>Registration Conformation</title>
<style>
body{
	background-color:black;
}
a, h1, h4{
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
$repassword = $_POST['passdup'];
$name = $_POST['name'];
$email = $_POST['email'];
$reEmail = $_POST['emaildup'];
$MD5passwd = MD5($password);

$regexUsername = "/^[0-9A-z_]{3,20}$/";
$regexName = "/^[A-z ',]{1,20}$/";
$regexPassword = "/^(?!.*\\^)(?=.*[0-9]+)(?=.*[a-z]+)[A-z0-9!@#$&*]{6,30}$/";
$regexEmail = "/^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,4}$/";

if($username == null || $password == null || $repassword == null || $name == null || $email == null || $reEmail == null) {
    echo "<h1>Registration Failed!</h1><br>"; 
    echo "<h4>Please make sure you fill out all the fields</h4><br>";
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');
}

if($password != $repassword) {
    echo "<h1>Registration Failed!</h1><br>";
    echo "<h4>Passwords do not match</h4><br>"; 
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');
    
}

if(preg_match($regexPassword, $password) === 0) {
    echo "<h1>Registration Failed!</h1><br>";
    echo "<h4>Password does not specifications</h4><br>"; 
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');
    
}

if($email != $reEmail) {
    echo "<h1>Registration Failed!</h1><br>";
    echo "<h4>Emails do not match</h4><br>";
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');
}

if(preg_match($regexEmail, $email) === 0) {
    echo "<h1>Registration Failed!</h1><br>";
    echo "<h4>Email does not specifications</h4><br>";
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');
}

if(preg_match($regexName, $name) === 0) {
    echo "<h1>Registration Failed!</h1><br>";
    echo "<h4>Name does not meet specifications</h4><br>";
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');        
}

if(preg_match($regexUsername, $username) === 0) {
    echo "<h1>Registration Failed!</h1><br>"; 
    echo "<h4>Username does not meet specifications</h4><br>";
    die('<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>');        
}

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);


if(mysqli_num_rows($row)) {
    $message = "<h1>Registration Failed!</h1>" . "Username already taken<br>";
    $link = '<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>';
    $response = "Fail: username " . $username . " taken.";
} 
else {
	$query = "INSERT INTO User (username, password, Name, email) VALUES ('$username', '$MD5passwd', '$name', '$email')";
	if($sql->query($query)){
		$response = "Success: " . $name . " registered with username '" . $username . "' and email '" . $email . "'";
                $message = "<h1>Registration Successful!</h1><br>";
                $link = '<a href = "http://pluto.cse.msstate.edu/~dcsp01/index.html">Click here to return to main page</a>';
	} else {
		$response = "Fail: Registration failed.";
                $message = "<h1>Registration Failed!</h1><br>";
                $link = '<a href = "http://pluto.cse.msstate.edu/~dcsp01/register.html">Return to registration page</a>';
	}
}

echo $message;
echo $link;
$log->lwrite($response);
$log->lclose();
?>
</body>
</html>