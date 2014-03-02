<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.blstream.myhoard.db.dao.CollectionDAO"%>
<%@page import="com.blstream.myhoard.db.model.CollectionDS"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload</title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/media/"
		enctype="multipart/form-data">
		Nazwa: <input type="text" name="name" /> <br /> <input type="file"
			name="file"/ > <br /> <br /> <input type="submit"
			value="Wyślij" />
	</form>

	<img src="${pageContext.request.contextPath}/media/a" />


</body>
</html>
