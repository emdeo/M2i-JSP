function generetableauformes(listeForme) {

	// $("#tbody-Formes").empty()

	for (var i = 0; i < listeForme.length; i++) {
		var ligne = $("<tr></tr>")

		ligne.append($("<td></td>").text(listeForme[i]._ID_Point))
		ligne.append($("<td></td>").text(listeForme[i]._X))
		ligne.append($("<td></td>").text(listeForme[i]._Y))
		ligne.append($("<td></td>").text(listeForme[i]._Longueur))
		ligne.append($("<td></td>").text(listeForme[i]._Largeur))
		ligne.append($("<td></td>").text(listeForme[i]._Cote))
		ligne.append($("<td></td>").text(listeForme[i]._Rayon))

		ligne.append($("<td></td>").text(listeForme[i]._Surface))
		ligne.append($("<td></td>").text(listeForme[i]._Perimetre))

		let type
		if (listeForme[i]._Longueur > 0) {
			type = "rectangle"
		} else if (listeForme[i]._Cote > 0) {
			type = "carre"
		} else if (listeForme[i]._Rayon > 0) {
			type = "cercle"
		}

		// alert("Forme "+listeForme[i]._ID_Forme+" : "+type)

		ligne.append($("<td></td>").append(
				$("<button></button>").attr("class", 'btn btnDessiner').attr(
						'data-IdPoint', listeForme[i]._ID_Point).attr(
						'data-typeForme', type).attr("data-toggle", "modal")
						.attr("data-target", "#modalDessin").append(
								$("<i></i>").attr("class", "fa fa-pencil"))))

		$("#tbody-Formes").append(ligne)
	}
}

function recupererFormesGeometriques() {

	$.ajax({
		url : "Servlet_Commune",
		type : "get",
		contentType : "json",
		success : function(result, status) {
			// alert("Success du Servlet_Commune")
			generetableauformes(result)
		},
		error : function(jqXHR, exception) {
			var msg = 'Servlet_Commune:\n';
			if (jqXHR.status === 0) {
				msg += 'Not connect.\n Verify Network.';
			} else if (jqXHR.status == 404) {
				msg += 'Requested page not found. [404]';
			} else if (jqXHR.status == 500) {
				msg += 'Internal Server Error [500].';
			} else if (exception === 'parsererror') {
				msg += 'Requested JSON parse failed.';
			} else if (exception === 'timeout') {
				msg += 'Time out error.';
			} else if (exception === 'abort') {
				msg += 'Ajax request aborted.';
			} else {
				msg += 'Uncaught Error.\n' + jqXHR.responseText;
			}
			alert(msg);
		},
	})

}