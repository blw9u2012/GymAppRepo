<?php
require_once "dbconnect.php";

//get the command from the android device...

if(isset($_REQUEST['command'])){
	$command = $_REQUEST['command'];


	//determine the command...
	if($command == "getRooms"){
		$result = mysql_query("SELECT * FROM Room");
		while($row = mysql_fetch_assoc($result)){
			echo $row['name']."\n";
		}
	}
}



?>
