<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="java.util.*"%>
<%@ page import="example.demo002.servletMVCdemo.dto.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 	<table> -->
<!-- 		<tbody> -->
<%-- 			<c:forEach var="du" items="${unitList}"> --%>
<!-- 				<tr> -->
<%-- 					<td>${du.no }</td> --%>
<%-- 					<td>${du.id }</td> --%>
<!-- 					<td>{du.name }</td> -->
<!-- 				</tr> -->
<!-- 			</c:forEach> -->
<!-- 		</tbody> -->
<!-- 	</table> -->
	<%
	List<DemoUnit> unitList = (ArrayList<DemoUnit>) request.getAttribute("unitList");
	for (DemoUnit du : unitList) {
		out.println("번호: "+du.getNo() + " " + "아이디: "+du.getId() + " " + "이름: "+du.getName());
		out.println("<br/>");
	}
	%>
</body>
</html>