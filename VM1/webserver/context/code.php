<?PHP
session_start();
require_once("./include/membersite_config.php");

$_SESSION['auth']=0;

if ($_SESSION['redirect']==1)
{
	$fgmembersite->RedirectToURL("login-home.php");
	exit;
}

if(!$fgmembersite->CheckLogin())
{
    $fgmembersite->RedirectToURL("login.php");
    exit;
}
$useme = $_SESSION['id'];
if ($_SERVER["REQUEST_METHOD"] == "POST") {


	if (empty($_POST["code"])) {
		$nameErr = "Code is required";
		header('Location: logout.php');  
	}

	else{
		$data1 = exec('cat /var/www/otp/client'.$useme.'/otp.pt');
        	if ($_POST["code"] == $data1) {
			$_SESSION['redirect']=1;
			$_SESSION['auth']=1;
        		header('Location: login-home.php');
			exit;
        	}
		else {
			header("Location: logout.php");
		}
    	}
}

if (!$_SESSION['f5']) {
	exec("/var/www/qrcodegen.sh $useme");
	$_SESSION['f5'] = 1;
}
	$file = 'otp/qrCode'.$useme.'.png';
	echo '<img src="'. $file. '" alt="'. "Not Found". '" />';


?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<html>
<head>
      <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
      <title>Login</title>
      <link rel="STYLESHEET" type="text/css" href="normalise.css" />
      <link rel="STYLESHEET" type="text/css" href="style.css" />
      <script type='text/javascript' src='scripts/gen_validatorv31.js'></script>
</head>

<body>
<div id="wrap">
<form method="post">
QR Code: <input type="text" name="code"><br>
<!--E-mail: <input type="text" name="email"><br>-->
<input type="submit">
</form>

</body>
</html>
