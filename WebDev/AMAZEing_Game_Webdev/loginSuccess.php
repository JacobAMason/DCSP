<?PHP
session_start();
if(!isset($_SESSION["name"])) {
    header('Location: index.html');
}
?>
﻿<!DOCTYPE HTML>
<html>
    <head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    
    <body>
    	<div id="container">
            <img class="mazeImg" alt="mazeImg" src="Maze.PNG"/>
            
            <div id = "Title"> 
                <img class="logoImg" alt="logoImg" src="logo.png"/>
            	
            	<div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                <?PHP if($_SESSION['username'] == 'th739' 
                                || $_SESSION['username'] == 'Admin' 
                                || $_SESSION['username'] == 'aes421' 
                                || $_SESSION['username'] == 'Doctorwheauxdat') { ?>
                                <li><a href="listUsers.php">Users</a></li>
                                <?PHP } ?>
                                <li><a href="accountInfoPHP.php">Account Info</a></li>
                                <li><a href="HighScores.php">High Scores</a></li>
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="FAQ.php">FAQ</a></li>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div>

            <div id = "welcome">
            	Welcome, <?PHP echo $_SESSION["name"];?>
            </div>
            
            <div id = "logoutID">
            	<form class = "logout" action = "logOut.php" method = "POST">
            		<input type = "submit" value = "Logout"/>
            	</form>
            </div>    
        </div>  
    </body>
</html>
