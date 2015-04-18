<?php
session_start();
if(!isset($_SESSION['username'])) {
    header('Location: index.html');
}
$_SESSION['ChangePass'] = true;
?>

<html>

<head>
    <title>Account Info</title>
    <link rel="stylesheet" type="text/css" href="accountInfoCSS.css">
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
                                <li><a href="HighScores.php">High Scores</a></li>
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

        <div id = "Info">
	        <div id = "GameInfo">
	        	<h3>Game Info</h3>
	                <li><a href="LevelsCompleted.php">Levels Completed</a></li> 
	        </div>

	        <div id = "Security">
	        	<h3>Sign-In and Security</h3>
	            <li><a href="SecurityQuestion.php">Set up Security Question</a></li> 
	            <li><a href="forgotPassword.php">Change Password</a></li>
                    <li><a href="DeleteAccount.php">Delete Account</a></li>
	        </div>
    	</div>
    </div>
</body>    
</html>

