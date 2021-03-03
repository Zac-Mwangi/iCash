<?php

//getting user values
$username = $_POST['username'];
$securityQuestion = $_POST['secQuiz'];
$answer = $_POST['answer'];

//array of responses
$output=array();

//require database and functions
include ('config.php');
//checking if username exists
$conn=$dbh->prepare('SELECT * FROM users WHERE (username=? AND securityQuestion=? AND answer=?)');
$conn->bindParam(1,$username);
$conn->bindParam(2,$securityQuestion);
$conn->bindParam(3,$answer);
$conn->execute();

//results
if($conn->rowCount()==0){
$output['error'] = true;
$output['message'] = "data does not Exists. try again";
}else{
$results=$conn->fetch(PDO::FETCH_OBJ);
$username=$results->username;
$output['error'] = false;
$output['message'] = "Correct Data";
$output['username'] = $username;
}
echo json_encode($output);
?>