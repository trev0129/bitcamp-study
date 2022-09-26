<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bitcamp</title>
</head>
<body>
<h1>요청 오류!-JSP</h1>
<pre>
 <%
 
Exception e = (Exception) request.getAttribute("exception");
if (e != null) {
e.printStackTrace(new PrintWriter(out));
}

%>
</pre>
</body>
</html>