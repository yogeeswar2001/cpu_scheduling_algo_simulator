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
