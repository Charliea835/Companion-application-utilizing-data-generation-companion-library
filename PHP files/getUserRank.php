<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT


/*
This PHP file gets a users individual ranking in the leaderboard and returns it to the client.
*/

if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php';
	showRank();
}
function showRank() //create function to show the rank of the user.
{
	global $connect;
	$game = $_POST['game'];
	$username = $_POST['username'];
    $password = $_POST['password'];
    
    if($game=='TPS')//if the game is TPS.
    {
        
            //get the count of scores that are higher than the users and the count of scores that are the same as the users excluding the users own score, add them together and add an extra 1 to be sure other users dont get the same rank.
            
        	$query = "select 
                      username,
                      score,
                      (select count(*) from TPS_STATS where score > t.score) +
                      (select count(*) from TPS_STATS where score = t.score and username < t.username) + 1
                      rank
                      from TPS_STATS t where username='$username' and password = '$password';";
        	
        	$result = mysqli_query($connect, $query); //connect to database and execute query
        	$number_of_rows = mysqli_num_rows($result);
        	
        	$temp_array  = array(); //create array 
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row;
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array));//echo result back to client in JSON format.
        	mysqli_close($connect);//clsoe the connection.
    }
    else if($game=='2DS')//if the game is 2DS 
    {
        //get the count of scores that are higher than the users and the count of scores that are the same as the users excluding the users own score, add them together and add an extra 1 to be sure other users dont get the same rank.
        
        	$query = "select 
                      username,
                      score,
                      (select count(*) from 2DS_STATS where score > d.score) +
                      (select count(*) from 2DS_STATS where score = d.score and username < d.username) + 1
                      rank
                      from 2DS_STATS d where username='$username' and password = '$password';";
        	
        	$result = mysqli_query($connect, $query); //connect to database and execute query
        	$number_of_rows = mysqli_num_rows($result);
        	
        	$temp_array  = array();
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row;
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array));//echo result back to client in JSON format.
        	mysqli_close($connect);//close the connection.
    }
}
?>