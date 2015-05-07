<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>FAQ</title>
    <link rel="stylesheet" type="text/css" href="FAQ.css">
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
                                <?PHP if(isset($_SESSION["username"])) {?> 
                                    <li><a href="loginSuccess.php">Home</a></li> 
                                    <li><a href="accountInfoPHP.php">Account Info</a></li>
                                <?PHP } 
                                else {?> 
                                    <li><a href="index.html">Home</a></li> 
                                 <?PHP }?>
                                 <li><a href="HighScores.php">High Scores</a></li>
                                 <li><a href="DownloadPHP.php">Download Game</a></li>
                                <?PHP if(isset($_SESSION["username"])) {?>
                                    <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <?PHP } ?>
                                    <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div> 

            <div id="FAQ">
                <div id="words">
                Frequently Asked Questions
                </div>
            </div>
            <br/><br/><br/>
            <div id="questions">
                <b> How do I download the game? </b> <br>
                &nbsp;&nbsp;&nbsp;&nbsp;Just click <a href="DownloadPHP.php"> here</a>! Or you can visit our Download Game page located in the menu bar. </br></br>
                <b> How do I play the game? </b> </br>
                &nbsp;&nbsp;&nbsp;&nbsp;The goal of this game is to make your way to the bottom right corner
                of the maze.  When playing on Android, the character will move towards the point on</br>
                &nbsp;&nbsp;&nbsp;&nbsp;the screen that you touch.  When playing on Desktop, the arrow keys or WASD will move the character in their 
                respective direction.  One can also use the </br>
                &nbsp;&nbsp;&nbsp;&nbsp;mouse and the character will move towards where you are clicking on the screen.  Enjoy! </br></br>
            </div>
        </div>
    </body>
</html>
