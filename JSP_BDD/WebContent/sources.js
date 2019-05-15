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

	$(".btnDelete").on("click", function() {
		$.ajax({
			url : "MaServlet",
			type : "delete",
			dataType : "html",
			data : "IdSociete=" + $(this).attr("data-idSociete"),
			success : function(code, status) {
				alert(status)
				$("#tbodySociete").empty().html(code)
			},
			error:function(jqXHR, textStatus, errorThrown){
				alert(jqXHR, textStatus, errorThrown)
			}
		})
	})
})