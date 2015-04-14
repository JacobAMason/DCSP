<?PHP
session_start();
?>
<html>
    <head>
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

$query = "SELECT Question, Answer FROM Question WHERE username='$username'";
$row = $sql->query($query);
$result = $row->fetch_object();
$question = $resut->Question;
$_SESSION["answer"] = $result->Answer;

echo $question;
?>
    <form action = "forgotPassCheck.php" method = "POST">
        <div id ="username" style = "padding-top: 335px; color: #FFFFFF; margin:0;">
            Please enter your Security Answer: <input type = "text" name = "username" placeholder="Answer"/> <br/>
        </div>

        <div class= "Button" style = "padding-top: 5px; padding-left: 300px; margin: 0;">
            <input type = "submit" value = "Submit" style = "color: black; margin:0;"/>
        </div> 
    </form>
    </body>
        
</html>