<?PHP
session_start();
if($_SESSION['username'] == "th739"
    || $_SESSION['username'] == 'Admin' 
    || $_SESSION['username'] == 'aes421' 
    || $_SESSION['username'] == 'Doctorwheauxdat') {
} else {
	header('Location: index.html');
}
?>
<!DOCTYPE HTML>
<html>
    <head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="listUsers.css">
    </head>

    
    <body>
    	<div id="container">
            
            <div id = "Title"> 
                <img class="logoImg" alt="logoImg" src="logo.png"/>
            	
            	<div class="menu-wrap">
                <nav class="menu">
                    <ul class="clearfix">
                        <li>
                            <a href="#"> Menu <span class="arrow">&#9660;</span></a>
 
                            <ul class="sub-menu">
                                <li><a href="loginSuccess.php">Home</a></li> 
                                <li><a href="accountInfoPHP.php">Account Info</a></li>
                                <li><a href="DownloadPHP.php">Download Game</a></li>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="SubmitAnIdea.php">Submit an idea</a></li>
                                <li><a href="Donations.php">Donations :)</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            </div> 

            <div id="users">
                <div id="words">
                User List
                </div>
            </div>

            <div id = "table">
            <form name="delete" method="post" action="deleteUsers.php">
            <table><tr><th>Delete?</th><th>Username</th></tr>

<?PHP 
$config = parse_ini_file('../../AMAZEing_Game_DBConfig.ini');

$sql = new mysqli($config['dbhostname'], $config['dbusername'], $config['dbpassword'], $config['dbname']);

$query = "SELECT * FROM User";
$userRows = $sql->query($query);

while($entry = $userRows->fetch_assoc())
{
    $user = $entry['username'];
    $ID = $entry['ID'];
    
    echo "<tr>".
    '<td><input name="checkbox['.$ID.']?>]" type="checkbox"  id="checkbox[]" value="'.$ID.'";></td>'.
    "<td>".$user."</td>".
    "</tr>";
}
?>
<tr>
<td colspan="5" align="left"><input name="delete" type="submit" id="delete" value="Delete Users"></td>
</tr>
            </table>
            </form> 
        </div>
        </div>  
    </body>
</html>