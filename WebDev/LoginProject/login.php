<!DOCTYPE HTML>


<html>
    <head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    </head>

    
    <body>
        <div id = "Sign-in">
            <fieldset style="width:30%"><legend>LOGIN HERE</legend>
                <form action="process.php" method = "POST">
                    username: <input type = "text" name = "user"/></br>
                    password: <input type = "password" name = "pass"/></br></br>
                    <input type = "submit" value = "Login!"/>
                </form>
            </fieldset>
            
            <form action = "registerHTML.php" method = "POST">
                <div id = "Register">
                    <input type = "submit" value = "Register now!"/>
                </div>
            </form>
            
      </div>
               
    </body>
</html>
      