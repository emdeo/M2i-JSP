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
		
		ligne.append($("<td></td>").text(listeForme[i]._Surface))
		ligne.append($("<td></td>").text(listeForme[i]._Perimetre))

		let type
		if(listeForme[i]._Longueur > 0){
			type = "rectangle"
		}
		else if(listeForme[i]._Cote > 0){
			type = "carre"
		}
		else if(listeForme[i]._Rayon > 0){
			type = "cercle"
		}
		
		ligne.append($("<td></td>").append(
				$("<button></button>").attr("class", 'btn btnDessiner')
						.attr('data-IdForme', listeForme[i]._ID_Forme)
						.attr('data-typeForme', type)
						.attr("data-toggle","modal")
						.attr("data-target","#modalDessin")
						.append($("<i></i>").attr("class", "fa fa-pencil"))))
						
						
		$("#tbody-Formes").append(ligne)
	}
}
