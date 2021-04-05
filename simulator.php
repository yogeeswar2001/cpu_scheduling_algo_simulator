<html>
<head>
<title>
	simulator
</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="js/simulator.js"></script>

<style>
table,th,td { 
	border: 1px solid black;
	border-collapse: collapse;
}
</style>

</head>

<body>
<?php
session_start();

ini_set('display_errors',1);
error_reporting(E_ALL);

$algo_num = $_GET["algo_num"];

$_SESSION["algo_num"] = $algo_num;

$algo_name;
if ( $algo_num == 1)
	$algo_name = "FIRST COME FIRST SERVE";
else if( $algo_num == 2)
	$algo_name = "SHORTEST JOB FIRST";
else if ( $algo_num == 3)
	$algo_name = "ROUND ROBIN";
else
	$algo_name = "PRIORITY SCHEDULING";
echo "<h2 style=\"text-align:center\">".$algo_name."</h2><br>";

echo "<table id=\"input_tab\">";
if ( $algo_num == 4 ) {
	echo "<tr>
		<th>PID</th>
		<th>BURST TIME</th>
		<th>PRIORITY</th>
		</tr>
		<tr>
		<td>1</td>
		<td><input type=\"text\"></td>
		<td><input type=\"text\"></td>
		</tr>";
}
else { 
	echo "<tr>
		<th>PID</th>
		<th>BURST TIME</th>
		</tr>
		<tr>
		<td>1</td>
		<td><input type=\"text\"></td>
		</tr>";
}
echo "</table><br><br>";

if ( $algo_num == 3 ) {
	echo "QUANTUM TIME: <input type=\"text\" id=\"qtime\"><br><br>";
}

echo "<button onclick=\"add_row()\">+</button><br>";
echo "<button onclick=\"rem_row()\">-</button><br>";
echo "<button onclick=\"submit()\">submit</button><br>";
echo "<br><br><div id=\"output\"/>";
?>

</body>
</html>
