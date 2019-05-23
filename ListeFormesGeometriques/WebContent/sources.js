$(document)
		.ready(
				function() {

					$.ajax({
						url : "Servlet_geom",
						type : "get",
						contentType : "json",
						success : function(result, status) {
							generetableauformes(result);
						},
						error : function(jqXHR, exception) {
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
								msg = 'Uncaught Error.\n' + jqXHR.responseText;
							}
							alert(msg);
						},

					});

					// COMPORTEMENT DES INPUTS RADIO
					$("input[type=radio][name=inlineRadioOptions]")
							.on(
									"change",
									function() {
										if (this.id == 'rCarre') {
											$("#dimensions")
													.html(
															"<label for='txtCote' class='col-md-2 col-form-label'>Cote :</label>"
																	+ "<div class='col-md-8'>"
																	+ "<input type='number' class='form-control' id='txtCote' name='txtCote'></div>")
										} else if (this.id == 'rCercle') {
											$("#dimensions")
													.html(
															"<label for='txtRayon' class='col-md-2 col-form-label'>Rayon :</label>"
																	+ "<div class='col-md-8'>"
																	+ "<input type='number' class='form-control' id='txtRayon' name='txtRayon'></div>")
										} else if (this.id == 'rRectangle') {
											$("#dimensions")
													.html(
															"<label for='txtLongueur' class='col-md-2 col-form-label'>Longueur</label>"
																	+ "<div class='col-md-3'>"
																	+ "<input type='number' class='form-control' id='txtLongueur'>"
																	+ "</div>"
																	+ "<label for='txtLargeur' class='col-md-2 col-form-label'>Largeur</label>"
																	+ "<div class='col-md-3'>"
																	+ "<input type='number' class='form-control' id='txtLargeur'></div>")
										}
									})

					$("#cmdAjouter")
							.on(
									"click",
									function() {

										let data

										if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "carre") {
											data = {
												type : "carre",
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												cote : $("#txtCote").val()
											}
										} else if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "cercle") {
											data = {
												type : "cercle",
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												rayon : $("#txtRayon").val()
											}
										} else if ($(
												"input[name=inlineRadioOptions]:checked",
												"#radioForme").val() == "rectangle") {
											data = {
												type : "rectangle",
												X : $("#txtAbscisse").val(),
												Y : $("#txtOrdonnee").val(),
												largeur : $("#txtLargeur")
														.val(),
												longueur : $("#txtLongueur")
														.val()
											}
										}

										$
												.ajax({
													url : "Servlet_geom",
													type : "get",
													data : data,
													contentType : "json", // majuscules
													// ?

													success : function(result,
															status) {
														generetableauformes(result);
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

								$("#titreModalDessin")
										.html("Dessin de " + type)

								// Vider le canvas
								context.clearRect(0, 0, canvas.attr("width"),
										canvas.attr("height"))

								// Démarrer nouveau tracé
								context.beginPath()

								$.ajax({

									url : "Servlet_geom",
									type : "get",
									data : {
										IdForme : $(this).attr("data-IdForme"),
										TypeForme : $(this).attr(
												"data-typeForme")
									},
									contentType : "json",

									success : function(resultat) {

										let x = resultat._X
										let y = resultat._Y

										if (type == "carre") {

											context.fillRect(x, y,
													resultat._Cote,
													resultat._Cote)
										} else if (type == "cercle") {
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
