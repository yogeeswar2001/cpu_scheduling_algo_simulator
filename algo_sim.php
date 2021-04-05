<?php
$decoded = json_decode($_POST['input'],true);

foreach ($decoded as $value) {
	echo $value["0"].",".$value[1]."<br>";
}
?>
