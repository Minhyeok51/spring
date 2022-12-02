<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="insert" method = "post">
    	<input type ="text" name="id">
    	<input type ="text" name="name">
    	<button type = "submit">formRequest</button>
    </form>
</body>
</html>