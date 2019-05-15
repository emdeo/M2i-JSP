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

</head>
<body>

	<div class="container align-items-center">
		<form method="get" action="./resultats.jsp">
			<div>

				<!-- input ID_Personne -->
				<div class="form-group row">
					<label for="txtIDPersonne" class="col-sm-2 col-form-label">ID
						Personne</label>
					<div class="col-sm-3">
						<input name="txtIDPersonne" class="form-control" value="99" />
					</div>
				</div>

				<!-- input Nom, checkbox IMC -->
				<div class="form-group row">
					<label for="txtNom" class="col-sm-2 col-form-label">Nom</label>
					<div class="col-sm-3">
						<input name="txtNom" class="form-control" value="Dupond" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxIMC"> <label
							for="cboxIMC">IMC</label>
					</div>
				</div>

				<!-- input Prénom, checkbox Poids minimum -->
				<div class="form-group row">
					<label for="txtPrenom" class="col-sm-2 col-form-label">Prenom</label>
					<div class="col-sm-3">
						<input name="txtPrenom" class="form-control" value="Tintin" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsMin"> <label
							for="cboxPoidsMin">Poids minimum sain</label>
					</div>
				</div>

				<!-- input Poids, checkbox Poids maximum -->
				<div class="form-group row">
					<label for="txtPoids" class="col-sm-2 col-form-label">Poids</label>
					<div class="col-sm-3">
						<input type="number" name="txtPoids" class="form-control" min="0"
							max="300" step="0.01" value="66" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsMax"> <label
							for="cboxPoidsMax">Poids maximum sain</label>
					</div>
				</div>

				<!-- input Taille, checkbox Poids idéal -->
				<div class="form-group row">
					<label for="txtTaille" class="col-sm-2 col-form-label">Taille</label>
					<div class="col-sm-3">
						<input type="number" name="txtTaille" class="form-control"
							min="0.00" max="2.59" step="0.01" value="1.7" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsIdeal"> <label
							for="cboxPoidsIdeal">Poids "idéal"</label>
					</div>
				</div>

				<!-- input Sexe, checkbox Diagnostic -->
				<div class="form-group row">
					<label for="lstSexe" class="col-sm-2 col-form-label">Sexe</label>
					<div class="col-sm-3">

						<!-- Générer autant d'options qu'il y a des valeurs dans l'enum Sexe -->
						<select name="lstSexe" class="form-control">
							<%
								for (Sexe s : Sexe.values()) {
									out.println("<option value='" + s.name() + "'>" + s.name() + "</option>");
								}
							%>
						</select>
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxDiagnostic"> <label
							for="cboxDiagnostic">Diagnostic</label>
					</div>
				</div>

				<!-- input ID_Societe -->
				<div class="form-group row">
					<label for="txtIDSociete" class="col-sm-2 col-form-label">ID
						Société</label>
					<div class="col-sm-3">
						<input name="txtIDSociete" class="form-control" value="1" />
					</div>
				</div>

				<button type="submit" class="form-control btn btn-primary col-sm-8"
					id="cmdCalculer">Calculer</button>

			</div>
		</form>

		<br>
		<h3>Table Société (bdd "dp_formation")</h3>
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
				</tr>
			</thead>
			<tbody id="tbodySociete">
				<%
					/* Récupérer les entrées de la table */
					DAO_Societe daos = new DAO_Societe();
					java.util.ArrayList<Societe> lstSoc = daos.ReadAll();

					/* Afficher les entrées de la table */
					for (Societe s : lstSoc) {
						String IDSoc = "<td class='w-15'>" + s.get_ID_Societe() + "</td>";
						String Nom = "<td class='w-15'>" + s.get_Nom() + "</td>";
						String CA = "<td class='w-15'>" + s.get_CA() + "</td>";
						String Act = "<td class='w-15'>" + s.get_Activite() + "</td>";
						String Empl = "<td class='w-15'>" + s.get_Nb_Employes() + "</td>";

						String btnUpdate = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
								+ "' class='btnUpdate'><i class='fa fa-bars'></i></button></td>";
								
						String btnDelete = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
								+ "'class='btnDelete'><i class='fa fa-trash'></i></button></td>";

						out.println("<tr>" + IDSoc + Nom + CA + Act + Empl + btnUpdate + btnDelete + "</tr>");
					}
				%>
			</tbody>
		</table>

		<div id="tblResultat">
			<table class="table">
			</table>
		</div>
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
.btnUpdate {
	background-color: DodgerBlue; /* Blue background */
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
.btnUpdate:hover {
	background-color: RoyalBlue;
}

.btnDelete:hover {
	background-color: IndianRed;
}
</style>

	<script src="./sources.js"></script>

</body>
</html>





















