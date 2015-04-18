<html>
    <head>
    	<title>Delete Account</title>
    	<link rel="stylesheet" type="text/css" href="deleteConfirmation.css">
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
session_start();

$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$username = $_SESSION['username'];
$query = "DELETE FROM User WHERE username = '$username'";

if($sql->query($query)){
    echo "<h1>Account Deleted!</h1><br>"; 
    echo '<a href = "index.html">Return to home page</a>';
}
?>
    </body>
</html>