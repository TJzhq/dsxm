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
	
</script>
<title>测试页面</title>
</head>
<body>
	<div id="sxtj" > 属性选择: </div>
	<c:forEach items="${attr_list}" var="attr"  varStatus="s">
		<div>${attr.shxm_mch} : 
		<c:forEach items="${attr.list_value}" var="val">
			<a href="javascript:shaixuan(${attr.id},${val.id },'${val.shxzh} ${val.shxzh_mch}');">${val.shxzh} ${val.shxzh_mch}</a>    
		</c:forEach></div>
	</c:forEach>

</body>
</html>