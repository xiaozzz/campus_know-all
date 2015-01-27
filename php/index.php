<?php
//<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />		//这样用来放在浏览器上调试，解决中文乱码
$conn = mysql_connect("localhost", "zzzkky", "philip") or die("连接数据库服务器失败！".mysql_error()); //连接MySQL服务器
mysql_select_db("campus",$conn);				//选择数据库campus_know-all
mysql_query("set names utf8");						//设置数据库编码格式utf8

$str = $_GET["type"];

switch ($str)
{
case "register"	:
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
	break;
case "login" :	
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
	break;
case "school_act_list" :
	$res = mysql_query("SELECT * FROM school_act;");
	while ($row = mysql_fetch_row($res))
	{
		//print_r($row);
		echo $row[0]."\n";
		//echo $row[1]."\n";
		$type = mysql_query	("SELECT content FROM school_act_type WHERE type_id = $row[1];");	
		//print_r($type);
		$tmp = mysql_fetch_row($type);
		echo $tmp[0]."\n";
		echo $row[2]."\n";	
	}
	break;
case "school_act_detail" :
	$str1 = $_GET["id"];
	$res = mysql_query("SELECT * FROM school_act WHERE act_id = '$str1';");
	$row = mysql_fetch_row($res);
	//print_r($row);
	$type = mysql_query	("SELECT content FROM school_act_type WHERE type_id = $row[1];");	
	//print_r($type);
	$tmp = mysql_fetch_row($type);
	echo $tmp[0]."\n";	
	//echo $row[1]."\n";
	echo $row[3]."\n";
	echo $row[4]."\n";
	echo $row[5]."\n";
	
	break;
case "entrust_list" :
	$str1 = $_GET["entrust_type"];
	$str2 =  $_GET["username"];
	if ($str1 == '0')
		$res = mysql_query("SELECT * FROM entrust WHERE from_id != '$str2';");
	else
		$res = mysql_query("SELECT * FROM entrust WHERE from_id = '$str2';");
	while ($row = mysql_fetch_row($res))
	{
		//print_r($row);
		echo $row[0]."\n";
		//echo $row[1]."\n";
		$type = mysql_query	("SELECT content FROM entrust_type WHERE type_id = $row[5];");	
		//print_r($type);
		$tmp = mysql_fetch_row($type);
		echo $tmp[0]."\n";
		echo $row[1]."\n";	
	}
	break;
case "entrust_detail" :
	$str1 =  $_GET["id"];
	$res = mysql_query("SELECT * FROM entrust WHERE en_id = '$str1';");
	$row = mysql_fetch_row($res);
	//print_r($row);
		
	echo $row[3]."\n";
	echo $row[4]."\n";	
	$type = mysql_query	("SELECT content FROM entrust_type WHERE type_id = $row[5];");	
	$tmp = mysql_fetch_row($type);	
	echo $tmp[0]."\n";	
	$nickname = mysql_query	("SELECT nickname FROM user_info WHERE id = '$row[2]';");	
	$tmp = mysql_fetch_row($nickname);
	echo $tmp[0]."\n";		
	echo $row[6]."\n";
	break;
case "get_reply" :
	$str1 =  $_GET["id"];
	$res = mysql_query("SELECT * FROM reply WHERE entrust_id = $str1;");
	while ($row = mysql_fetch_row($res))
	{

		$type = mysql_query	("SELECT nickname FROM user_info WHERE id = '$row[2]';");	
		$tmp = mysql_fetch_row($type);
		echo $tmp[0]."\n";
		echo $row[3]."\n";	
	}
	break;
case "send_reply" :
	$str1 =  $_GET["en_id"];
	$str2 =  $_GET["from_id"];
	$str3 =  $_GET["content"];	
	$res = mysql_query("INSERT INTO reply(entrust_id,from_id,content) VALUES($str1,'$str2','$str3');");
	if ($res)
		echo "success";
	else
		echo "fail";
	break;
case "send_entrust" :
	$str0 =  $_GET["description"];
	$str1 =  $_GET["username"];
	$str2 =  $_GET["time"];
	$str3 =  $_GET["place"];
	$str4 =  $_GET["e_type"];
	$str5 =  $_GET["event"];	
	$res = mysql_query("INSERT INTO entrust(description,from_id,time,place,type,event) 
	VALUES('$str0','$str1','$str2','$str3',$str4,'$str5');");
	if ($res)
		echo "success";
	else
		echo "fail";
	break;
default :
	echo "error(#no such type)";	
}
mysql_close($conn);
?>


