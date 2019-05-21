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

<!-- Import custom CSS -->
<link rel="stylesheet" href="./index.css">

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

	<%
		// Réinitialiser les tables 'Societe' et 'Personne'

		DAO_Societe daos = new DAO_Societe();
		daos.Truncate();
		daos.Instanciate();

		DAO_Personne daop = new DAO_Personne();
		daop.Truncate();
		daop.Instanciate();
	%>

	<div class="container align-items-center">
		<br>
		<h3>Table Société</h3>
		<br>

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



		<!-- COLLAPSE : Ajouter une société -->
		<button type='button' class='btn btn-outline-primary'
			id='btnAjouterSociete' data-toggle='collapse'
			data-target='#collapseAjouterSociete' aria-expanded='false'
			aria-controls='collapseExample'>Ajouter une société</button>
		<div class="collapse" id="collapseAjouterSociete"></div>



		<!-- COLLAPSE : Modifier une société -->
		<div class="collapse" id="collapseModifierSociete"></div>



		<!-- Tableau d'employés -->
		<div id="tblEmployes"></div>



		<!-- MODAL TEST -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">...</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save
							changes</button>
					</div>
				</div>
			</div>
		</div>



		<!-- MODAL : ajouter ou modifier un employé -->
		<%
			out.println(HTMLDynamique.GenererModalEmploye());
		%>

	</div>
</body>
</html>





















