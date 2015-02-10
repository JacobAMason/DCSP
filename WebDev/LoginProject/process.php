<!DOCTYPE HTML>


<html>
<?php 
//Start session
session_start();

//Source: http://mrbool.com/how-to-create-a-login-page-with-php-and-mysql/28656
//$username = $_POST['user'];
//$password = $_POST['pass'];


$DB_HOST = 'localhost';
$DB_NAME = 'login_project_db';
$DB_USER = 'root';
$DB_PASSWORD = '';

//create connection to database
$conn = new PDO("mysql:host=$DB_HOST;dbname=$DB_NAME", $DB_USER, $DB_PASSWORD);
$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);


 
//get username and password
    if(!empty($_POST['user'])){         //Checking that they entered something
        
        //Setting up query
        $query = $conn->prepare("SELECT * FROM username WHERE userName = ? AND pass = ?");
        $query->bindParam(1, $_POST['user']);
        $query->bindParam(2,$_POST['pass']);
        
        //Selecting from database using PDO(secure)
        $query->execute();
     
        //If it found the input username with correct password in the database
        if ($query->fetchAll()){
            $_SESSION['user'] = $_POST['user'];
            echo "Welcome, ". $_SESSION['user'] . ".<br>"; ?>
    
            <form action="logOut.php" method = "POST">
                <input type = "submit" value = "Logout"/> 
            </form>
        
        <?php       
   }
        else{
            echo "Incorrect username of password";
        }
   }
   
 function signIn(){
    session.start();
 }
 
   if(isset($_POST['submit']))
{
	signIn();
}
    
?> </html>