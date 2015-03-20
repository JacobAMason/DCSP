<?PHP
include 'Logging.php';
$log = new Logging();

$fromApplication = $_POST['key1'];

$data = "Received: " . $fromApplication;
$log->lwrite($data);

//header('Content-Type: application/json');
echo json_encode($data);

$log->lclose();
?>