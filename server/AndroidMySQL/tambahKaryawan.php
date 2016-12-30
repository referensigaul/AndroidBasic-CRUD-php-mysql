<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
	$nama = $_POST['nama'];
	$jab = $_POST['jab'];
	$gaji = $_POST['gaji'];
	
	$sql = "INSERT INTO employe (nama, jabatan, gaji) VALUES ('$nama', '$jab', '$gaji')";
	
	require_once('koneksi.php');
	
	if(mysql_query($sql)){
		echo 'Sukses menambahkan karyawan';
	} else{
		echo 'Gagal menambahkan karyawan';
	}
	
	mysql_close($con);
}
?>
