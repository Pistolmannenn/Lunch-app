<?php
    if(empty($_GET['UID']) || empty($_GET['Resturant']) || empty($_GET['Amount'])){
        errorWrite($version,"Either UID or Resturant was not submitted");
    }
    $uID = $_GET['UID'];
    $resturant = $_GET['Resturant'];
    $amount = $_GET['Amount'];
    $maxVal = 10;
    $exist = 0;

    while($exist == 0){
        $sql = "SELECT `ID`,`MaxVal`,`Val`,`Bonus`,`ExpiryDate` FROM loyaltycard WHERE `UID` = $uID and `RID` = $resturant";
        $result = $conn->query($sql);
    
        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $exist = 1;
            $val = $row['Val']+1;
            $sql = "UPDATE `loyaltycard` SET `Val` = '$val' WHERE `UID` = $uID and `RID` = $resturant";
            $result = $conn->query($sql);
            $exist = 1;

            if($row['Val'] >= $maxVal){
                $sql = "DELETE FROM `loyaltycard` WHERE `UID` = $uID and `RID` = $resturant";
                $result = $conn->query($sql);
                $exist = 0;
                echo"<br>Card completed! Congratulations!<br>";
            }
            else{
                echo "ID: " . $row["ID"]. " - MaxVal: " . $row["MaxVal"]. " - Val: " . $row["Val"]. " - Bonus: " . $row["Bonus"]. " - ExpiryDate: " . $row["ExpiryDate"]. "<br>";
            }
            
            
        }
        else{
            $stmt = $conn->prepare("INSERT INTO loyaltycard (`UID`,`RID`,`MaxVal`,`ExpiryDate`) VALUES (?,?,?,?)");
            $stmt->bind_param("ssis", $uID, $resturant, $maxVal, $expDate);
    
            $date = date("Y-m-d H-i-s");
            $expDate = date('Y-m-d H-i-s', strtotime('+1 years'));
        
            // set parameters and execute
            $stmt->execute();
            $last_id = $conn->insert_id;
            $userID = $last_id;
        }
    }
    // $sql = "UPDATE `loyaltycard` SET `Val` = '2' WHERE `UID` = $uID and `RID` = $resturant";
    // $result = $conn->query($sql);
    


?>