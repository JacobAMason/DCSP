<!DOCTYPE HTML>


<html>
    <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    
    <body>
    	<div id="container">
            <img class="mazeImg" alt="mazeImg" src="http://pluto.cse.msstate.edu/~aes421/Maze.PNG"/>
            
            <div id = "Title"> 
                <img class="logoImg" alt="logoImg" src="http://pluto.cse.msstate.edu/~th739/lab5/logo.PNG"/>
            </div>
            
            <form class = "login" action="process.php" method="POST">
            	username: <input type = "text" name = "user"/> &nbsp; &nbsp;
            	password: <input type = "text" name = "pass"/>
            	<input type = "submit" value = "Login"/>
            </form>
        </div>  
    </body>
</html>
      