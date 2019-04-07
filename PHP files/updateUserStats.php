<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT


/*
This PHP file updates the users statistics that they get from the game only when they log in through the unity side.
*/

if($_SERVER['REQUEST_METHOD']=='POST')
{
    //retrieve all data from HTTP requests and assign them to the appropriate variables.
    $database = $_POST['database'];
    var_dump($database);
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
    
        require_once('connection.php'); #require connection.php to connect to database
        
    if($database=='TPS_STATS')//if the database is TPS_STATS
     {
        //update the users statistcs
    	$sql = "UPDATE TPS_STATS SET kills = '$kills', bulletsFired = '$bulletsFired', bulletsHit = '$bulletsHit', bulletsMissed = '$bulletsMissed', score = '$score', hitAccuracy = '$hitAccuracy',timePlayed = '$timePlayed', scorePerMinute = '$scorePerMinute' WHERE username = '$username' and password='$password';";
    	
    	$result = mysqli_query($connect,$sql);//execute query.
     }
     
    else if($database=='2DS_STATS')//if database is 2DS_STATS.
     {
        //Update stats for 2DS game.
    	$sql = "UPDATE 2DS_STATS SET kills = '$kills', bulletsFired = '$bulletsFired', bulletsHit = '$bulletsHit', bulletsMissed = '$bulletsMissed', score = '$score', hitAccuracy = '$hitAccuracy',timePlayed = '$timePlayed', scorePerMinute = '$scorePerMinute' WHERE username = '$username' and password='$password';";
    	
    	$result = mysqli_query($connect,$sql);//execute query.
     }
}

?>