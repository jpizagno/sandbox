<?php
    session_start();

    $config = parse_ini_file('config');
    $gaSql['user']       = $config['db_user'];
    $gaSql['password']   = $config['db_password'];
    $gaSql['db']         = $config['db_database'];
    $gaSql['server']     = $config['db_ip'];

    //unlink("login.txt");
    //$myfile = fopen("login.txt", "w") or die('fopen failed');
    //$conn=mysqli_connect($gaSql['server'], $gaSql['user'] ,$gaSql['password'],$gaSql['db']);
    //$json_data = array('message' => 'login information not correct');
    //if (!$conn) {
    //    error_log("\n failed to login:  user=".$gaSql['user'], 3, '/tmp/php.log');
    //    $_SESSION["username"] = '';
    //    $_SESSION["password"] = '';
    //    fwrite($myfile, "fail from login.php");
    //    $_SESSION["loginsuccess"] = "false";
    //} else {
    //    $json_data = array('message' => 'ok');
    //    fwrite($myfile, "ok");
    //    $_SESSION["loginsuccess"] = "true";
    //}
    //fclose($myfile);
    //echo json_encode($json_data);

     $json_data = array('message' => 'ok');
     echo json_encode($json_data);
    


?>
