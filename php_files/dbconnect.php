<?php

ini_set("display_errors", "on");
ini_set("short_open_tag", "off");

ini_set("output_buffering", "off");

ini_set("error_reporting", -1);


$__mysql_username__ = "blw9u";
$__mysql_password__ = "brandon1889";
$__mysql_server__ = "dbm2.itc.virginia.edu";
$__mysql_database__ = "gym_app_db";


$database = mysql_connect($__mysql_server__, $__mysql_username__, $__mysql_password__)
    or die("Could not connect to MySQL Server ({$__mysql_server__})");
// select database

mysql_select_db($__mysql_database__)
    or die("Could not select database ({$__mysql_database__})");

