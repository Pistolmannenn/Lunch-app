<?php
    if(empty($_GET['Name']) || empty($_GET['Password']) || empty($_GET['Email'])){
        errorWrite($version,"Either Name, Password or Email was not submitted");
    }
    $nameC = $_GET['Name'];
    $passwordC = password_hash($_GET["Password"],PASSWORD_DEFAULT);
    $emailC = $_GET['Email'];

    $stmt = $conn->prepare("INSERT INTO users (`Name`,`Password`,`Email`) VALUES (?,?,?)");
    $stmt->bind_param("sss", $nameC, $passwordC, $emailC);

    // set parameters and execute
    $stmt->execute();
    $last_id = $conn->insert_id;
    $userID = $last_id;
    
    $sql = "SELECT `ID`,`Name`,`Password`,`Email` FROM users WHERE ID = $last_id";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        while($row = $result->fetch_assoc()) {
            echo "====================== <br>";
            echo "id: " . $row["ID"]. " - Name: " . $row["Name"]. " - Password: " . $row["Password"]. " - Email: " . $row["Email"]. "<br>";
        }

        while(strlen($userID) < 6){
            $userID = "0".$userID;
        }

        require_once('qr/qrgen.php');

        $stmt = $conn->prepare("UPDATE `users` SET `QR` = ? WHERE `users`.`ID` = $last_id");
        $stmt->bind_param("s", $urlRelativeFilePath);
    
        // set parameters and execute
        $stmt->execute();

        $sql = "SELECT `ID`,`Name`,`Password`,`Email`,`QR` FROM users WHERE ID = $last_id";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
                echo "====================== <br>";
                echo "id: " . $row["ID"]. " - Name: " . $row["Name"]. " - Password: " . $row["Password"]. " - Email: " . $row["Email"]. " - QR: " . $row["QR"]. "<br>";
                echo '<img src="'.$row['QR'].'" />';
            }
        }
    }

?>