<?php
// Connecting into the database

    session_start();
    //error_reporting(0); 
    $version = "0.0.1";
    
    $servername = "localhost";
    $username = "root";
    $password = "";
    $db = "takeee";

    // Create connection
    $conn = new mysqli($servername, $username, $password,$db);

    // Check connection
    if ($conn->connect_error) {die("Connection failed: " . $conn->connect_error);}
?>

