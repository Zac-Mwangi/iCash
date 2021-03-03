<?php 
//$con=mysqli_connect("localhost","root","","icash");
include 'config.php';
$sql="SELECT security_question FROM `security_questions`";

$r = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($r)){
    array_push($result,array(
        'security_questions'=>$row['security_question']
    ));
}

echo json_encode(array('result'=>$result));

mysqli_close($con);
?>