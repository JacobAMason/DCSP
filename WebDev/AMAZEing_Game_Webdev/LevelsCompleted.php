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
                                 <li><a href="#">FAQ</a></li>
                                 <li><a href="SubmitAnIdea.php">Submit an idea</a></li>                      
                                 <li><a href="#">Donations :)</a></li>
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
        </div>
    </body>
</html>