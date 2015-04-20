<?php
session_start();
?>
<html>
    <head>
        <title>Delete Users</title>
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
<?php
$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$delete = $_POST['checkbox'];
$result;
//Then do what you want with the selected items://
foreach ($delete as $id) {
    $query="DELETE FROM User WHERE ID = '$id'";
    $result= $sql->query($query);
}

if (!$result) {
	echo "<h1>Delete Users Failed!</h1><br>"; 
    echo'<a href = "listUsers.php">Return to User List</a>';
} 
else {
	echo "<h1>User(s) Deleted!</h1><br>"; 
    echo'<a href = "listUsers.php">Return to User List</a>';
}
?>
	</body>
</html>
