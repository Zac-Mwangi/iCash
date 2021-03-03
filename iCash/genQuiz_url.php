<?php

//getting user values
$username = $_POST['username'];

//array of responses
$output=array();

//require database and functions
include ('config.php');

//checking if username exists
$conn=$dbh->prepare('SELECT username,securityQuestion FROM users WHERE username=?');
$conn->bindParam(1,$username);
$conn->execute();

//results
if($conn->rowCount()==0){
$output['error'] = true;
$output['message'] = "username does not Exists. try again";
$output['securityQuiz'] = "";
}else{
$results=$conn->fetch(PDO::FETCH_OBJ);
$theeQuiz=$results->securityQuestion;
$output['error'] = false;
$output['message'] = "Data found";
$output['securityQuiz'] = $theeQuiz;
}	
echo json_encode($output);
?>