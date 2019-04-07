
<?php

// CHARLIE ANSELL
// 04/04/2019
// COMPANION APP PROJECT

/*
This PHP file establishes a connection to the remote MySQL databased stored in Hostingers server and then creates the two tables needed to store data for both games if they already dont exist.
*/ 

define('hostname', 'localhost');
define('user', 'u530535384_yheh');
define('password', 'companion');   //define parameters for connecting to the database.
define('databaseName', 'u530535384_yheh');

$connect = mysqli_connect(hostname, user, password, databaseName); //connect to the database.


   #create table to hold thrid person data
	$query = "CREATE TABLE IF NOT EXISTS TPS_STATS (
    ID int(11) unsigned NOT NULL auto_increment,
    username varchar(255) NOT NULL default '',
    password varchar(255) NOT NULL default '',
    kills integer unsigned NOT NULL default '1',
    bulletsFired integer NOT NULL default 0,
    bulletsHit integer NOT NULL default 0,
	bulletsMissed integer NOT NULL default 0,
	score Integer NOT NULL default 0,
	hitAccuracy Integer not null default 0,
	timePlayed VARCHAR(20) not null default 0,
	scorePerMinute Integer not null default 0,
    PRIMARY KEY  (ID));";
    mysqli_query($connect, $query) or die (mysqli_error($connect));
    
    //create table 2DS_STATS to hold 2ds game stats.
    $query1 = "CREATE TABLE IF NOT EXISTS 2DS_STATS (
    ID int(11) unsigned NOT NULL auto_increment,
    username varchar(255) NOT NULL default '',
    password varchar(255) NOT NULL default '',
    kills integer unsigned NOT NULL default '1',
    bulletsFired integer NOT NULL default 0,
    bulletsHit integer NOT NULL default 0,
	bulletsMissed integer NOT NULL default 0,
	score Integer NOT NULL default 0,
	hitAccuracy Integer not null default 0,
	timePlayed VARCHAR(20) not null default 0,
	scorePerMinute Integer not null default 0,
    PRIMARY KEY  (ID));";
    mysqli_query($connect, $query1) or die (mysqli_error($connect));
	
?>