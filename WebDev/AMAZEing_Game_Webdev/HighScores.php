<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>High Scores</title>
    <link rel="stylesheet" type="text/css" href="HighScore.css">
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
                                <?PHP if(isset($_SESSION["username"])) {?> <li><a href="loginSuccess.php">Home</a></li> <li><a href="#">Account Info</a></li>
                                <?PHP } else {?> <li><a href="index.html">Home</a></li> <?PHP }?>
                                <li><a href="#">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <?PHP if(isset($_SESSION["username"])) {?>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <?PHP } ?>
                                <li><a href="#">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div> 

            <div id="highscore">
                <div id="words">
                HIGH SCORES
                </div>
            </div>

            <table><tr><th>Level Number</th><th>Username</th><th>Time</th></tr>
<?PHP 
//include 'Logging.php';
//$log = new Logging();

$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$query = "SELECT * FROM HighScores";
$highscoreRows = $sql->query($query);

while($entry = $highscoreRows->fetch_assoc())
{
    // This line will only need to be run once

    $level = $entry[level];
    $ID = $entry[ID];
    $query = "SELECT username FROM User WHERE ID='$ID'";
    $rows = $sql->query($query);
    
    if(mysqli_num_rows($rows)) {
        $user = $rows->fetch_assoc();
        $username = $user[username];
        //$log->lwrite("Getting score for user ".$username);
    } else {
        //$log->lwrite("Fail: There is no matching entry in User for ID ". $ID);
    }

    $query = "SELECT score FROM Scores WHERE ID='$ID' AND level='$level'";
    $rows = $sql->query($query);
    
    if(mysqli_num_rows($rows)) {
        $userEntry = $rows->fetch_assoc();
        $score = $userEntry[score];
    } else {
        //$log->lwrite("Fail: There is no matching entry in Scores for ID ". $ID);
    }

    while($score >= 60) {
        $minutes += 1;
        $score -= 60;
    }
    $score = round($score, 2);

    if($minutes > 0) {
        echo "<tr>".
        "<td>".$level."</td>".
        "<td>".$username."</td>".
        "<td>".$minutes . " min   " . $score. " sec" ."</td>".
        "</tr>";
    }

    else {
        echo "<tr>".
        "<td>".$level."</td>".
        "<td>".$username."</td>".
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