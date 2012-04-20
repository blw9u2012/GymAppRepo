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
			//$output[] = $row;
		}
	}
	if($command == "getMachines"){
		$result = mysql_query("SELECT * FROM Machine");
			
		if($result !== false){
			while($row = mysql_fetch_assoc($result)){
				echo $row['name'].", ".$row['body_focus'].", ".$row['inventory_id'].", ".$row['availibility'].", ".$row['exercise_type']."\n";
			}
		}
	}

	if($command == "changeAvailibility"){
		//echo $command, $avail, $id;
		if(isset($_REQUEST['avail'], $_REQUEST['id'])){
			$avail = $_REQUEST['avail'];
			$id = $_REQUEST['id'];


			$sql = "UPDATE Machine SET availibility='$avail' WHERE inventory_id='$id'";
			//$sql = "SELECT availibility FROM Machine WHERE inventory_id='$id'";
			$result = mysql_query($sql);

			if($result !== false){
				//echo $row['availibility'];
				echo "Availibility changed!";
					
			}
		}
	}
}



?>
