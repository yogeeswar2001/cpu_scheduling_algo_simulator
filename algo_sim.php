<?php
session_start();

$decoded = json_decode($_POST['input'],true);
$num_proc = $_GET["num_proc"];

$input = $_SESSION["algo_num"];
$input = $input." ".$num_proc;

if ( $_SESSION["algo_num"] == 3 ) {
	$input = $input." ".$_GET["qtime"];
}

if ( $_SESSION["algo_num"] < 4 ) {
	foreach ($decoded as $value) {
		$input = $input." ".$value["0"]." ".$value[1];
	}
}
else {
	foreach ($decoded as $value) {
		$input = $input." ".$value["0"]." ".$value[1]." ".$value[2];
	}
}

#echo $input;

exec('./target/simulator '.$input, $output, $return_stat);

echo "<table id=\"output_tab\">
	<tr>
	<th>PID</th><th>BURST TIME</th><th>WAITING TIME</th><th>TURN-AROUND TIME</th>
	</tr>";
$str = "";
for ( $i=0;$i<$num_proc;$i++ ) {
	$temp = explode(" ",$output[$i]);
	$str = "";
	foreach ($temp as $result) {
		$str = $str."<td>".$result."</td>";
	}
	echo "<tr>".$str."</tr>";
}
echo "</table>";
echo "<h3>AVERAGE WAITING TIME: ".$output[$num_proc]."<br>AVERAGE TURN-AROUND TIME: ".$output[$num_proc+1]."</h3>";
echo "<h3>GANTT CHART<br>".$output[$num_proc+2]."<br>".$output[$num_proc+3]."</h3>";

?>
