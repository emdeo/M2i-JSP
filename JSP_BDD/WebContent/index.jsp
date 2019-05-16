<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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

<!-- Scripts JQuery (Ajax), Popper et BootStrap -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Bootstrap boutons avec icônes -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Fichier JS -->
<script src="./sources.js"></script>

</head>
<body>

	<div class="container align-items-center">
		<br>
		<h3>Table Société</h3>
		<br>

		<%
			// Réinitialiser les tables 'Societe' et 'Personne'

			DAO_Societe daos = new DAO_Societe();
			daos.Truncate();
			daos.Instanciate();

			DAO_Personne daop = new DAO_Personne();
			daop.Truncate();
			daop.Instanciate();
		%>

		<!-- Afficher la table "Societe" de la BDD "dp_formation" -->
		<table class="table">
			<thead>
				<tr>
					<th>ID Société</th>
					<th>Nom</th>
					<th>CA</th>
					<th>Activité</th>
					<th>Employés</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbodySociete">
				<%
					// Afficher le tableau de sociétés
					out.println(HTMLDynamique.TableauSocietes());
				%>
			</tbody>
		</table>

		<!-- Collapse : Modifier une société -->
		<div class="collapse" id="collapseSociete"></div>

		<div id="tblEmployes"></div>
	</div>

	<style>

/* Régler la largeur de colonne d'un tableau */
table .w-5 {
	width: 5%;
}

table .w-15 {
	width: 15%;
}

/* Mise en forme des boutons (couleurs) */
.btnSelect {
	background-color: DodgerBlue; /* Blue background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}

.btnUpdate {
	background-color: #28a745; /* Yellow background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}

.btnDelete {
	background-color: LightCoral; /* Red background */
	border: none; /* Remove borders */
	color: white; /* White text */
	padding: 12px 16px; /* Some padding */
	font-size: 16px; /* Set a font size */
	cursor: pointer; /* Mouse pointer on hover */
}

/* Background plus sombre quand on passe la souris sur le bouton */
.btnSelect:hover {
	background-color: RoyalBlue;
}

.btnUpdate:hover {
	background-color: #208537;
}

.btnDelete:hover {
	background-color: IndianRed;
}
</style>

</body>
</html>





















