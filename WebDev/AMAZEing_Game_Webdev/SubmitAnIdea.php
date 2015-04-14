<?PHP
session_start();
if(!isset($_SESSION["username"])) {
    header('Location: index.html');
}

?>
<html>
 
<head>
    <link rel="stylesheet" type="text/css" href="SubmitAnIdeaCSS.css">
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
                                <?PHP if(isset($_SESSION["username"])) {?> <li><a href="loginSuccess.php">Home</a></li>
                                <?PHP } else {?> <li><a href="index.html">Home</a></li> <?PHP }?>
                                <li><a href="#">Account Info</a></li>
                                <li><a href="HighScoresHTML.html">High Scores</a></li>
                                <li><a href="#">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <li><a href="#">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    <form class = "submit" action="submit.php" method="POST">
        <textarea rows="20" required>
        </textarea> 
        <br/>
        <br/>
        <input type = "submit" value = "Submit idea"/>
    </form>
        
    </div>
</body>

    
</html>

