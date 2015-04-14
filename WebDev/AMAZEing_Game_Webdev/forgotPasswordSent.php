<?PHP
session_start();
?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Forgot Password</title>
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
$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);        

$username = $_POST['username'];

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);

if(!mysqli_num_rows($row)) {
    $message = "<h1>Incorrect username</h1>";
    $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/forgotPassword.php>Re-enter username</a>";
    echo $message;
    die($link);
}
$result = $row->fetch_object();
$userID = $result->ID;
echo "<h1>" .$userID. "</h1>"
$query = "SELECT Question, Answer FROM Question WHERE ID='$userID'";
$row = $sql->query($query);

if(!mysqli_num_rows($row)) {
    $message = "<h4>You do not have a security question to recover your password</h4>";
    $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/index.html>Home Page</a>";
    echo $message;
    die($link);
}

$result = $row->fetch_object();
$question = $resut->Question;
$_SESSION["answer"] = $result->Answer;

echo "<h1>" .$question. "</h1>";
?>
    
    </body>    
</html>