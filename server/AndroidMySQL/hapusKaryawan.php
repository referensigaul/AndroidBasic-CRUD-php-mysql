<?php
$id = $_GET['id'];

require_once('koneksi.php');

$sql = "DELETE FROM employe WHERE id = '$id'";

if(mysql_query($sql)){
	echo 'Hapus data karyawan sukses';
} else{
	echo 'Hapus data karyawan gagal';
}

mysql_close($con);
?>
