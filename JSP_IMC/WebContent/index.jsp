<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="m2i.jsp.*"%>
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

	<div class="container align-items-center">
		<form method="get" action="./resultats.jsp">
			<div>

				<!-- input Nom, checkbox IMC -->
				<div class="form-group row">
					<label for="txtNom" class="col-sm-1 col-form-label">Nom</label>
					<div class="col-sm-3">
						<input name="txtNom" class="form-control" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxIMC"> <label
							for="cboxIMC">IMC</label>
					</div>
				</div>

				<!-- input Prénom, checkbox Poids minimum -->
				<div class="form-group row">
					<label for="txtPrenom" class="col-sm-1 col-form-label">Prenom</label>
					<div class="col-sm-3">
						<input name="txtPrenom" class="form-control" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsMin"> <label
							for="cboxPoidsMin">Poids minimum sain</label>
					</div>
				</div>

				<!-- input Poids, checkbox Poids maximum -->
				<div class="form-group row">
					<label for="txtPoids" class="col-sm-1 col-form-label">Poids</label>
					<div class="col-sm-3">
						<input type="number" name="txtPoids" class="form-control" min="0"
							max="300" step="0.01" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsMax"> <label
							for="cboxPoidsMax">Poids maximum sain</label>
					</div>
				</div>

				<!-- input Taille, checkbox Poids idéal -->
				<div class="form-group row">
					<label for="txtTaille" class="col-sm-1 col-form-label">Taille</label>
					<div class="col-sm-3">
						<input type="number" name="txtTaille" class="form-control"
							min="0.00" max="2.59" step="0.01" />
					</div>
					<div class="col-sm-3">
						<input type="checkbox" name="cboxPoidsIdeal"> <label
							for="cboxPoidsIdeal">Poids "idéal"</label>
					</div>
				</div>

				<!-- input Sexe, checkbox Diagnostic -->
				<div class="form-group row">
					<label for="lstSexe" class="col-sm-1 col-form-label">Sexe</label>
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

				<input type="submit" class="form-control" id="cmdCalculer"
					value="Calculer" />
			</div>
		</form>
	</div>
</body>
</html>