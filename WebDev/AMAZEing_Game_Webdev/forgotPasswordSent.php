<?PHP
session_start();
?>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="SubmitAnIdeaCSS.css">
        <title>Forgot Password</title>
        <style>
            body{
               background-color:black;
            }
            
            a, h1{
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

            #pass{
                padding-top: 15px;
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
$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);        

$username = $_POST['username'];

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
    $message = "<h4>You do not have a security question to recover your password</h4>";
    $link = '<a href = "http://pluto.cse.msstate.edu/~dcsp01/index.html">Home Page</a>';
    echo $message;
    die($link);
}

$result = $row->fetch_object();
$question = $result->Question;
$_SESSION["answer"] = $result->Answer;
$_SESSION["name"] = $username;

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
                                <li><a href="index.html">Home</a></li>
                                <li><a href="HighScores.php">High Scores</a></li>
                                <li><a href="#">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="#">Donations :)</a></li>
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
                <input type = "text" name = "textAnswer" style = "margin-top: 10px"/>

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