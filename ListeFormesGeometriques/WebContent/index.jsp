<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>

<!-- BootStrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	<script src="utilitaires.js"></script>
	<script src="sources.js"></script>

</head>
<body>

	<div class="container">
		<br>
		<h2>Liste des formes géometriques</h2>
		<br>

		<table class="table">
			<thead>
				<tr>
					<th>X</th>
					<th>Y</th>
					<th>Longueur</th>
					<th>Largeur</th>
					<th>Côtés</th>
					<th>Rayon</th>
					<th>Surface</th>
					<th>Périmètre</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbody-Formes">
			</tbody>
		</table>

		<div>
			<button class="btn btn-primary form-control" type="button"
				data-toggle="collapse" data-target="#collapseNouveau"
				aria-expanded="false" aria-controls="collapseExample">
				Nouveau</button>
		</div>





		<div class="collapse" id="collapseNouveau">
			<div class="card card-body">
				<div class="row justify-content-md-center">
					<form>

						<div class="form-group row">
							<!-- Radios -->
							<div class="col col-md-2">
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="option1">
									<label class="form-check-label" for="inlineRadio1">Cercle</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="option2">
									<label class="form-check-label" for="inlineRadio2">Carré</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="inlineRadio3" value="option3">
									<label class="form-check-label" for="inlineRadio3">Rectangle</label>
								</div>
							</div>



							<div class="col col-md-8">
								<!-- Coordonnées -->
								<div class="form-group row" id="coordonnees">
									<label for="txtAbscisse" class="col-md-1 col-form-label">X</label>
									<div class="col-md-3">
										<input type="number" class="form-control" id="txtAbscisse"
											placeholder="abscisse">
									</div>

									<label for="txtOrdonnee" class="col-md-1 col-form-label">Y</label>
									<div class="col-md-3">
										<input type="number" class="form-control" id="txtOrdonnee"
											placeholder="ordonnée">
									</div>
								</div>

								<div id="dimensions"></div>
							</div>

							<div class="col col-md-2">
								<button type="button" class="btn btn-primary">Ajouter</button>
							</div>

						</div>
					</form>
				</div>
				<!-- "Rayons", "côtés", "longueur" -->


			</div>
		</div>
	</div>
</body>
</html>