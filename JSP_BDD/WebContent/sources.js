$(document).ready(
		function() {

			// Les fonctions suivantes doivent nécessairement pointer vers un
			// ELEMENT PARENT STATIQUE, sans quoi on ne pourra pas agir sur les
			// éléments enfants après qu'ils aient été supprimés et recréés

			// Dans le cas suivant, on cible les éléments de classe 'btnSelect'
			// et 'btnDelete' de l'élément '#tbodySociete'

			// Bouton SELECT : afficher les employés d'une société
			$("#tbodySociete").on(
					"click",
					".btnSelect",
					function() {
						$.ajax({
							url : "MaServlet",
							type : "get",
							dataType : "html",

							// la valeur du paramètre btnAction détermine le
							// comportement de la méthode doGet()
							data : "IdSociete="
									+ $(this).attr("data-idSociete")
									+ "&btnAction=Select",
							success : function(code, status) {
								$("#tblEmployes").html(code)
							}
						})
					})

			// Bouton UPDATE : ouvir un collapse qui permettra de modifier une
			// ligne de la table société
			$("#tbodySociete").on(
					"click",
					".btnUpdate",
					function() {
						$.ajax({
							url : "MaServlet",
							type : "get",
							dataType : "html",

							// la valeur du paramètre btnAction détermine le
							// comportement de la méthode doGet()
							data : "IdSociete="
									+ $(this).attr("data-idSociete")
									+ "&btnAction=Update",
							success : function(code, status) {
								$("#collapseSociete").html(code)
							}
						})
					})

			// Bouton MODIFIER : modifier une ligne de la table société
			$("#formModifierSociete").on("submit", function(e) {
				
				e.preventDefault();
				var donnees = $(this).serialize(); // On créer une variable content le formulaire sérialisé
				
				$.ajax({
					url : "MaServlet",
					type : "get",
					dataType : "html",
					
					data : donnees,
					
//					data : ({ // JSON.stringify() ??????
//						IdSociete : $(this).attr("data-idSociete"),
//						btnAction : 'ModifierSociete',
//						newNom : $("#inputNom").val(),
//						newCA : $("#inputCA").val(),
//						newActivite : $("#selectActivite").val()
//					}),
					success : function(code, status) {
						alert(IdSociete,btnAction,newNom,newCA,newActivite)
						$("#collapseSociete").empty()
					}
				})
			})

			// Bouton DELETE : supprimer une ligne de la table société
			$("#tbodySociete").on(
					"click",
					".btnDelete",
					function() {
						$.ajax({
							url : "MaServlet",
							type : "get",
							dataType : "html",

							// la valeur du paramètre btnAction détermine le
							// comportement de la méthode doGet()
							data : "IdSociete="
									+ $(this).attr("data-idSociete")
									+ "&btnAction=Delete",
							success : function(code, status) {
								$("#tblEmployes").empty()
								$("#tbodySociete").html(code)
							}
						})
					})
		})