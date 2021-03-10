<?php
//getting user values
$username=$_POST['username'];
$password=$_POST['password'];

//$username = "zack";
//$password = "123";


//an array of response
$output = array();
//requires database connection

include 'config.php';
include 'functions.php';

//checking if username exit
$conn=$dbh->prepare("SELECT * FROM users WHERE username=?");
//$pass=md5($password);
$conn->bindParam(1,$username);
$conn->execute();
if($conn->rowCount() == 0){
$output['error'] = true;
$output['message'] = "Username does not exist";
}

//checking password correctness
if($conn->rowCount() !==0){
$results=$conn->fetch(PDO::FETCH_OBJ);
//we get both the username and password
$username=$results->username;
$userID = $results->user_id;

$passwordHashDB=$results->password;
$salt = $results->salt;
//Validate the password
if(password_verify(concatPasswordWithSalt($password,$salt),$passwordHashDB)){
$output['error'] = false;
$output['message'] = "login sucessful";
$output['username'] = $username;
//echo $userID;

//inserting into logs table
	//$devices_logged_in = 0;
	$conn2=$dbh->prepare("SELECT devices_logged_in FROM user_logs WHERE user_id=? ORDER BY log_id DESC LIMIT 1");
	$conn2->bindParam(1,$userID);
	$conn2->execute();

	if($conn2->rowCount() !==0){
		$results2=$conn2->fetch(PDO::FETCH_OBJ);
		//we get both the username and password
		$devices_logged_in=$results2->devices_logged_in;
		//echo $userID;
		//$output['loggedInDevices'] = $devices_logged_in;
	}
	else{
		$devices_logged_in= 0 ;
		//$output['loggedInDevices'] = $devices_logged_in;
	}

	$conn3=$dbh->prepare("INSERT INTO user_logs (user_id,devices_logged_in) VALUES (?,?)");
	$devices_logged_in = $devices_logged_in + 1;
	$conn3->bindParam(1,$userID);
	$conn3->bindParam(2,$devices_logged_in);
	$conn3->execute();	
	//$output['devices_logged_in'] = $devices_logged_in;

	if($conn3->rowCount() == 0)
			{
			//$output['error'] = true;
			$output['message2'] = "Err";
			}
			elseif($conn3->rowCount() !==0){
			//$output['error'] = true;
			$output['devices_logged_in'] = $devices_logged_in;

			
			
			
}}else{
$output['error'] = true;
$output['message'] = "Wrong Password";
}

}
echo json_encode($output);

?>

