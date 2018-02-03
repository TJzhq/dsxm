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

	$(function() {
		$.getJSON("js/json/class_1.js", function(data) {
			$.each(data, function(i, json) {
				$("#class_list_class1").append(
						"<li onmouseover='class2("+json.id+")' value="+json.id+">" + json.flmch1
								+ "</li>");
				
			})

		});

	});

	function class2(id) {
		$.getJSON("js/json/class_2_" + id + ".js", function(data) {
			$("#class_list_class2").empty();
			$.each(data, function(i, json) {

				$("#class_list_class2").append(
						"<li value="+json.id+"><a href='tolist.do?flbh2="+json.id+"'>" + json.flmch2
								+ "</a></li>");

			})

		});

	}
</script>
<title>测试页面</title>
</head>
<body>
	<ul id="class_list_class1" style="float:left;width:70px;"></ul>
	<ul id="class_list_class2" style="float:left;width:70px;"></ul>
	

</body>
</html>