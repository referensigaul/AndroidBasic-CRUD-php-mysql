<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$id = $_POST['id'];
	$nama = $_POST['nama'];
	$jab = $_POST['jab'];
	$gaji = $_POST['gaji'];
	
	require_once('koneksi.php');
	
	$sql = "UPDATE employe SET nama = '$nama', jabatan = '$jab', gaji = '$gaji' WHERE id = '$id';";

	if(mysql_query($sql)){
		echo 'Update data karyawan sukses';
	} else{
		echo 'Update data karyawan gagal';
	}
}

mysql_close($con);
?>
