<?php
$id = $_GET['id'];

require_once('koneksi.php');

$sql = "SELECT * FROM employe WHERE id = '$id'";

$r = mysql_query($sql);

$result = array();
$row = mysql_fetch_array($r);
array_push($result, array(
	"id" => $row['id'],
	"nama" => $row['nama'],
	"jab" => $row['jabatan'],
	"gaji" => $row['gaji']
));

echo json_encode(array('result' => $result));

mysql_close($con);
?>
