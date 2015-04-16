<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>High Scores</title>
    <link rel="stylesheet" type="text/css" href="DownloadCSS.css">
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

            <div id="download">
                <div id="words">
                Download Game
                </div>
            </div>
            
            <div id="links">
                <a href="#">Click here to download APK for Android Game</a> <br/>
                <a href="#">Click here to download Desktop Game </a>
            </div>
        </div>
    </body>
</html>
