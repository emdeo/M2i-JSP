<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Librairies nécessaires à BootStrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Librairie d'icônes boutons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Fichier CSS personnalisé -->
<link rel="stylesheet" href="index.css">

<title>Insert title here</title>

<!-- BootStrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
	
<!-- Fichiers JS -->
<script src="utilitaires.js"></script>
<script src="sources.js"></script>

</head>
<body>

	<div class="container">
		<br>
		<h2>Formes géométriques</h2>
		<br>

		<table class="table">
			<thead>
				<tr>
					<th>ID point</th>
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
				<div class="container">
					<form>

						<div class="form-group row">
							<!-- Radios -->

							<div class="col" id="radioForme">
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="rCercle" value="cercle">
									<label class="form-check-label" for="inlineRadio1">Cercle</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="rCarre" value="carre"> <label
										class="form-check-label" for="inlineRadio2">Carré</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio"
										name="inlineRadioOptions" id="rRectangle" value="rectangle">
									<label class="form-check-label" for="inlineRadio3">Rectangle</label>
								</div>
							</div>



							<div class="col-9">

								<!-- COORDONNEES -->
								<div class="form-group row" id="coordonnees">
									<label for="txtAbscisse" class="col-md-2 col-form-label text-right">X</label>
									<div class="col-md-3">
										<input type="number" class="form-control" id="txtAbscisse"
											placeholder="abscisse" min="-150" max ="150">
									</div>

									<label for="txtOrdonnee" class="col-md-2 col-form-label text-right">Y</label>
									<div class="col-md-3">
										<input type="number" class="form-control" id="txtOrdonnee"
											placeholder="ordonnée" min="-150" max ="150">
									</div>
								</div>

								<!-- DIMENSIONS -->
								<div id="dimensions" class="form-group row"></div>

							</div>

							<!-- BOUTON 'AJOUTER' -->
							<div class="col">
								<button type="button" class="btn btn-primary" id="cmdAjouter">Ajouter</button>
							</div>

						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<!-- MODAL #modalDessin -->
	<div class="modal fade" id="modalDessin" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			
			<!-- TITRE DU MODAL (généré dynamiquement dans 'sources.js') -->
				<div class="modal-header">
					<h5 class="modal-title" id="titreModalDessin"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<!-- CORPS DU MODAL -->
				<div class="modal-body" id="modalbody-Dessin">
					<canvas id="canvasForme" width="300" height="300">
				</canvas>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fermer</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>