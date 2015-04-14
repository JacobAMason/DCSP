<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="GenericCSS.css">
    </head>
    <body>
        <div id="container">
           <a href="index.html">
                <div id="Title">
                    <img class="logoImg" alt="logoImg" src="logo.png"/>
                </div>
           </a>
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