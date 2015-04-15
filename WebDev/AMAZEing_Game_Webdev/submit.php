<?php
session_start();
if(!isset($_SESSION["username"])) {
    header('Location: index.html');
}
session_start();
if(!isset($_SESSION["ideaSubmitted"])) {
    header('Location: loginSuccess.php');
}
?>
<html>
    <head>
    <title>Idea Submitted</title>
        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
                width: 100%;
                background-color: #000000;
            }

            html {
                display: table;
            }

            body {
                display: table-cell;
            }
        </style>
    </head>
    <body>
        <div style="display:table-cell; text-align:center;  vertical-align:middle; ">
            <a href="loginSuccess.php" style="color:white;">Thank&nbsp;you&nbsp;for&nbsp;submitting&nbsp;your&nbsp;idea!</a>
        </div>
</html>