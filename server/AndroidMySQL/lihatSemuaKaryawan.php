<?php
require_once('koneksi.php');

$sql = "SELECT * FROM employe";

$r = mysql_query($sql);

$result = array();

while($row = mysql_fetch_array($r)){
	array_push($result, array(
	"id" => $row['id'],
	"nama" => $row['nama']
));	
}

echo json_encode(array('result' => $result));

mysql_close($con);
?>
