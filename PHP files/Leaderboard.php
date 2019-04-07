<?php

// Charlie Ansell 
// COMPANION APP PROJECT 
// 04/04/2019


/* This file processes all the data in the database and ranks users based on score and returns that to the client in the form of a JSON array
*/

if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php';
	createLeaderboard();
}
function createLeaderboard() //declare function to create leaderboard.
{
	global $connect;
	$game = $_POST['game'];
	$username = $_POST['username']; //retrieve data and store in variables.
    $password = $_POST['password'];
    
    //if the game is third person shooter.
    if($game=='TPS')
    {
        
        /*This query gets the username and score of the players and for the ranking of each
        it gets the total count of scores that are greater than each score for each player.
        once it gets that it then gets the amount of values that are the same as that score where the usernames are not different. finally we add an extra 1 to each players rank so if two players have the same score they will not have the same ranking.....just generally being placed underneath the first user it finds with that score.*/
        
        	$query = "select 
                      username,
                      score,
                      (select count(*) from TPS_STATS where score > t.score) +
                      (select count(*) from TPS_STATS where score = t.score and username < t.username) + 1
                      rank
                      from TPS_STATS t
                      order by rank, username
                      limit 100;";
        //	var_dump($query);
        	$result = mysqli_query($connect, $query); //connect to database and execute query.
        	$number_of_rows = mysqli_num_rows($result);
        	
        	$temp_array  = array();
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row;
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array));
        	mysqli_close($connect);
    }
    else if($game=='2DS') //if game is two dimensional shooter.
    {
        
        /*This query gets the username and score of the players and for the ranking of each
        it gets the total count of scores that are greater than each score for each player.
        once it gets that it then gets the amount of values that are the same as that score where the usernames are not different. finally we add an extra 1 to each players rank so if two players have the same score they will not have the same ranking.....just generally being placed underneath the first user it finds with that score.*/
        
        	$query = "select 
                      username,
                      score,
                      (select count(*) from 2DS_STATS where score > d.score) +
                      (select count(*) from 2DS_STATS where score = d.score and username < d.username) + 1
                      rank
                      from 2DS_STATS d
                      order by rank, username
                      limit 100;";
        //	var_dump($query);
        	$result = mysqli_query($connect, $query); //connect to database and execute query.
        	$number_of_rows = mysqli_num_rows($result); 
        	
        	$temp_array  = array(); //create array.
        	
        	if($number_of_rows > 0) {
        		while ($row = mysqli_fetch_assoc($result)) {
        			$temp_array[] = $row; //add result values to array.
        		}
        	}
        	
        	header('Content-Type: application/json');
        	echo json_encode(array("data"=>$temp_array)); //send array labelled 'data' back to client in JSON format.
        	mysqli_close($connect);//close connection to database as it is not needed anymore.
    }
}
?>