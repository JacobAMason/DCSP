<?php

if($_POST['pass'] != $_POST['passmatch']){
    echo "Passwords do not match";
}

else{
    $DB_HOST = 'localhost';
    $DB_NAME = 'login_project_db';
    $DB_USER = 'root';
    $DB_PASSWORD = '';

    //create connection to database
    $conn = new PDO("mysql:host=$DB_HOST;dbname=$DB_NAME", $DB_USER, $DB_PASSWORD);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    //get username and password
    if(!empty($_POST['user']) and !empty($_POST['pass'])){         //Checking that they entered something
        //Setting up query to check if username is taken
        $query = $conn->prepare("SELECT * FROM username WHERE userName = ?");
        $query->bindParam(1, $_POST['user']);
        
        //Selecting from database using PDO(secure)
        $query->execute();
     
        //If it found the input username with correct password in the database
        if ($query->fetchAll()){
            echo "Sorry, username already exists";
        }
        else{
            //Setting up query
            $query = $conn->prepare("INSERT INTO username(userName, pass) VALUES (?, ?)");
            $query->bindParam(1, $_POST['user']);
            $query->bindParam(2,$_POST['pass']);
        
     
         //Check for success
            if ($query->execute()){
                echo "Successful registration";
            }
            else{
                echo "Registration failure.  Please try again.";
            }
        }
   }
}
?>
