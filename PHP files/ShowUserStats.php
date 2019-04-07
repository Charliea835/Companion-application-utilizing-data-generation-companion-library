<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT

/*
This PHP file shows the users statistics based on the username and password that they enter to login.
*/


if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php'; //include connection to database.
	showData();
}
function showData() //create function to show the users data.
{
	global $connect;
	$game = $_POST['game'];
	$username = $_POST['username']; //get username and passwords from HTTP request.
    $password = $_POST['password'];
    
    if($game=="TPS") //if game is TPS
        {
            //get users stats for that game
        	$query = "SELECT kills,bulletsFired,bulletsHit,bulletsMissed,score,hitAccuracy,timePlayed,scorePerMinute FROM TPS_STATS WHERE username = '$username' AND password='$password'";
        	//var_dump($query);
        	$result = mysqli_query($connect, $query); //connect to database and execute query
        	$number_of_rows = mysqli_num_rows($result);
        	
        	$temp_array  = array();
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row;
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array)); //echo result back labelled data in JSON format.
        	mysqli_close($connect);
        }

	else if($game=='2DS')//if game is 2ds
        {
            //select stats for that game
        	$query = "SELECT kills,bulletsFired,bulletsHit,bulletsMissed,score,hitAccuracy,timePlayed,scorePerMinute FROM 2DS_STATS WHERE username = '$username' AND password='$password'";
        
        	$result = mysqli_query($connect, $query); //connect to database and execute query
        	$number_of_rows = mysqli_num_rows($result);
        	
        	$temp_array  = array();
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row;
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array));
        	mysqli_close($connect);//close the connection to the database.
        }
}
?>