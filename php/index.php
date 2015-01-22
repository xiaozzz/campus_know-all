<?php
$conn = mysql_connect("localhost", "zzzkky", "philip") or die("连接数据库服务器失败！".mysql_error()); //连接MySQL服务器
mysql_select_db("campus",$conn);				//选择数据库campus_know-all
//mysql_query("set names utf8");						//设置数据库编码格式utf8



$str = $_GET["type"];

if ($str == "register")
{
	$str1 = $_GET["id"];
	$str2 = $_GET["pwd"];
	$str3 = $_GET["nickname"];
	$res = mysql_query("INSERT INTO user_info VALUES('$str1','$str2','$str3');");
	if ($res){
		echo "success";
	}
	else{
		echo "fail";	
	}
}
else
if ($str == "login")
{
	$str1 = $_GET["id"];
	$str2 = $_GET["pwd"];		
	$res = mysql_query("SELECT * FROM user_info WHERE id = '$str1' and pwd = '$str2';");
	if ($res){
		echo "success"."\n";
	}
	else{
		echo "fail"."\n";	
	}
	//$row = mysql_fetch_assoc($res);
	$row = mysql_fetch_row($res);
	$tmp[] = $row;
	echo $tmp[0][2];
	//print_r($tmp);
}
?>


