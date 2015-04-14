<?PHP
session_start();
if(!isset($_SESSION)) {
    header('Location: index.html');
}
?>
﻿<!DOCTYPE HTML>
<html>
    <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    
    <body>
    	<div id="container">
            <img class="mazeImg" alt="mazeImg" src="http://pluto.cse.msstate.edu/~aes421/Maze.PNG"/>
            
            <div id = "Title"> 
                <img class="logoImg" alt="logoImg" src="logo.png"/>
            	
            	<div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                <li><a href="#">Account Info</a></li>
                                <li><a href="HighScoresHTML.html">High Scores</a></li>
                                <li><a href="#">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <li><a href="#">Donations :)</a></li>
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
