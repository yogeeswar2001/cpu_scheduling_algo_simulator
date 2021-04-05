<?php
session_start();

$decoded = json_decode($_POST['input'],true);

if ( $_SESSION["algo_num"] < 4 ) {
	foreach ($decoded as $value) {
		echo $value["0"].",".$value[1]."<br>";
	}
}
else {
	foreach ($decoded as $value) {
		echo $value["0"].",".$value[1].",".$value[2]."<br>";
	}
}

exec('ls', $output, $return_stat);

foreach ($output as $result)
	echo "<h1>".$result."</h1><br>";
?>
