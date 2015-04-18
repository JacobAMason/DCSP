<?PHP
session_start();
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>Delete Account</title>
    <link rel="stylesheet" type="text/css" href="deleteConfirmation.css">
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

            <div id="delete">
                <div id="words">
                Delete Account
                </div>
            </div>
            
            <?PHP
            $dbusername = "dcsp01";
            $dbpassword = "AimAtJ";
            $dbhostname = "localhost";
            $sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

            $username = $_POST['user'];
            $password = $_POST['pass'];
            $MD5passwd = MD5($password);

            $row = $sql->query("SELECT Name FROM User WHERE username='$username' AND password='$MD5passwd'");
            $result = $row->fetch_object(); 

            if(mysqli_num_rows($row)) { 
                    echo "<div id='Sure'> Are you sure you want to delete your account? </div>";

            } else {
                    echo "<div class='link'><a href='DeleteAccount.php'>Incorrect Password.&nbsp; Click here to return</a></div>";
            }   
            ?>
        </div>
    </body>
</html>