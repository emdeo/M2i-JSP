/**
 * 
 */

$(document).ready(function() {
	$(".btnSelect").on("click", function() {
		$.ajax({
			url : "MaServlet",
			type : "get",
			dataType : "html",
			
			// la valeur du paramètre btnAction détermine le comportement de la méthode doGet()
			data : "IdSociete=" + $(this).attr("data-idSociete")+"&btnAction=Select",
			success : function(code, status) {
				$("#tblResultat .table").html(code)
			}
		})
	})

	$(".btnDelete").on("click", function() {
		$.ajax({
			url : "MaServlet",
			type : "get", // remplacer "delete" par "get"
			dataType : "html",
			
			// la valeur du paramètre btnAction détermine le comportement de la méthode doGet()
			data : "IdSociete=" + $(this).attr("data-idSociete")+"&btnAction=Delete",
			success : function(code, status) {
				$("#tbodySociete").empty().html(code)
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR, textStatus, errorThrown)
			}
		})
	})
})