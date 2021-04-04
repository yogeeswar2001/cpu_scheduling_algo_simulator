<html>
<head>
<title>input sim</title>
</head>

<body>
<?php
$algo_num = $_GET["algo_num"];

#echo $algo_num;
echo "<form action=\"simulator.php\" method=\"post\">
	<textarea name=\"input\" rows=\"10\" cols=\"30\">".$algo_num."
	</textarea>
	<input type=\"submit\" value=\"submit\">
</form>";
?>
</body>
</html>
