<?PHP
session_start();
if(!isset($_SESSION['username'])) {
    header('Location: index.html');
}
?>
<html>
 
<head>
    <title>Security Question </title>
    <link rel="stylesheet" type="text/css" href="SecurityQuestion.css">
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
                                <li><a href="loginSuccess.php">Home</a></li>
                                <li><a href="accountInfoPHP.php">Account Info</a></li>
                                <li><a href="HighScores.php">High Scores</a></li>
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="FAQ.php">FAQ</a></li>
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
        
    <div id = "center">   
        <form class = "submit" action="QuestionSubmit.php" method="POST">
            <textarea rows="5" required name = "question" placeholder="Question Here"></textarea> 
            <br/>
            <br/>
            
            <div id = "answer">
                <input type = "text" name = "answer" placeholder="Answer Here" style = "margin-top: 10px;"/>
            </div>
            <br/>

            <div id = "submit">
                <input type = "submit" value = "Submit"/>
            </div>
        </form>
    </div>
        
    </div>
</body>

    
</html>