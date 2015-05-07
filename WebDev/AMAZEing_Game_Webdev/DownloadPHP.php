<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>Download</title>
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

            <div id="download">
                <div id="words">
                Download Game
                </div>
            </div>
            <br/><br/><br/>
            <div style="text-align: center;" id="links">
                <a href="downloads/An AMAZEing Game.apk">Click here to download An AMAZEing Game APK for Android</a> <br/><br/>
                <a href="downloads/An AMAZEing Game.jar">Click here to download An AMAZEing Game for Desktop</a> <br/><br/>
                <br/><br/>
                If you want to feed the developers, who are all broke college students, you may use the link below to give us however much you can spare.

                <form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
                    <input type="hidden" name="rm" value="2" />
                    <input type="hidden" name="cmd" value="_s-xclick">
                    <input type="hidden" name="hosted_button_id" value="596NPKRLDPRU8">
                    <input type="hidden" name="return" value="http://pluto.cse.msstate.edu/~dcsp01/DownloadPHP.php" />
                    <input type="hidden" name="cancel_return" value="http://pluto.cse.msstate.edu/~dcsp01/Donate.html" />
                    <br />
                    <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
                    <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
                </form>

            </div>
        </div>
    </body>
</html>
