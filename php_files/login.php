<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
  <!-- INSERT LOGIN FORM HERE -->
  <form id="loginForm" action="<?php echo $_SERVER["PHP_SELF"];?>" method="post">
   <div id="login">
     <label for="usernameInput">Username</label><br />
     <input id="usernameInput" name="username" type="text" /><br />

     <label for="passwordInput">Password</label><br />
     <input id="passwordInput" name="password" type="password" /><br />
     
     <input id="logInButton" type="submit" value="Log in" />
   </div> 
 </form>
 <!-- end login -->
</body>
</html>

<?php
require_once "dbconnect.php";
if(isset($_POST['submit'])){


if(isset($_POST['username'], $_POST['password'])){
    $username = mysql_real_escape_string($_POST["username"]);
    $password = mysql_real_escape_string($_POST["password"]);

// select user id from database (if login credentials valid)

$sql = "SELECT id FROM User WHERE username = '$username' AND password = '$password'";
$result = mysql_query($sql);

if($result !== false){
  $row = mysql_fetch_assoc($result);
  if($row !== false){
    echo $row['username'];
  }
  echo $row;
 }
}
}
?>

