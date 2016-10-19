<?php
require_once("./include/membersite_config.php");
session_start();
if(!$fgmembersite->CheckLogin() )
{
    $fgmembersite->RedirectToURL("login.php");
    exit;
}

$target_dir = "fileserver/".$_SESSION['user-name'].'/';
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
if(isset($_POST["submit"])) {
move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file);
$location = "fileserver/".$_SESSION['user-name'].'/';
header("Location: $location");
}
?> 

<!DOCTYPE html>
<html>
      <link rel="STYLESHEET" type="text/css" href="normalise.css" />
      <link rel="STYLESHEET" type="text/css" href="style.css" />

<body>

<form action="upload.php" method="post" enctype="multipart/form-data">
    Select image to upload:
    <input type="file" name="fileToUpload" id="fileToUpload">
    <input type="submit" value="Upload" name="submit">
</form>

</body>
</html>  
