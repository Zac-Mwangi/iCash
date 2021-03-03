<?php
$username=$_POST['username'];
$answer = $_POST['answer'];
$securityQuiz = $_POST['selected'];

//an array of response
$output = array();
//requires database connection

include ('config.php');

$conn1=$dbh->prepare("SELECT securityQuestion,answer FROM users WHERE username=?");
//$pass=md5($password);
$conn1->bindParam(1,$username);

$conn1->execute();
if($conn1->rowCount() == 0){
$output['error'] = true;
$output['message'] = "Error Username missing";
}else{
	$conn=$dbh->prepare("UPDATE users SET securityQuestion = ?, answer = ? WHERE username=?");
	$conn->bindParam(1,$securityQuiz);
	$conn->bindParam(2,$answer);
	$conn->bindParam(3,$username);
	$conn->execute();

	if($conn->rowCount() == 0){
		$output['error'] = true;
		$output['message'] = "Security Not Registered";
	}else {
		# code...
		$output['error'] = false;
		$output['message'] = "Security Updated Successfully";

	}}
echo json_encode($output);
?>
