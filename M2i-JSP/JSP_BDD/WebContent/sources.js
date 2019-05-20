/**
 * 
 */

$(document).ready(function() {
	$(".btnUpdate").on("click", function() {
		$.ajax({
			url : "MaServlet",
			type : "get",
			dataType : "html",
			data : "IdSociete=" + $(this).attr("data-idSociete"),
			success : function(code, status) {
				$("#tblResultat .table").html(code)
			}
		})
	})

	$.post('/deleteRoute', {
		id : 1,
		action : 'delete'
	});

	$(".btnDelete").on("click", function() {
		$.ajax({
			url : "MaServlet",
			type : "post", // remplacer "delete" par "post" ?
			dataType : "html",
			data : "IdSociete=" + $(this).attr("data-idSociete"),
			success : function(code, status) {
				$("#tbodySociete").empty().html(code)
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR, textStatus, errorThrown)
			}
		})
	})
})