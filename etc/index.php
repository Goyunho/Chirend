<?php
$db_host = "localhost";
$db_user = "naneg93";
$db_passwd = "qwer1234";
$db_name = "naneg93";
$conn = mysqli_connect($db_host, $db_user, $db_passwd, $db_name);
if(mysqli_connect_errno($conn)){
    echo '001';
    echo "DB access fail : ".mysqli_connect_error();
    exit;
} else {
    echo "access\n";
}

if(is_null($_GET['contents_id']) && is_null($_GET['sex']) && is_null($_GET['age'])){
    echo 'no query';
    exit;
}

$sql = "INSERT INTO `sulmun` (`no`, `date`, `contents_id`, `sex`, `age`) VALUES (NULL, CURRENT_TIMESTAMP, '" . $_GET['contents_id'] . "', '" . $_GET['sex'] . "', '" . $_GET['age'] . "');";
$result = mysqli_query($conn, $sql);

if(!$result){
    echo '002 ';
    echo "DB Error, could not query the database\n";
    echo 'MySQL Error: '.mysqli_error();
    exit;
}
?>