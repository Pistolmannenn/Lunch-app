<?php 
//  errorWrite($version,$error)
//  kills the code and writes out a JSON text with the error message
//  $version      | string    | ex. 0.1.0 from db.php
//  $error        | string    | ex. "Something went wrong"
    function errorWrite($version,$error){ 
        #Message on error
        $return = ["Version"=>$version,"Type"=>"Error","Data"=>"$error"];
        die(json_encode($return));
    }

//  jsonWrite($version,$data)
//  kills the code and writes out a JSON text with the data requested
//  $version      | string    | ex. 0.1.0 from db.php
//  $error        | string    | ex. "Result"->"Action preformed succsessfully"
    function jsonWrite($version,$data){
        #Displaying the inserted data
        $contents = ["Version"=>$version,"Type"=>"Ok","Data"=>$data];
        die(json_encode($contents));
    }
?>