<?php
//getting user values
$username=$_POST['username'];

//an array of response
$output = array();
//requires database connection

include ('config.php');

$conn=$dbh->prepare("SELECT user_id FROM users WHERE username=?");

$conn->bindParam(1,$username);

$conn->execute();
if($conn->rowCount() == 0){
$output['error'] = true;
$output['message'] = "Error";
}

if($conn->rowCount() !==0){
$results=$conn->fetch(PDO::FETCH_OBJ);
//we get the user_id
$userID = $results->user_id;
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
	$devices_logged_in = $devices_logged_in - 1;
	$conn3->bindParam(1,$userID);
	$conn3->bindParam(2,$devices_logged_in);
	$conn3->execute();	
	//$output['devices_logged_in'] = $devices_logged_in;

	if($conn3->rowCount() == 0)
			{
			$output['error'] = true;
			$output['message2'] = "Err";
			}
			elseif($conn3->rowCount() !==0){
			$output['error'] = false;
			$output['devices_logged_in'] = $devices_logged_in;
			
			
}}
echo json_encode($output);

?>
