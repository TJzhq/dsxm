<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/icon" href="image/jd.ico">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var flbh2="${flbh2}";
		
		$.get("get_attr_list.do",{flbh2:flbh2},function(data){
			$("#sxlist").html(data);
		});
		$.get("get_sku_list_inner.do",{flbh2:flbh2},function(data){
			$("#skulist").html(data);
		});
	})
	function shaixuan(shxm_id,shxzh_id,shxzhmch) {
		$("#sxtj").append("<input type='hidden' name='attr_param' type='text' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'/>"+" "+shxzhmch);
		getparam();
	}
	function getparam(){
		// 二级分类id
		var flbh2 = "${flbh2}";
		
		// 参数，jquery数组
		var inputs = $("#sxtj input[name='attr_param']");

		// 声明一个查询字符串
		var param = "flbh2="+flbh2;
		
		// 将jquery数组转化为js的数组对象
		$(inputs).each(function(i,data){			
			var json = jQuery.parseJSON(data.value);
			param= param +"&list_pro_value["+i+"].shxm_id="+json.shxm_id+"&list_pro_value["+i+"].shxzh_id="+json.shxzh_id;
		});
		
 		$.get("get_sku_list_by_attr.do",param, function(html) {
			$("#skulist").html(html);
		}); 
	}
</script>
<title>测试页面</title>
</head>
<body>
	
	<hr/>
	<div id="sxlist"></div>
	
	<hr/>
	商品列表 <div id="skulist"> </div>
	
	

</body>
</html>