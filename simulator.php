<html>
<head>
<title>
	sim
</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
var count=1;
var input = [];

function add_row() {
	let table = document.getElementById("input_tab");
	let x = document.getElementById("input_tab").rows[0].cells.length;

	let newrow = table.insertRow(-1);

	let arr = [];
	let i=0;
	for ( i=0;i<x;i++ ) {
		arr[i] = newrow.insertCell(i);
	}	

	count++;
	let newtext = document.createTextNode(count);
	arr[0].appendChild(newtext);

	for( i=1;i<x;i++ ) {
		let newinput = document.createElement("INPUT");
		newinput.setAttribute("type","text");
		arr[i].appendChild(newinput);
	}
}

function rem_row() {
	let table = document.getElementById("input_tab");
	table.deleteRow(-1);
	count--;
}

function submit() {
	let table = document.getElementById("input_tab");
	let r = table.rows.length;
	let c = table.rows[0].cells.length;

	var i,j;
	for( i=1;i<r;i++ ) {
		let temp = [];
		temp.push(table.rows[i].cells[0].innerHTML);
		for( j=1;j<c;j++ ) {
			let t = table.rows[i].cells[j].getElementsByTagName("input");
			temp.push(t[0].value);
		}
	input.push(Object.assign({},temp));
	}

	var json_inp = JSON.stringify(input);

	let qtime = document.getElementById("qtime");
	let datatosend = 'input='+json_inp;
	let urltosend = "algo_sim.php?num_proc="+count;

	console.log(qtime);
	if( JSON.stringify(qtime) != "null" ) {
		urltosend += "&qtime="+qtime.value;
	}

	$.ajax({
		type: "POST",
		url: urltosend,
		async:true,
		data: datatosend,
		success: function(output_var) {
			console.log(output_var);
			document.getElementById("output").innerHTML = output_var;
			return true;
		},
		complete: function() {
			console.log("completed");
		},
	});
}

function display_output() {

}
</script>

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

echo "<br><br>";

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
