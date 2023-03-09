<?php
    require_once("db.php");
    require_once("functions.php");
?>

<?php
    // if(!empty($_GET['Action']) == "Login"){
    //     require_once("login.php");
    //     errorWrite($version,"Something went wrong in the login process");
    // }


    // if(empty($_GET['Token']) || empty($_GET['Password'])){ 
    //     errorWrite($version,"Wrong username or password was given");
    // }

?>

<?php
    if(!empty($_GET['Action'])){
        if($_GET['Action'] == "CreateUser"){
            require_once("createuser.php");
            // errorWrite($version,"Something went wrong in the creating process");
        }
    
        if($_GET['Action'] == "ManageCard"){
            require_once("managecard.php");
            // errorWrite($version,"Something went wrong in the creating process");
        }
    }

?>
