<?php
session_start();

if(!isset($_SESSION['ChangePass'])) {
session_unset();
session_destroy();
}
?>
<html>
 <title>Change Password</title>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>   
    
<body>
    <div id="container">
         <div id="Title">
             <img class="logoImg" alt="logoImg" src="logo.png"/>
             <div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                <?PHP if(isset($_SESSION["ChangePass"])) {?> 
                                    <li><a href="loginSuccess.php">Home</a></li> 
                                    <li><a href="accountInfoPHP.php">Account Info</a></li>
                                <?PHP } 
                                else {?> 
                                    <li><a href="index.html">Home</a></li> 
                                <?PHP }?>
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="FAQ.php">FAQ</a></li>
                                <?PHP if(isset($_SESSION["ChangePass"])) {?>
                                    <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <?PHP } ?>
                                <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

    <form action= "forgotPasswordSent.php" method= "POST">
        <div id ="username" style = "padding-top: 200px; color: #FFFFFF; margin:0;">
                Please enter your username: <input type = "text" name = "username" placeholder="Username"/> <br/>
        </div>
        
        <div class= "Button" style = "padding-top: 5px; padding-left: 300px; margin: 0;">
            <input type = "submit" value = "Submit" style = "color: black; margin:0;"/>
        </div> 
    </form>
    </div>
</body>
</html>