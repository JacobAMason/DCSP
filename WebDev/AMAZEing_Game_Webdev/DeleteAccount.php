<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>Delete Account</title>
    <link rel="stylesheet" type="text/css" href="DeleteAccount.css">
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
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="FAQ.php">FAQ</a></li>
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

            <div id="delete">
                <div id="words">
                Delete Account
                </div>
            </div>
            
            <form action= "deleteConfirmation.php" method= "POST">
                <div id ="userInfo">
                    Please enter your username: <input type = "text" name = "user" placeholder="Username"/> <br/>
                    Please enter your password:  <input type = "password" name = "pass" placeholder="Password"/> <br/><br/>
                </div>
        
                <div class = "Button">
                    <input type = "submit" value = "Submit" style = "color: black; margin:0;"/>
                </div> 
            </form>
            
            
        </div>
    </body>
</html>