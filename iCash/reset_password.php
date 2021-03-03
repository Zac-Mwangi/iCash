<?php
$username = $_POST['username'];
$password = $_POST['password'];

//$username = "zack";
//$password = "123";
//array of responses
$output=array();

//require database and functions
include 'config.php';
include 'functions.php';


//Get a unique Salt
$salt = getSalt();
//Generate a unique password Hash
$passwordHash = password_hash(concatPasswordWithSalt($password,$salt),PASSWORD_DEFAULT);
//then insert

$conn=$dbh->prepare('UPDATE users SET password= ?,salt=? WHERE username = ?');

$conn->bindParam(1,$passwordHash);
$conn->bindParam(2,$salt);
$conn->bindParam(3,$username);
$conn->execute();

if($conn->rowCount() == 0)
{
$output['error'] = true;
$output['message'] = "Update failed please try again";
}
else{
$output['error'] = false;
$output['message'] = "Password Update Success";
$output['username'] = $username;
}
echo json_encode($output);
?>