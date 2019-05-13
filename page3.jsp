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
		Retourne un tableau HTML contenant les noms des paramètres
		de la page et leur valeur.

		URL test = "http://localhost:8080/FAFIEC10_DYNWEB_Exemple1/page3.jsp?prenom=alice&nom=alpha&prenom=julien"
		*/
	String genererMapParametres(Map<String, String[]> prmMap) {
		String output = "<table><thead><tr><td>Key</td><td>Value</td></tr></thead><tbody>";

		// Itération à travers les clés
		for (String key : prmMap.keySet()) {

			String[] strArr = (String[]) prmMap.get(key);

			// Itération à travers les valeurs
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

		// Itérer à travers les entrées de la Map
		for (java.util.Map.Entry<String, String[]> entry : prmMap.entrySet()) {

			// Itérer à travers les valeurs de chaque entrée
			for (String val : entry.getValue()) {
				output += "<tr><td>" + entry.getKey() + "</td><td>" + val + "</td></tr>";
			}
		}

		output += "</tbody></table>";

		return output;
	}%>

	<%
		/*
			Récupérer les noms de paramètres ET leur valeur.
			
			La méthode getParameterMap() est utile quand plusieurs paramèrtes
			ont le même nom
		*/
		Map<String, String[]> prmMap = request.getParameterMap();
		out.println(genererMapParametres2(prmMap));
	%>

</body>
</html>