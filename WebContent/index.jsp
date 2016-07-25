<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.InetAddress" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cluster test</title>
</head>
<body>

ip:
<%
InetAddress addr = InetAddress.getLocalHost();
System.out.println(addr.getHostAddress()+"   "+addr.getHostName() );
%>

<%=addr.getHostAddress() %>
<%=addr.getHostName() %>

<br>
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<br>

sessionid:
<%=request.getSession().getId() %>


<br>

++++++
<%
int count=0;
if(request.getSession().getAttribute("count")!=null){
	count = Integer.parseInt(request.getSession().getAttribute("count").toString());
	request.getSession().setAttribute("count",++count);
}else{
	request.getSession().setAttribute("count",count);
}

%>

<%=count %>


</body>
</html>