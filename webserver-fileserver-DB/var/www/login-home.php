<?PHP
session_start();
require_once("./include/membersite_config.php");

if($_SESSION['auth']==0)
{
	header("Location: logout.php");
}
if(!$fgmembersite->CheckLogin() )
{
    $fgmembersite->RedirectToURL("login.php");
    exit;
}

if ($_GET['run']) {
  # This code will run if ?run=true is set.
  
$link = '/fileserver/'.$_SESSION['user-name'].'/';
  header("Location: $link");
}



?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<head>
      <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
      <title>Home page</title>
      <link rel="STYLESHEET" type="text/css" href="normalise.css" />
      <link rel="STYLESHEET" type="text/css" href="style.css" />

</head>
<body>
<div id='fg_membersite_content'>
<h2>Home Page</h2>
Welcome back <?= $fgmembersite->UserFullName(); ?>!

<p><a href='change-pwd.php'>Change password</a></p>

<p><a href='access-controlled.php'>A sample 'members-only' page</a></p>
<!--<p><a href='fileserver/'.$_SESSION['id']>myfiles</a></p>-->
<!-- This link will add ?run=true to your URL, myfilename.php?run=true -->
<p><a href="?run=true">my-file-system</a></p>
<p><a href='upload.php'>upload-a-file</a></p>

<br><br><br>
<p><a href='vm4'>internal-data</a></p>
<p><a href='logout.php'>Logout</a></p>
</div>
</body>
</html>
