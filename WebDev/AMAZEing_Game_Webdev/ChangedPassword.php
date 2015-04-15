<?PHP
session_start();
?>

<html>
    <head>
        <title>Password Change</title>
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
$userAnswer = $_POST['textAnswer'];
$answer = $_SESSION["answer"];
$username = $_SESSION["name"];
$password = $_POST['newPass'];
$repassword = $_POST['reNewPass'];
$regexPassword = "^(?!.*\\^)(?=.*[0-9]+)(?=.*[a-z]+)[A-z0-9!@#$&*]{6,30}$)";

if($userAnswer != $answer){
    echo "<h1>Password Change Failed!</h1><br>"; 
    echo "<h4>Your answer was incorrect</h4><br>";
    die('<a href = "forgotPassword.php">Return to reset password page</a>');
}

if($password != $repassword) {
    echo "<h1>Password Change Failed!</h1><br>";
    echo "<h4>Passwords do not match</h4><br>"; 
    die('<a href = "forgotPassword.php">Return to reset password page</a>');
    
}

if(preg_match($regexPassword, $password) === 0) {
    echo "<h1>Password Change Failed!</h1><br>";
    echo "<h4>Password does not specifications</h4><br>"; 
    die('<a href = "forgotPassword.php">Return to reset password page</a>');
    
}


$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$query = "UPDATE USER SET Password='$password' WHERE username='$username'";
echo "<h4>Hello</h4>";
if($sql->query($query)){
    echo "<h1>Password Changed!</h1><br>"; 
    echo '<a href = "index.php">Return to home page</a>';
}

?>
    </body>
</html>
