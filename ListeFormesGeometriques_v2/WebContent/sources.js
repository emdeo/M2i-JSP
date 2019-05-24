$(document)
		.ready(
				function() {

					// Initialiser le tableau HTML
					recupererFormesGeometriques()

					// COMPORTEMENT DES INPUTS RADIO
					$("input[type=radio][name=inlineRadioOptions]")
							.on(
									"change",
									function() {
										if (this.id == 'rCarre') {
											$("#dimensions")
													.html(
															"<label for='txtCote' class='col-md-2 col-form-label text-right'>Cote :</label>"
																	+ "<div class='col-md-8'>"
																	+ "<input type='number' class='form-control' id='txtCote' name='txtCote'></div>")
										} else if (this.id == 'rCercle') {
											$("#dimensions")
													.html(
															"<label for='txtRayon' class='col-md-2 col-form-label text-right'>Rayon :</label>"
																	+ "<div class='col-md-8'>"
																	+ "<input type='number' class='form-control' id='txtRayon' name='txtRayon'></div>")
										} else if (this.id == 'rRectangle') {
											$("#dimensions")
													.html(
															"<label for='txtLongueur' class='col-md-2 col-form-label text-right'>Longueur :</label>"
																	+ "<div class='col-md-3'>"
																	+ "<input type='number' class='form-control' id='txtLongueur'>"
																	+ "</div>"
																	+ "<label for='txtLargeur' class='col-md-2 col-form-label text-right'>Largeur :</label>"
																	+ "<div class='col-md-3'>"
																	+ "<input type='number' class='form-control' id='txtLargeur'></div>")
										}
									})

					$("#cmdAjouter")
							.on(
									"click",
									function() {

										let data
										let url

										// Si l'utilisateur sélectionne le CARRE
										if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "carre") {
											data = {
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												cote : $("#txtCote").val()
											}
											url = "Servlet_Carre"

											alert("Servlet : " + url + "\nX : "
													+ data.X + ", Y : "
													+ data.Y + ", cote : "
													+ data.cote)
										}

										// Si l'utilisateur sélectionne le
										// CERCLE
										else if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "cercle") {
											data = {
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												rayon : $("#txtRayon").val()
											}
											url = "Servlet_Cercle"
										}

										// Si l'utilisateur sélectionne le
										// RECTANGLE
										else if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "rectangle") {
											data = {
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												largeur : $("#txtLargeur")
														.val(),
												longueur : $("#txtLongueur")
														.val()
											}
											url = "Servlet_Rectangle"
										}

										$
												.ajax({
													url : url,
													method : "get",
													data : data,

													success : function(result,
															status) {
														$("#tbody-Formes")
																.empty()
														recupererFormesGeometriques()

													},

													error : function(jqXHR,
															exception) {
														var msg = '';
														if (jqXHR.status === 0) {
															msg = 'Not connect.\n Verify Network.';
														} else if (jqXHR.status == 404) {
															msg = 'Requested page not found. [404]';
														} else if (jqXHR.status == 500) {
															msg = 'Internal Server Error [500].';
														} else if (exception === 'parsererror') {
															msg = 'Requested JSON parse failed.';
														} else if (exception === 'timeout') {
															msg = 'Time out error.';
														} else if (exception === 'abort') {
															msg = 'Ajax request aborted.';
														} else {
															msg = 'Uncaught Error.\n'
																	+ jqXHR.responseText;
														}
														alert(msg);
													},

												});
									})

					$("body").on(
							"click",
							".btnDessiner",
							function() {

								// let canvas =
								// document.getElementById("canvasForme")
								// //
								// équivaut à canvas[0].getContext('2d')
								let canvas = $("#canvasForme")
								let context = canvas[0].getContext('2d')
								let type = $(this).attr("data-typeForme")
								let IdPoint = $(this).attr("data-IdPoint");

								// Modifier le titre du modal
								$("#titreModalDessin")
										.html("Dessin de " + type)

								// Dimensions du canvas
								var width = canvas.attr("width")
								var height = canvas.attr("height")

								// Vider le canvas
								context.clearRect(0, 0, width, height)

								// Démarrer nouveau tracé
								context.beginPath()
								var url;

								if (type == "carre") {
									url = "Servlet_Carre"
								} else if (type == "cercle") {
									url = "Servlet_Cercle"
								} else if (type == "rectangle") {
									url = "Servlet_Rectangle"
								}

								// alert("id " + IdPoint)

								$.ajax({
									url : url,
									type : "Post",
									data : {
										IdPoint : IdPoint
									},
									ContentType : "json",

									success : function(resultat) {

										let x = resultat._coord.X + width/2
										let y = resultat._coord.Y + height/2

//										alert("keys : " + Object.keys(resultat)
//												+ "\nvalues : "
//												+ Object.values(resultat)
//												+ "\n_Coord : "
//												+ Object.keys(resultat._coord)+ "\n_Coord values : "
//												+ Object.values(resultat._coord))

										if (type == "carre") {

											context.fillRect(x, y,
													resultat._Cote,
													resultat._Cote)
										} else if (type == "cercle") {
											alert("Rayon = " + resultat._Rayon)
											context.arc(x, y, resultat._Rayon,
													0, 2 * Math.PI)
											context.fill()
										} else if (type == "rectangle") {
											context.fillRect(x, y,
													resultat._Largeur,
													resultat._Longueur)
										}
									}
								})

								// Clore le contexte
								context.closePath()

							})

				});
