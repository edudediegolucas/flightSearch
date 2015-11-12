<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="com.eduardo.flightSearch.beans.ResultFlight"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Search - Result</title>
</head>
<body>
	<h1>Result of your query</h1>
	<%
		ResultFlight[] array = (ResultFlight[])request.getAttribute("array");
		if(array.length>0){
	%>
	<h3 style="text-decoration: underline;">Flights</h3>
	<%
			for(int i=0;i<array.length;i++){
			
	%>
	<p>* <%=array[i].toString() %></p>
	<%
			}
		}else{
	%>
	<h3 style="text-decoration: underline;">There are 0 matches with your query. Please try again.</h3>
	<%		
		}
	%>
	<a href="/flightSearch">Back to index</a>
</body>
</html>