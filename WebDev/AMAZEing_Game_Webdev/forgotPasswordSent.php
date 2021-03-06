<?PHP
session_start();
?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="SubmitAnIdeaCSS.css">
        <title>Change Password</title>
        <style>
            body{
               background-color:black;
            }
            
            h1{
                text-align:center;
                color: white;
                display: block;
                margin: 0 auto;
            }

            h4{
                padding-top: 150px;
                width: 500px;
                text-align:center;
                color: white;
                display: block;
                margin: 0 auto;
            }

            #center{
                width: 500px;
                position: absolute;
                top: 0; left: 0; right: 0; bottom: 0;
                margin: auto;
            }

            #answer{
                width: 335px;
                float: right;
                clear: both;
            }

            #pass{
                padding-top: 50px;
                color: #FFFFFF;
                margin:0;
            }

            #repass{
                padding-top: 15px;
                color: #FFFFFF;
                margin:0;
            }

            #buttonAlign{
                width: 335px;
                float: right;
            }


        </style>
    </head>
    <body>
<?PHP
$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);        

if(isset($_SESSION['username'])) {
    $username = $_SESSION['username'];
}

else {
    $_SESSION['username'] = $_POST['username'];
    $username = $_SESSION['username'];
}

$query = "SELECT * FROM User WHERE username='$username'";
$row = $sql->query($query);

if(mysqli_num_rows($row) === 0) {
    $message = "<h1>Incorrect username</h1>";
    $link ='<a href = "http://pluto.cse.msstate.edu/~dcsp01/forgotPassword.php">Re-enter username</a>';
    echo $message;
    die($link);
}

$result = $row->fetch_object();
$userID = $result->ID;

$query = "SELECT Question, Answer FROM Question WHERE ID='$userID'";
$row = $sql->query($query);

if(mysqli_num_rows($row) === 0) {
    $message = "<h1>You do not have a security question to recover your password</h1>";
    $link = '<a href = "http://pluto.cse.msstate.edu/~dcsp01/index.html">Home Page</a>';
    echo $message;
    die($link);
}

$result = $row->fetch_object();
$question = $result->Question;
$_SESSION["answer"] = $result->Answer;
$_SESSION["username"] = $username;

?>
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
                                <li><a href="#">FAQ</a></li>
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

        <div id="SubmitAnIdea">
                <div id="words">
                Security Question
               </div>
       </div>

        <div id="center">
            <h4><?PHP echo $question; ?></h4>
            <form class = "submit" action="ChangedPassword.php" method="POST">
                <div id = "answer">
                    <input type = "text" name = "textAnswer" placeholder="Answer Here" style = "margin-top: 10px;"/>
                </div>
                <br>

                <div id ="pass"> 
                    New Password:
                    <div id = "buttonAlign"> 
                        <input type = "password" name = "newPass" class="button" placeholder="Password"/> <br/>
                    </div>
                </div>

                <div id ="repass">
                    Re-enter New Password: 
                    <div id = "buttonAlign">
                        <input type = "password" name = "reNewPass" class="button" placeholder="Retype Password"/> <br/>
                    </div>
                </div>
                
                <div id="button" style = "padding-top: 5px">
                    <input type = "submit" value = "Submit"/>
                </div>


            </form>
        </div>
    </div>
    </body>    
</html>