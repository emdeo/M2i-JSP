<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- COMMENTAIRE JSP
	Importer une librairie Java --%>
<%@ page import="java.time.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fafiec10_DynWeb_Exemple1</title>
</head>
<body>

	<%
		out.println("Hello world! Today's " + LocalDate.now());
	%>

</body>
</html>