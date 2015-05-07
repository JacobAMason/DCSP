<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>High Scores</title>
    <link rel="stylesheet" type="text/css" href="LevelsCompleted.css">
    </head>

    
    <body>
    	<div id="container">
            
            <div id = "Title"> 
                <img class="logoImg" alt="logoImg" src="logo.png"/>
            	
            	<div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                 <li><a href="loginSuccess.php">Home</a></li> 
                                 <li><a href="accountInfoPHP.php">Account Info</a></li>
                                 <li><a href="HighScores.php">High Scores</a></li>
                                 <li><a href="FAQ.php">FAQ</a></li>
                                 <li><a href="SubmitAnIdea.php">Submit an idea</a></li>                      
                                 <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div> 

            <div id="Levels">
                <div id="words">
                Levels Completed
                </div>
            </div>
            
            <table><tr><th>Level Number</th><th>Time</th></tr>
<?PHP 
//include 'Logging.php';
//$log = new Logging();

$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$uName = "'".$_SESSION["username"]."'";

$query = "SELECT level, score FROM Scores, User WHERE Scores.ID = User.ID AND User.username = $uName ORDER BY level ASC";
$highscoreRows = $sql->query($query);

while($entry = $highscoreRows->fetch_assoc())
{
    $minutes = 0;
    // This line will only need to be run once

    $level = $entry[level];
    $score = $entry[score];

    while($score >= 60) {
        $minutes += 1;
        $score -= 60;
    }
    $score = round($score, 2);

    if($minutes > 0) {
        echo "<tr>".
        "<td>".$level."</td>".
        "<td>".$minutes . " min   " . $score. " sec" ."</td>".
        "</tr>";
    }

    else {
        echo "<tr>".
        "<td>".$level."</td>".
        "<td>".$score." sec" . "</td>".
        "</tr>";
     }

}

//$log->lclose();
?>
            </table> 
            
            
        </div>
    </body>
</html>