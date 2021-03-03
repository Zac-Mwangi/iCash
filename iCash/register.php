<?php

//getting user values
$username = $_POST['username'];
$fullname = $_POST['fullname'];
$password = $_POST['password'];
$email = $_POST['email'];
$gender = $_POST['gender'];

//array of responses
$output=array();

//require database and functions
include ('config.php');
include 'functions.php';


//checking if username exists
$conn=$dbh->prepare('SELECT username FROM users WHERE username=?');
$conn->bindParam(1,$username);
$conn->execute();

//checking if username exists
$conn2=$dbh->prepare('SELECT email FROM users WHERE email=?');
$conn2->bindParam(1,$email);
$conn2->execute();

//results
if($conn->rowCount() !==0){
$output['error'] = true;
$output['message'] = "username Exists Please Login";
}elseif($conn2->rowCount() !==0){
$output['error'] = true;
$output['message'] = "Email Exists Please Login";
}else{

//Get a unique Salt
$salt = getSalt();
//Generate a unique password Hash
$passwordHash = password_hash(concatPasswordWithSalt($password,$salt),PASSWORD_DEFAULT);
//then insert


$conn=$dbh->prepare('INSERT INTO users (username,fullname,email,password,gender,salt) VALUES (?,?,?,?,?,?)');
//encrypting the password
//$pass=md5($password);
$conn->bindParam(1,$username);
$conn->bindParam(2,$fullname);
$conn->bindParam(3,$email);
$conn->bindParam(4,$passwordHash);
$conn->bindParam(5,$gender);
$conn->bindParam(6,$salt);

$conn->execute();
if($conn->rowCount() == 0)
{
$output['error'] = true;
$output['message'] = "Registration failed, Please try again";
}
elseif($conn->rowCount() !==0){
$output['error'] = false;
$output['message'] = "Succefully Registered";
$output['username']=$username;
}
}
echo json_encode($output);

?>

