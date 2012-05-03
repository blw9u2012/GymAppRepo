<?php
require_once "dbconnect.php";

//get the command from the android device...

if(isset($_REQUEST['command'])){
	$command = $_REQUEST['command'];


	//determine the command...

	//sign in...
	if($command == "signIn")
	{
		if(isset($_REQUEST['username'], $_REQUEST['password']))
		{
			$username = mysql_real_escape_string($_REQUEST['username']);

			//use mysql real escape string function to prevent sql injection attacks...
			$password = mysql_real_escape_string($_REQUEST['password']);
			$md5 = md5($password);

			//try the query here...
			$signInResult = mysql_query("SELECT username,id FROM Patron WHERE username='$username' AND password='$md5'");
				
			//login attempt succeded..
			if(($signInResult !== false)){

				if(mysql_num_rows($signInResult) < 1){
					echo "Failed";
				}
				else{
					$signInRow = mysql_fetch_assoc($signInResult);
					echo "success".",".$signInRow['username'].",".$signInRow['id'];
				}
			}

		}
		else{
			echo "Error username and password not set";
		}
	}

	//validate a user
	if($command == "validateUser"){
		if(isset($_REQUEST['username'], $_REQUEST['usertype'])){
			$username = $_REQUEST['username'];
			$usertable = $_REQUEST['usertype'];

			switch($usertable){
			   case "FullTimeStaff":
				$result = mysql_query("SELECT username FROM FullTimeStaff WHERE username='$username'");
				if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }
				break;
			   case "PersonalTrainers":
				$result = mysql_query("SELECT username FROM PersonalTrainers WHERE username='$username'");
				if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }
				break;
			   case "MassageTherapist":
				$result = mysql_query("SELECT username FROM MassageTherapist WHERE username='$username'");
				 if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }
				break;
			   case "DropInInstructor":
				$result = mysql_query("SELECT username FROM DropInInstructor WHERE username='$username'");
				 if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }
				break;
			   case "Nutritionist":
				$result = mysql_query("SELECT username FROM Nutritionist WHERE username='$username'");
				 if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }
				break;
			   case "Club":
				$result = mysql_query("SELECT username FROM Club WHERE username='$username'");
				 if(mysql_num_rows($result) < 1){
                                        echo "Failed";
                                }
                                else{           
                                        echo "Succeeded ";
                                }

				break;
			}
		}
	}

	//add a user...aka Patron
	if($command == "addUser"){
		if(isset($_REQUEST['name'], $_REQUEST['lastname'], $_REQUEST['age'], $_REQUEST['email'], $_REQUEST['phone'], $_REQUEST['username'], $_REQUEST['password'])){

			//is userType1 is set add to the patron table
			if(isset($_REQUEST['userType1'])){
				if(($_REQUEST['userType1'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into Patron (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}

			//if userType2 is set add to the personal trainer table...
			if(isset($_REQUEST['userType2'])){
				if(($_REQUEST['userType2'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into PersonalTrainers (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}
			//if userType3 is set add to the Instructor table...
			if(isset($_REQUEST['userType3'])){
				if(($_REQUEST['userType3'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into DropInInstructors (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}
				
			//if userType4 is set add to the nutritionist table...
			if(isset($_REQUEST['userType4'])){
				if(($_REQUEST['userType4'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into Nutritionist (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}
				
			//if userType5 is set add to the club table...
			if(isset($_REQUEST['userType5'])){
				if(($_REQUEST['userType5'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into Club (name,email,phone,username,password) VALUES ('$name','$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$clubResult = mysql_query("SELECT clubId FROM Club WHERE username='$username'");
						$clubRow = mysql_fetch_assoc($clubResult);
						echo $clubRow['clubId'].",".$name;
					}
				}
			}
			//if userType6 is set add to the massage therapist table...
			if(isset($_REQUEST['userType6'])){
				if(($_REQUEST['userType6'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into MassageTherapist (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}
			//if userType7 is set add to the personal trainer table...
			if(isset($_REQUEST['userType7'])){
				if(($_REQUEST['userType7'] == "true")){
					$name = mysql_real_escape_string($_REQUEST['name']);
					$lastname = mysql_real_escape_string($_REQUEST['lastname']);
					$age = mysql_real_escape_string($_REQUEST['age']);
					$email = mysql_real_escape_string($_REQUEST['email']);
					$phone = mysql_real_escape_string($_REQUEST['phone']);
					$username = mysql_real_escape_string($_REQUEST['username']);
					$password = md5(mysql_real_escape_string($_REQUEST['password']));
					$result = mysql_query("INSERT into FullTimeStaff (name,lastname,age,email,phone,username,password) VALUES ('$name','$lastname',$age,'$email','$phone','$username','$password')");
					if($result !== false){
						//return their id...
						$userIdResult = mysql_query("SELECT id FROM Patron WHERE username='$username'");
						$userIdRow = mysql_fetch_assoc($userIdResult);
						echo $userIdRow['id'].",".$name;
					}
				}
			}
		}
	}

	//getRooms
	if($command == "getRooms"){
		$result = mysql_query("SELECT * FROM Room");
		while($row = mysql_fetch_assoc($result)){
			echo $row['roomId'].",".$row['name'].",".$row['floor'].",".$row['capacity'].",".$row['availability']."\n";
			//$output[] = $row;
		}
	}

        //get individual room
        if($command == "getRoom"){
		if(isset($_REQUEST['roomId'])){
			$room_id = $_REQUEST['roomId'];
                	$result = mysql_query("SELECT * FROM Room WHERE roomId=$room_id");
			$roomRow = mysql_fetch_assoc($result);
                        echo
$roomRow['roomId'].",".$roomRow['name'].",".$roomRow['floor'].",".$roomRow['capacity'].",".$roomRow['availability']."\n";
		
        	}
	}

	//get the machines
	if($command == "getMachines"){
		$result = mysql_query("SELECT * FROM Machine");
			
		if($result !== false){
			while($row = mysql_fetch_assoc($result)){
				echo $row['id'].",".$row['name'].",".$row['body_focus'].",".$row['availability'].",".$row['exercise_type']."\n";
			}
		}
	}

	//get the machines associated with a user
	if($command == "getUserMachines"){
		if(isset($_REQUEST['userId'])){
			$user_id = $_REQUEST['userId'];
			$result = mysql_query("SELECT * FROM Machine WHERE user=$user_id");
			while($row = mysql_fetch_assoc($result)){
				echo $row['id'].",".$row['name'].",".$row['body_focus'].",".$row['availability'].",".$row['exercise_type']."\n";
			}
		}
	}

	//get the classes
	if($command == "getClasses"){
		$result = mysql_query("SELECT * FROM Classes");
			
		if($result !== false){
			while($row = mysql_fetch_assoc($result)){
				echo $row['id'].",".$row['name'].",".$row['location'].",".$row['start_time'].",".$row['end_time'].",".$row['capacity'].",".$row['enrolled'].",".$row['instructor']."\n";
			}
		}
	}

	if($command == "getUserClasses"){
		if(isset($_REQUEST['userId'])){
			$user_id = $_REQUEST['userId'];
			$result = mysql_query("SELECT classId, className FROM ClassSignUp WHERE patronId=$user_id");
			while($row = mysql_fetch_assoc($result)){
				$class_id = $row['classId'];
				$classResult = mysql_query("SELECT instructor, capacity, enrolled FROM Classes WHERE id=$class_id");
				$classRow = mysql_fetch_assoc($classResult);
				echo $row['classId'].",".$row['className'].",".$classRow['instructor'].",".$classRow['capacity'].",".$classRow['enrolled']."\n";
			}

		}
	}


	//allow a user to sign up for a class...
	if($command == "signUp"){
		if(isset($_REQUEST['userId'], $_REQUEST['classId'])){
			$userId = $_REQUEST['userId'];
			$classId = $_REQUEST['classId'];

			//insert into the class sign up table...
			$classResult = mysql_query("SELECT name, enrolled, capacity FROM Classes WHERE id='$classId'");
			$classRow = mysql_fetch_assoc($classResult);
			$class_name = $classRow['name'];
			$class_enrolled = $classRow['enrolled'];
			$class_capacity = $classRow['capacity'];

			//check to see if the user can enroll into the specific class...
			$class_enrollment = $class_enrolled + 1;
			echo "Enrollment: ".$class_enrollment.", Capacity: ".$class_capacity;

			//if the user cannot sign up for the class..
			if($class_enrollment > $class_capacity){
				echo "Class is full, try again later";
			}

			//sign up the user for the class by inserting the entry into the table ClassesSignUp so there is a record...
			else{

				//first check to see if the user is already enrolled...
				$checkUserResult = mysql_query("SELECT patronId, classId FROM ClassSignUp WHERE patronId=$userId AND classId=$classId");
				if(mysql_num_rows($checkUserResult) < 1){
					echo "Already signed up for this class";
				}
				else{
					$signUpResult = mysql_query("INSERT INTO ClassSignUp (patronId,classId,className) VALUES ($userId,$classId,'$class_name')");
					if($signUpResult !== false){

						//update the number of people enrolled in that particular class...
						$updateResult = mysql_query("UPDATE Classes SET enrolled=$class_enrollment WHERE id=$classId");
						if($updateResult !== false){
							echo " Signed Up! :)";
						}
					}
					else{
						echo "Failed";
					}
				}
			}


		}
	}

	if($command == "setMachineUser"){
		//echo "in set machine user";
		if(isset($_REQUEST['userId'], $_REQUEST['machineId'])){
			$userId = $_REQUEST['userId'];
			$machineId = $_REQUEST['machineId'];
			//echo $userId;
			$userResult = mysql_query("SELECT username FROM Patron WHERE id=$userId");
			if($userResult !== false){
				//echo "trying query";
				$row = mysql_fetch_assoc($userResult);
				$username = $row['username'];

				//echo $username;
				//echo $machineId;
				//update machine table
				$updateResult = mysql_query("UPDATE Machine SET user=$userId WHERE id=$machineId");
			}
		}
	}

	if($command == "getMachineUser"){
		if(isset($_REQUEST['machineId'])){
			$machineId = $_REQUEST['machineId'];
			$result = mysql_query("SELECT user FROM Machine WHERE id=$machineId");
			$row = mysql_fetch_assoc($result);
			$userId = $row['user'];

			$userResult = mysql_query("SELECT username FROM Patron WHERE id=$userId");
			$userRow = mysql_fetch_assoc($userResult);
			echo $userRow['username'];


		}
	}
	//change availability of the machine...
	if($command == "changeAvailability"){
		//echo $command, $avail, $id;
		if(isset($_REQUEST['avail'], $_REQUEST['id'], $_REQUEST['type'])){
			$avail = $_REQUEST['avail'];
			$id = $_REQUEST['id'];
			$type = $_REQUEST['type'];

			if($type == 'machine'){
				$sql = "UPDATE Machine SET availability='$avail' WHERE id='$id'";
				//$sql = "SELECT availability FROM Machine WHERE inventory_id='$id'";
				$result = mysql_query($sql);

				if($result !== false){
					//echo $row['availability'];
					echo "Availability changed!";
				}
			}

			if($type == 'room'){
				//echo "in room clause";
				$sql = "UPDATE Room SET availability='$avail' WHERE roomId='$id'";
				$result = mysql_query($sql);

				if($result !== false){
					//determine if the room is reserved or not...
					if($avail == '1') echo "The room is now available";
					else echo "The room is now unavailable";
				}
			}
		}
	}

	if($command == "getUserId"){
		if(isset($_REQUEST['user_name'])){
			$user_name = $_REQUEST['user_name'];
			$result = mysql_query("SELECT id FROM Patron WHERE username='$user_name'");
			if($result !== false){
				$row = mysql_fetch_assoc($result);
				echo $row['id'];
			}
		}
	}
	//add to the number of check ins for a user
	if($command == "checkIn"){
		if(isset($_REQUEST['user_id'], $_REQUEST['class_id'])){
			$userId = $_REQUEST['user_id'];
			$classId = $_REQUEST['class_id'];

			//get class information...
			$class_sql = "SELECT id, name FROM Classes WHERE id='$classId'";
			$class_result = mysql_query($class_sql);
			$classRow = mysql_fetch_assoc($class_result);
			$class_name = $classRow['name'];

			//get user information...
			$user_sql = "SELECT name FROM Patron WHERE id='$userId'";
			$user_result = mysql_query($user_sql);
			$userRow = mysql_fetch_assoc($user_result);
			$user_name = $userRow['name'];

			$insert_result = mysql_query("INSERT INTO ClassCheckIn (patron_id,class_id) VALUES ($userId,$classId)");
			if($insert_result !== false) echo "Checked In to ".$class_name;

		}
	}

	if($command == "getClass"){
		if(isset($_REQUEST['classId'])){
			$classId = $_REQUEST['classId'];
			$result = mysql_query("SELECT * FROM Classes WHERE id=$classId");
			if($result !== false){
				$row = mysql_fetch_assoc($result);
				echo $row['name'].",".$row['instructor'].",".$row['description'].",".$row['location'].",".$row['start_time'].",".$row['end_time'].",".$row['day'].",".$row['capacity'].",".$row['enrolled']."\n";
			}
		}
	}

}



?>
