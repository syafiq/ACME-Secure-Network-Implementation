<?php
session_start();
require_once("./include/membersite_config.php");
if(!$fgmembersite->CheckLogin() )
{
   $fgmembersite->RedirectToURL("../../login.php");
 exit;
}

echo "Here are your files";
$user1 = $_SESSION['user-name'];
$path = "/var/www/fileserver/$user1";
$dh = opendir($path);
$i=1;
while (($file = readdir($dh)) !== false) {
    if($file != "." && $file != ".." && $file != "index.php" && $file != ".htaccess" && $file != "error_log" && $file != "cgi-bin") {
        echo "<a href='$file'>$file</a><br /><br />";
        $i++;
    }
}
closedir($dh);
?> 
