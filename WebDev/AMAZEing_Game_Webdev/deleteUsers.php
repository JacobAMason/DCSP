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

if($result){
    $message = "<h1>User(s) Deleted!</h1><br>";
    $link = '<a href = "listUsers.php">Return to User List</a>';
}
else {
    $message = "<h1>Delete Users Failed!</h1><br>"; 
    $link = '<a href = "listUsers.php">Return to User List</a>';
}

$query = "SELECT * FROM Scores AS s INNER JOIN (SELECT level, MIN(score) AS min FROM Scores GROUP BY level) as minScore ON minScore.level = s.level AND minScore.min = s.score ORDER BY s.level;";
$highscoreRows = $sql->query($query);        
if($highscoreRows){
    $message = "<h1>User(s) Deleted!</h1><br>";
    $link = '<a href = "listUsers.php">Return to User List</a>';
}
else {
    $message = "<h1>Delete Users Failed!</h1><br>"; 
    $link = '<a href = "listUsers.php">Return to User List</a>';
}

while($entry = $highscoreRows->fetch_assoc())
{
    $level = $entry['level'];
    $ID = $entry['ID'];
    
    $query = "UPDATE HighScores SET ID = '$ID' WHERE level = '$level'";
    $result = $sql->query($query);
    
    if($result){
        $message = "<h1>User(s) Deleted!</h1><br>";
        $link = '<a href = "listUsers.php">Return to User List</a>';
    }
    else {
        $message = "<h1>High Score Not Updated!</h1><br>"; 
        $link = '<a href = "listUsers.php">Return to User List</a>';
    }
    
}

echo $message;
echo $link;
?>
	</body>
</html>
