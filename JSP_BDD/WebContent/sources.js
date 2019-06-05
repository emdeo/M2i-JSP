$(document)
    .ready(
        function () {

            // Les fonctions suivantes doivent nécessairement pointer
            // vers un ELEMENT PARENT STATIQUE, sans quoi on ne pourra
            // pas agir
            // sur les éléments enfants après qu'ils aient été supprimés
            // et
            // recréés

            // Dans le cas suivant, on cible les éléments de classe
            // 'btnSelect' et 'btnDelete' de l'élément '#tbodySociete'

            // Bouton AJOUTER : afficher un collapse qui permettra
            // d'ajouter une entrée au tableau de sociétés
            $("#btnAjouterSociete").on("click", function () {
                $.ajax({
                    url: "MaServlet",
                    type: "get",
                    dataType: "html",
                    data: "btnAction=AjouterSociete",
                    success: function (code, status) {
                        $("#collapseModifierSociete").empty()
                        $("#collapseAjouterSociete").html(code)
                    }
                })
            })

            // Bouton CONFIRMER : ajouter une entrée au tableau de
            // sociétés
            // (-> récupérer les infos du formulaire
            // '.formAjouterSociete'
            // à l'intérieur du div 'collapseAjouterSociete')
            $("#collapseAjouterSociete")
                .on(
                    "click",
                    "#btnConfirmerAjoutSociete",
                    function () {

                        // Le comportement par défaut d'un
                        // bouton submit
                        // est de relancer entièrement la page
                        event.preventDefault()
                        $
                            .ajax({
                                url: "MaServlet",
                                type: "get",
                                dataType: "html",

                                data: "IdSociete="
                                    + $(this)
                                        .attr(
                                            "data-idSociete")
                                    + "&btnAction=ConfirmerAjoutSociete&newNom="
                                    + $("#inputNom")
                                        .val()
                                    + "&newCA="
                                    + $("#inputCA")
                                        .val()
                                    + "&newActivite="
                                    + $(
                                        "#selectActivite")
                                        .val(),

                                success: function (code,
                                    status) {
                                    $(
                                        "#collapseAjouterSociete")
                                        .empty()
                                    $("#tbodySociete")
                                        .empty().html(
                                            code)
                                }
                            })
                    })

            // Bouton SELECT : afficher les employés d'une société
            $("#tbodySociete").on(
                "click",
                ".btnSelect",
                function () {
                    $.ajax({
                        url: "MaServlet",
                        type: "get",
                        dataType: "html",

                        // la valeur du paramètre btnAction
                        // détermine
                        // le comportement de la méthode doGet()
                        data: "IdSociete="
                            + $(this).attr("data-idSociete")
                            + "&btnAction=Select",
                        success: function (code, status) {
                            $("#tblEmployes").html(code)
                        }
                    })
                })

            // Bouton UPDATE : ouvrir un collapse qui permettra de
            // modifier une ligne de la table société
            $("#tbodySociete").on(
                "click",
                ".btnUpdate",
                function () {
                    $.ajax({
                        url: "MaServlet",
                        type: "get",
                        dataType: "html",

                        // la valeur du paramètre btnAction
                        // détermine le
                        // comportement de la méthode doGet()
                        data: "IdSociete="
                            + $(this).attr("data-idSociete")
                            + "&btnAction=Update",

                        success: function (code, status) {
                            $("#collapseAjouterSociete").empty()
                            $("#tblEmployes").empty()
                            $("#collapseModifierSociete")
                                .html(code)
                        }
                    })
                })

            // Bouton MODIFIER : modifier une ligne de la table société
            // (-> récupérer les infos du formulaire
            // 'formModifierSociete'
            // à l'intérieur du div 'collapseModifierSociete')
            $("#collapseModifierSociete").on(
                "click",
                "#btnModifierSociete",
                function () {

                    // Le comportement par défaut d'un bouton
                    // 'submit'
                    // est de relancer entièrement la page
                    event.preventDefault()

                    $.ajax({
                        url: "MaServlet",
                        type: "get",
                        dataType: "html",

                        data: "IdSociete="
                            + $("#btnModifierSociete").attr(
                                "data-idSociete")
                            + "&newNom=" + $("#inputNom").val()
                            + "&newCA=" + $("#inputCA").val()
                            + "&newActivite="
                            + $("#selectActivite").val()
                            + "&btnAction=ModifierSociete",

                        success: function (code, status) {
                            $("#tbodySociete").html(code)
                            $("#collapseModifierSociete").empty()
                            $("#tblEmployes").empty()
                        }
                    })
                })

            // Bouton DELETE : supprimer une ligne de la table société
            $("#tbodySociete").on(
                "click",
                ".btnDelete",
                function () {
                    $.ajax({
                        url: "MaServlet",
                        type: "get",
                        dataType: "html",

                        // la valeur du paramètre btnAction
                        // détermine le
                        // comportement de la méthode doGet()
                        data: "IdSociete="
                            + $(this).attr("data-idSociete")
                            + "&btnAction=Delete",

                        success: function (code, status) {
                            $("#tblEmployes").empty()
                            $("#tbodySociete").html(code)
                        }
                    })
                })

            $("#tblEmployes").on(
                "click",
                ".btnAjouterEmploye",
                function () {

                    alert("Action en cours : ajouter un employé")

                    $.ajax({
                        url: "MaServlet",
                        type: "get",
                        dataType: "html",

                        data: "IdSociete="
                            + $(this).attr("data-idSociete")
                            + "&btnAction=AjouterEmploye",

                        success: function (code, status) {
                            $("#exampleModalLabel").html("J'ai modifié le titre du modal test")
                        }
                    })
                })
        })
