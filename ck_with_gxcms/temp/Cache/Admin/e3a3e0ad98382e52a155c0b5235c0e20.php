<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>导出采集节点-<?php echo C("web_name");?></title>
<link rel='stylesheet' type='text/css' href='./views/css/admin_style.css'>
<link rel='stylesheet' type='text/css' href='./views/css/dialog.css'>
<link rel='stylesheet' type='text/css' href='./views/css/collect.css'>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="./views/js/jquery.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="./views/js/dialog.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="./views/js/collect.js"></script>
<script language="JavaScript" charset="utf-8" type="text/javascript" src="./views/js/formvalidator.js"></script>
<script type="text/javascript">
$(document).ready(
	function checkData() {			
	$.formValidator.initConfig({formid:"myform",autotip:true,onerror:function(msg,obj){art.dialog({content:msg,lock:true,width:'200',height:'50'})}});
	$("#file").formValidator({onshow:"请选择文件",onfocus:"请选择文件",oncorrect:""}).inputValidator({min:1,onerror:"请选择文件"});
	}
 );
function formcheck(){
   if($("#txt").attr("checked")==true){
		 //alert('#txt unchecked.');
		 $("#file").formValidator({onshow:"",onfocus:"请选择文件",oncorrect:""}).inputValidator({min:1,onerror:"请选择文件"});
		 $("#txtcode").formValidator({onshow:"",onfocus:"导入规则为空",oncorrect:""})
		 return false;
	}else{
		//alert('#txt checked.');
		 $("#txtcode").formValidator({onshow:"",onfocus:"导入规则为空",oncorrect:""}).inputValidator({min:1,onerror:"导入规则为空"})
		 $("#file").formValidator({onshow:"",onfocus:"请选择文件",oncorrect:""})
		 return false;
	}
}

</script>
</head>
<body>

<table width="98%" border="0" cellpadding="5" cellspacing="1" class="table">
<tr>
  <td colspan="6" class="table_title">
  <ul>
  <li><a  href="?s=Admin/CustomCollect/ListShow">采集节点管理</a> </li>
  <li><a href="?s=Admin/CustomCollect/Add">添加采集节点</a></li>
  <li><a href="?s=Admin/CustomCollect/Manage/action/import">导入采集规则</a></li>
  </ul>
  </td>
</tr>
</table>
<div class="ecode">
<?php if(empty($data)): ?><form action="" method="post"  name="myform" id="myform" enctype="multipart/form-data">
<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
<tr><td>
<ul class="import">
<li> 导入方式：</li>
<li class="on" id="tab_1" onclick="javascript:show_div('1')"><input type="radio" name="importmode" value="txt"   checked="checked"  id='txt' class="radio"   onclick="formcheck();" />直接导入txt文本</li>
<li id="tab_2"  onclick="javascript:show_div('2')"><input type="radio" name="importmode" value="code"  id='code' class="radio"  onclick="formcheck();" />导入源码</li>
</ul>
</td></tr>
  <tr>
    <td>
    <div id="show_div_1"><input type="file" name="file" id='file'   value="" /> 只支持.txt文件上传</div>
    <div id="show_div_2" style="display:none"><textarea rows="24" cols="90" name="txtcode"  id="txtcode"></textarea></div>
    </td>
  </tr>
  <tr>
    <td  style="padding-left:10px;"><input type="submit" name="submit" value="确定导入"  class="bginput"/></td>
  </tr>
</table>

</form>
<?php else: ?> 
<form action="" method="post" name="txtform" id='txtform'>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><textarea rows="24" cols="90" name="codedata" readonly="readonly"><?php echo ($data); ?></textarea></td>
  </tr>
  <tr>
    <td  style="padding-left:10px;"><input type="submit" name="submit" value="规则另存为"  onclick="txtform.action = '?s=Admin/CustomCollect/Manage/action/txtExport/nid/<?php echo ($nid); ?>'" class="bginput"/></td>
  </tr>
</table>
</form><?php endif; ?>



 
</div>
<style>
#footer, #footer a:link, #footer a:visited {
	clear:both;
	color:#0072e3;
	font:12px/1.5 Arial;
	margin-top:10px;
	text-align:center;
	white-space:nowrap;
}
</style>
<div id="footer">程序版本：<?php echo C("cms_var");?>&nbsp;&nbsp;&nbsp;&nbsp;Copyright © 2010-2011 All rights reserved</div>
</body>
</html>