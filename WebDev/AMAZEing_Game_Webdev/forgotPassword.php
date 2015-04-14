<html>
 
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

    <form action= "forgotPasswordSent.php" method= "POST">
        <div id ="username" style = "padding-top: 300px; color: #FFFFFF; margin:0;">
                Please enter your username: <input type = "text" name = "username" placeholder="Username" style = "padding-left: 10px"/> <br/>
        </div>

        <div id ="password" style = "padding-top: 15px; color: #FFFFFF; margin:0;">
                Please enter your new password: <input type = "password" name = "username" placeholder="password"/> <br/>
        </div>

        <div id ="repassword" style = "padding-top: 15px; color: #FFFFFF; margin:0;">
                Please re-enter new password: <input type = "password" name = "username" placeholder="password" style = "padding-left: 10px"/> <br/>
        </div>
        
        <div class= "Button" style = "padding-top: 5px; padding-left: 300px; margin: 0;">
            <input type = "submit" value = "Submit" style = "color: black; margin:0;"/>
        </div> 
    </form>
    </div>
</body>
</html>