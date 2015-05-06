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

if($sql->query($query)) {
    $message = "<h1>Account Deleted!</h1><br>"; 
    $link = '<a href = "index.html">Return to home page</a>';
}
else {
    $message = "<h1>Account Not Deleted!</h1><br>";
    $link = '<a href = "index.html">Return to home page</a>';
}

$query = "SELECT * FROM Scores AS s INNER JOIN (SELECT level, MIN(score) AS min FROM Scores GROUP BY level) as minScore ON minScore.level = s.level AND minScore.min = s.score ORDER BY s.level;";
$highscoreRows = $sql->query($query);        
if($highscoreRows){
    $message = "<h1>Account Deleted!</h1><br>";
    $link = '<a href = "index.html">Return to Home Page</a>';
}
else {
    $message = "<h1>Delete Account Failed!</h1><br>"; 
    $link = '<a href = "index.html">Return to Home Page</a>';
}

while($entry = $highscoreRows->fetch_assoc())
{
    $level = $entry['level'];
    $ID = $entry['ID'];
    
    $query = "UPDATE HighScores SET ID = '$ID' WHERE level = '$level'";
    $result = $sql->query($query);    
}

echo $message;
echo $link;
?>
    </body>
</html>