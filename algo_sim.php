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
echo "<h4>AVERAGE WAITING TIME: ".$output[$num_proc]."<br>AVERAGE TURN-AROUND TIME: ".$output[$num_proc+1]."</h4>";
echo "<h4>GANTT CHART<br></h4>";

echo "<table id=\"gantt_chart\">";
$temp = explode(" ",$output[$num_proc+2]);
$time = array_slice(explode(" ",$output[$num_proc+3]),1);
$str="";

$i=0;
$px=20;
foreach ($temp as $result) {
	if( $i == 0)
		$str = $str."<th style=\"width:".(int)$time[$i]*$px."px\">P".$result."</th>";
	else 
		$str = $str."<th style=\"width:".((int)$time[$i]-(int)$time[$i-1])*$px."px\">P".$result."</th>";
	$i++;
}
echo "<tr>".$str."</tr>";

$str="";
$i=0;
foreach ($time as $result) {
	if( $i == 0 ) 
		$str = $str."<td><div style=\"float:left;\">0</div><div style=\"float:right;\">".$result."</div></td>";
	else
		$str = $str."<td><div style=\"float:right\">".$result."</div></td>";
	$i++;
}
echo "<tr>".$str."</tr></table>";

?>
