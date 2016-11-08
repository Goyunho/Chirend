<?
require_once('mysql_class.php'); //mysql 연결

if(is_null($_GET['contents_id']) && is_null($_GET['sex']) && is_null($_GET['age'])){
    //echo 'no query';
} else{
    $sql = "INSERT INTO `sulmun` (`no`, `date`, `contents_id`, `sex`, `age`) VALUES (NULL, CURRENT_TIMESTAMP, '" . $_GET['contents_id'] . "', '" . $_GET['sex'] . "', '" . $_GET['age'] . "');";
    $result = mysqli_query($conn, $sql);

    if(!$result){
        echo '002 ';
        echo "DB Error, could not query the database\n";
        echo 'MySQL Error: '.mysqli_error();
        exit;
    }
}
?>