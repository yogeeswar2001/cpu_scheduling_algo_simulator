<html>
<head>
<title>
	sim
</title>

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
			//console.log( "yogi",t[0].value );
			temp.push(t[0].value);
		}
		console.log("temp arr",temp);
	input.push(temp);
	}
	console.log(input);
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
ini_set('display_errors',1);
error_reporting(E_ALL);

$algo_num = $_GET["algo_num"];
#echo $algo_num;
echo "<br><br>";

echo "<table id=\"input_tab\">";
if ( $algo_num == 4 ) {
	echo "<tr>
		<th>process id</th>
		<th>brust time</th>
		<th>priority</th>
		</tr>
		<tr>
		<td>1</td>
		<td><input type=\"text\"></td>
		<td><input type=\"text\"></td>
		</tr>";
}
else { 
	echo "<tr>
		<th>process id</th>
		<th>brust time</th>
		</tr>
		<tr>
		<td>1</td>
		<td><input type=\"text\"></td>
		</tr>";
}
echo "</table><br><br>";

echo "<button onclick=\"add_row()\">+</button><br>";
echo "<button onclick=\"rem_row()\">-</button><br>";
echo "<button onclick=\"submit()\">submit</button><br>";
?>

</body>
</html>
