<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT

/*
This PHP file checks if the user who is signing up exists or if the user does not. 
*/
if($_SERVER['REQUEST_METHOD']=='POST'){
$database = $_POST['database'];
$username = $_POST['username'];
require_once('connection.php'); #require connection.php to connect to database 

if($database=='TPS_STATS')//if the database is TPS_STATS
 {
    //check if a user in the database exists
    $sql = "SELECT username FROM $database WHERE username = '$username'";
    
    $result = mysqli_query($connect,$sql);//execute query
    
    $check = mysqli_fetch_array($result);//store result
   
    if(isset($check)){ //if result is not null
    echo 'exists';//echo to client 'exists'
    }else{//else
    echo 'success';//echo to the client 'success'
    }
 }
 else if ($database=='2DS_STATS'){//or if the database is 2DS_STATS
     
    //validate username from 2DS_STATS table
    $sql = "SELECT username FROM $database WHERE username = '$username'";
    
    $result = mysqli_query($connect,$sql);//execute query
    
    $check = mysqli_fetch_array($result);//store result
    
    if(isset($check)){//if result is not null
    echo 'exists';//echo 'exists' to client
    }else{//else
    echo 'success';//echo 'success' to client
    }
 }
}
?>