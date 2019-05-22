function generetableauformes(listeForme)
{

	$("#tbody-Formes").empty()

	for (var i = 0; i < listeForme.length; i++) {

		var ligne = $("<tr></tr>")

		ligne.append($("<td></td>").text(listeForme[i]._ID_Forme))
		ligne.append($("<td></td>").text(listeForme[i]._X))
		ligne.append($("<td></td>").text(listeForme[i]._Y))
		ligne.append($("<td></td>").text(listeForme[i]._Longueur))
		ligne.append($("<td></td>").text(listeForme[i]._Largeur))
		ligne.append($("<td></td>").text(listeForme[i]._Cote))
		ligne.append($("<td></td>").text(listeForme[i]._Rayon))
		ligne.append($("<td></td>").text("Surface"))
		ligne.append($("<td></td>").text("Perimètre"))

		ligne.append($("<td></td>").append(
				$("<button></button>").attr("class", 'btn dessiner')
						.attr('data-idForme', listeForme[i]._ID_Forme)
						.text("Dessiner")));
		$("#tbody-Formes").append(ligne)
	}
}
