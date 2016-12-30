<?php
define ('HOST', 'localhost');
define ('USER', 'root');
define ('PASS', 't4uf1ks4j4');
define ('DB', 'crud');

$con = mysql_connect(HOST, USER, PASS) or die ('Tidak terkoneksi');
$dbcon = mysql_select_db(DB, $con) or die ('Database tidak ada');
?>
