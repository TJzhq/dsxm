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
	$(function (){
		var yh_nch = getMyCookie("yh_nch");
		$("#yh_nch").text(yh_nch);
	});
	
	function getMyCookie(key){
		var val = "";
		var cookies =document.cookie.replace(/\s/,"");
		var cookie_array = cookies.split(";");
		
		for(i=0;i<cookie_array.length;i++){
			cookie = cookie_array[i].split("=");
			if(cookie[0] == key){
				val = cookie[1];
			}
		}
		return decodeURIComponent(val) ;
	}
</script>
<title>测试页面</title>
</head>
<body>
	<c:if test="${empty user}">
		<a href="tologin.do"><span style="color:red;" id="yh_nch">游客</span>请登陆</a>  注册
	</c:if>
	<c:if test="${not empty user}">
		欢迎${user.yh_nch} 订单列表  <a href="loginout.do">注销 </a>
	</c:if>
	

</body>
</html>