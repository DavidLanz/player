<?php
/**
 * Created by PhpStorm.
 * User: Terwer
 * Date: 13-12-8
 * Time: 上午9:13
 */
include "D:/MyCode/ckplayer/framework/smarty/libs/Smarty.class.php";
//include "/home/v78/domains/xinvalue.com/public_html/framework/smarty/libs/Smarty.class.php";
$tpl=new Smarty;
$tpl->template_dir="D:/MyCode/ckplayer/tpl";
//$tpl->template_dir="/home/v78/domains/xinvalue.com/public_html/ckplayer/tpl";
$tpl->compile_dir="D:/MyCode/ckplayer/com";
//$tpl->compile_dir="/home/v78/domains/xinvalue.com/public_html/ckplayer/com";
$tpl->left_delimiter="<{";
$tpl->right_delimiter="}>";
?>