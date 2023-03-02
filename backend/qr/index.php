<?php
    include('phpqrcode/qrlib.php');

    // how to save PNG codes to server
    
    $tempDir = "codes/";

    $codeContents = 'Token +1';
    
    // we need to generate filename somehow, 
    // with md5 or with database ID used to obtains $codeContents...
    $fileName = '005_file_'.md5($codeContents).'.png';
    
    $pngAbsoluteFilePath = $tempDir.$fileName;
    $urlRelativeFilePath = $tempDir.$fileName;
    
    // generating
    if (!file_exists($pngAbsoluteFilePath)) {
        QRcode::png($codeContents, $pngAbsoluteFilePath, 'H',4,4);
        echo 'File generated!';
        echo '<hr />';
    } else {
        echo 'File already generated! We can use this cached file to speed up site on common codes!';
        echo '<hr />';
    }
    
    echo 'Server PNG File: '.$pngAbsoluteFilePath;
    echo '<hr />';
    
    // displaying
    echo '<img src="'.$urlRelativeFilePath.'" />';
?>

<?php
    // include('phpqrcode/qrlib.php');

    // // how to build raw content - QRCode to send email, basic
    
    // $tempDir = "codes/";
    
    // // here our data
    // $email = 'kevinplopp03@gmail.com';
    // $subject = 'question';
    // $body = 'please write your question here';
    
    // // we building raw data
    // $codeContents = 'mailto:'.$email.'?subject='.urlencode($subject).'&body='.urlencode($body);
    
    // // generating
    // QRcode::png($codeContents, $tempDir.'022.png', QR_ECLEVEL_L, 3);
   
    // // displaying
    // echo '<img src="'.$tempDir.'022.png" />';
?>