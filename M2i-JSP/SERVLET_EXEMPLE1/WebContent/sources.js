/**
 * 
 */

$(document).ready(
		function() {
			$("#cmdEnvoyer").on(
					"click",
					function() {
						$.ajax({
							url : "MaServlet",
							type : "get",
							data : "txtNombre1=" + $("#txtNombre1").val()
									+ "&txtNombre2=" + $("#txtNombre2").val(),
							dataType : "html",
							success : function(code, status) {
								$("#resultat").html(code)
							}
						})
					})
		})