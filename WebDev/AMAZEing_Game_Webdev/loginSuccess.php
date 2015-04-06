<?PHP
session_start();
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
                <img class="logoImg" alt="logoImg" src="http://pluto.cse.msstate.edu/~th739/lab5/logo.png"/>
            	
            	<div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                <li><a href="#">Link 1</a></li>
                                <li><a href="#">Link 2</a></li>
                                <li><a href="#">Link 3</a></li>
                                <li><a href="#">Link 4</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div>

            <div id = "logoutID">
            	<form class = "logout" action = "logOut.php" method = "POST">
            		<input type = "submit" value = "Logout"/>
            	</form>
            </div>  
          
           
            
            <div id = "welcome">
            	Welcome, <?PHP echo $_SESSION["name"];?>
            </div>
            
            
            
        </div>  
    </body>
</html>
