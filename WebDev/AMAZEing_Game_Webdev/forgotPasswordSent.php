<html>
    <head>
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
$query = "SELECT password FROM User WHERE username='$username'";
$row = $sql->query($query);

if(!mysqli_num_rows($row)) {
    $message = "<h1>Incorrect username</h1>";
    $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/forgotPassword.php>Re-enter username</a>";
}
else {
    $message = "<h1>Email sent Succesfully</h1>";
    $link = "<a href = http://pluto.cse.msstate.edu/~dcsp01/index.html>Home page</a>";
}

echo $message;
echo $link;
?>
    </body>
        
</html>