<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String status=(String)request.getAttribute("flag");
	if(status==null){%>
	<h3 align="center" class="err">Session Out, Please Login Again</h3>
	<a href="/Assignment_Login_Rest">Login Again</a>
	
	<%}else if (status.equals("register_success")){%>
	<h3 align="center" class="err">Register Successfully</h3>
	<a href="/Assignment_Login_Rest/dashboard">Go To Dashboard</a>
	
	<%}else if (status.equals("login_success")){%>
	<h3 align="center" class="err">Login Successfully</h3>
	<a href="/Assignment_Login_Rest/dashboard">Go To Dashboard</a>
	
	<%}else if (status.equals("register_fail")){%>
	<h3 align="center" class="err">Register Fail, Try again</h3>
	<a href="/Assignment_Login_Rest/">Register Again</a>
	
	<%}else if (status.equals("login_fail")){%>
	<h3 align="center" class="err">Login Fail, Try again</h3>
	<a href="/Assignment_Login_Rest/">Login Again</a><%} else{ %>
	<h3 align="center" class="err">Some Error Occured, Please Try again</h3>
	<a href="/Assignment_Login_Rest/">Login/Register Again</a>
	<%} %>	
</body>
</html>