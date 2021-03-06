<!DOCTYPE html>
<html>

<head>
    <title>Register</title>
    <link rel="stylesheet" href="registerStyle.css" type="text/css"/>
</head>

<body>
    <div id="container">
        <div id = "homeLink">
            <a href="index.html"><img class="logoImg" alt="logoImg" src="http://pluto.cse.msstate.edu/~dcsp01/logoReg.png"/></a>
        </div>

        <form action = "registerResponse.php" method = "POST">
            <div id ="field">
                <div id = "buttonAlign">
                    Username: <input type = "text" name = "user" class="button" placeholder="Username"/> <br/>
                </div>

                <div id = "buttonAlign">
                    Password: <input type = "password" name = "pass" class="button" placeholder="Password"/> <br/>
                </div>
            
                <div id = "buttonAlign">
                    Re-enter password: <input type = "password" name = "passdup" class="button" placeholder="Retype Password"/> <br/>
                </div>
            
                <div id = "buttonAlign">
                    Name: <input type = "text" name = "name" class="button" placeholder="John Doe"/> <br/>
                </div>
            
                <div id = "buttonAlign">
                    Email: <input type = "text" name = "email" class="button" placeholder="Email@example.com"/> <br/>
                </div>
            
                <div id = "buttonAlign">
                    Re-enter Email: <input type = "text" name = "emaildup" class="button" placeholder="Email@example.com"/> <br/>
                </div>

                <div class = "register">
                    <input type = "submit" value = "Register"/>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
