<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT
/*
This PHP file posts all game data to the database.
*/



if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	sendData();
}
function sendData() #function to create the send the data to the database.
{
    	global $connect;
    	$database = $_POST['database'];
    	$username = $_POST["username"];	
    	$password = $_POST["password"];
    	$kills = $_POST["kills"];		#retrieve data by POST
    	$bulletsFired = $_POST["bulletsFired"];
    	$bulletsHit = $_POST["bulletsHit"];
    	$bulletsMissed = $_POST["bulletsMissed"];
    	$score= $_POST["score"];
        $hitAccuracy= $_POST["hitAccuracy"];
        $scorePerMinute= $_POST["scorePerMinute"];
        $timePlayed= $_POST["timePlayed"];
    	echo 'done';
    	if($database=='TPS_STATS') //if dataabse is TPS_STATS.
     {
         //insert all data into TPS_STATS database.
    	$query = "Insert into TPS_STATS(username,password,kills,bulletsFired,bulletsHit,bulletsMissed,score,hitAccuracy, timePlayed,scorePerMinute) values ('$username','$password','$kills','$bulletsFired','$bulletsHit','$bulletsMissed','$score','$hitAccuracy','$timePlayed','$scorePerMinute');";
    	
    	
    	mysqli_query($connect, $query) or die (mysqli_error($connect));
    	
    	
    	mysqli_close($connect);//close connection
     }
     
    else if ($database=='2DS_STATS')//if database is 2DS STATS
    
    {
        //insert all data to 2DS_STATS dataabse.
    	$query = "Insert into 2DS_STATS(username,password,kills,bulletsFired,bulletsHit,bulletsMissed,score,hitAccuracy, timePlayed,scorePerMinute) values ('$username','$password','$kills','$bulletsFired','$bulletsHit','$bulletsMissed','$score','$hitAccuracy','$timePlayed','$scorePerMinute');";
    	
    	
    	mysqli_query($connect, $query) or die (mysqli_error($connect));
    	
    	mysqli_close($connect); //close connection.
    }
}
?>