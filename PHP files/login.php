<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT
/*
This PHP file checks the users entered details from Unity or Android device and validates it. 
*/


if($_SERVER['REQUEST_METHOD']=='POST'){
$device = $_POST['device'];    
$database = $_POST['database'];
$username = $_POST['username']; //gather data from request.
$password = $_POST['password'];
require_once('connection.php'); #require connection.php to connect to database 

if($device=='unity') //if device variable equals unity
{
    //validate depending on what database it is.
    $sql = "SELECT Username FROM $database WHERE Username = '$username' AND password = '$password'";
    $result = mysqli_query($connect,$sql);
    $check = mysqli_fetch_array($result);
    
    if(isset($check)){ //if result is not null
    echo 'success'; //echo success
    }
    else
    {
     echo 'login failed'; //echo fail
    }
}
else if($device=='android'){ //if device is android. 
    
    //check from both TPS_STATS and 2DS_STATS database for username and password.
    $sql = "SELECT USERNAME,PASSWORD 
    FROM TPS_STATS AS TPS
    WHERE TPS.USERNAME ='$username' AND TPS.PASSWORD='$password'
    UNION
    SELECT USERNAME,PASSWORD 
    FROM 2DS_STATS as 2DS
    WHERE 2DS.USERNAME ='$username' AND 2DS.PASSWORD='$password'";
    
    $result = mysqli_query($connect,$sql);// execute query
    $check = mysqli_fetch_array($result); //get result
    
    if(isset($check)){ //if result is not null
    echo 'success';//echo success
    }else
    {
        echo 'login failed';//echo fail
    }
 }
}
?>