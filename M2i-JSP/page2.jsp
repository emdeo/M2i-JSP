<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Enumeration"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fafiec10_DynWeb_Exemple1</title>
</head>
<body>

	<%--
		L'expression ci-dessous sert uniquement pour les DECLARATIONS (point d'exclamation).
		On l'utilise pour les variables, les objets et les fonctions.
	--%>
	<%!int[] lstEntiers = { 3, 1, 4, 1, 5, 9 };

	String genereTableauHTML(int[] tableau) {
		String output = "<table><tr><td>Nombre</td></tr>";

		for (int elem : tableau) {
			output += "<tr><td>" + elem + "</td></tr>";
		}

		output += "</table>";
		return output;
	}

	String genereTableauHTML2(int[] tableau) {
		String output = "Nombre<br>";

		for (int elem : tableau) {
			output += elem + "<br>";
		}

		return output;
	}%>

	<%
		// Afficher les éléments d'un tableau
		out.println(genereTableauHTML2(lstEntiers));

		// Récupérer la valeur d'un paramètre dont on saisit le nom
		out.println(request.getParameter("Nom") + "<br>");

		// Récupérer le nom de tous les paramètres de la page
		Enumeration<String> prmNames = request.getParameterNames();
		while (prmNames.hasMoreElements()) {
			out.println(prmNames.nextElement());
		}
	%>

</body>
</html>