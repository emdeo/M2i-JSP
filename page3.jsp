<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fafiec10_DynWeb_Exemple1</title>
</head>
<body>

	<%!/*
		Retourne un tableau HTML contenant les noms des param�tres
		de la page et leur valeur.

		URL test = "http://localhost:8080/FAFIEC10_DYNWEB_Exemple1/page3.jsp?prenom=alice&nom=alpha&prenom=julien"
		*/
	String genererMapParametres(Map<String, String[]> prmMap) {
		String output = "<table><thead><tr><td>Key</td><td>Value</td></tr></thead><tbody>";

		// It�ration � travers les cl�s
		for (String key : prmMap.keySet()) {

			String[] strArr = (String[]) prmMap.get(key);

			// It�ration � travers les valeurs
			for (String val : strArr) {
				output += "<tr><td>" + key + "</td><td>" + val + "</td></tr>";
			}
		}

		output += "</tbody></table>";

		return output;
	}

	// Solution plus courte
	String genererMapParametres2(Map<String, String[]> prmMap) {
		String output = "<table><thead><tr><td>Key</td><td>Value</td></tr></thead><tbody>";

		// It�rer � travers les entr�es de la Map
		for (java.util.Map.Entry<String, String[]> entry : prmMap.entrySet()) {

			// It�rer � travers les valeurs de chaque entr�e
			for (String val : entry.getValue()) {
				output += "<tr><td>" + entry.getKey() + "</td><td>" + val + "</td></tr>";
			}
		}

		output += "</tbody></table>";

		return output;
	}%>

	<%
		/*
			R�cup�rer les noms de param�tres ET leur valeur.
			
			La m�thode getParameterMap() est utile quand plusieurs param�rtes
			ont le m�me nom
		*/
		Map<String, String[]> prmMap = request.getParameterMap();
		out.println(genererMapParametres2(prmMap));
	%>

</body>
</html>