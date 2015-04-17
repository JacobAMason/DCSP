<?php
session_start();
if(!isset($_SESSION['username'])) {
    header('Location: index.html');
}
?>
<html>
    <head>
        <title>Security Question</title>
        <style>
        body{
            background-color:black;
        }
        a, h1, h4{
            text-align:center;
            color: white;
            display: block;
            margin: 0 auto;
        }
        </style>
    </head>
    <body>
<?PHP
$dbusername = "dcsp01";
$dbpassword = "AimAtJ";
$dbhostname = "localhost";
$sql = new mysqli($dbhostname, $dbusername, $dbpassword, $dbusername);

$username = "'".$_SESSION['username']."'";
$question = $_POST['question'];
$answer = $_POST['answer'];

if(strlen($question) > 1000) {
	echo "<h1>Question Submission Failed!</h1><br>";
    echo "<h4>The question is longer than 1000 characters</h4><br>"; 
    die('<a href = "SecurityQuestion.php">Return to question page</a>');
}

if(strlen($answer) > 100 ) {
	echo "<h1>Question Submission Failed!</h1><br>";
    echo "<h4>The answer is longer than 100 characters</h4><br>"; 
    die('<a href = "SecurityQuestion.php">Return to question page</a>');
}

$query = "SELECT ID FROM User WHERE username = $username";
$row = $sql->query($query);
$result = $row->fetch_object();
$id = $result->ID;

$query = "SELECT * FROM Question WHERE ID = '$id'";
$row = $sql->query($query);

if(mysqli_num_rows($row)) {
    $query = "UPDATE Question SET question ='$question', answer = '$answer' WHERE ID ='$id'";

    if($sql->query($query) === TRUE) {
	echo "<h1>Question Updated!</h1><br>";
    echo '<a href = "accountInfoPHP.php">Return to account info page</a>';
	}

	else {
		echo "<h1>Question Not Updated!</h1><br>";
    	echo '<a href = "accountInfoPHP.php">Return to account info page</a>';
	}

} 
else {
	$query = "INSERT INTO Question (ID, Question, Answer) VALUES ('$id', '$question', '$answer')";
 
	if($sql->query($query) === TRUE) {
		echo "<h1>Question Added!</h1><br>";
    	echo '<a href = "accountInfoPHP.php">Return to account info page</a>';
	}

	else {
		echo "<h1>Question Not Added!</h1><br>";
    	echo '<a href = "accountInfoPHP.php">Return to account info page</a>';
	}
}

?>
	</body>
</html>