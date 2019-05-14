<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="m2i.BDD.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>M2i JSP - Exercice 3 (IMC)</title>

<!-- BootStrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

</head>
<body>

	<!-- Scriptlet de traitement -->
	<%!/* Format d'affichage dans la page HTML */
	String afficher(String label, Object valeur) {
		return "<div class='form-group row'><label class='col-sm-3 col-form-label'>" + label
				+ "</label><div class='col-sm-3'><input class='form-control' value='" + valeur
				+ "' readonly/></div></div>";
	}%>

	<div class="container align-items-center">
		<form method="get" action="./resultats.jsp">
			<div>

				<!-- Scriptlet de présentation -->
				<%
					Personne p1 = null;

					if ((request.getParameter("txtNom") != null) && (request.getParameter("txtPrenom") != null)
							&& (request.getParameter("txtPoids") != null) && (request.getParameter("txtTaille") != null)
							&& (request.getParameter("lstSexe") != null)) {

						String nom = request.getParameter("txtNom");
						String prenom = request.getParameter("txtPrenom");
						float poids = Float.parseFloat(request.getParameter("txtPoids"));
						float taille = Float.parseFloat(request.getParameter("txtTaille"));
						Sexe genre = Sexe.valueOf(request.getParameter("lstSexe"));

						p1 = new Personne(nom, prenom, taille, poids, genre);
					}

					if (p1 != null) {
						if (request.getParameter("cboxIMC") != null)
							out.println(afficher("IMC", p1.calculIMC()));
						if (request.getParameter("cboxPoidsMin") != null)
							out.println(afficher("Poids minimum sain", p1.poidsMin()));
						if (request.getParameter("cboxPoidsMax") != null)
							out.println(afficher("Poids maximum sain", p1.poidsMax()));
						if (request.getParameter("cboxPoidsIdeal") != null)
							out.println(afficher("Poids 'idéal'", p1.poidsIdeal()));
						if (request.getParameter("cboxDiagnostic") != null)
							out.println(afficher("Diagnostic", p1.diagnostic()));
					}
				%>
			</div>
		</form>
	</div>

</body>
</html>