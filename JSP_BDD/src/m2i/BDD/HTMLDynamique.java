package m2i.BDD;

import java.util.ArrayList;

public class HTMLDynamique {

	/**
	 * Renvoie un tableau HTML de la table "Societe" sous forme de chaîne de
	 * caractères.
	 * 
	 * @return
	 */
	public static String TableauSocietes() {

		String output = "";

		DAO_Societe daos = new DAO_Societe();
		ArrayList<Societe> lstSoc = daos.ReadAll();

		for (Societe s : lstSoc) {
			String IDSoc = "<td class='w-15'>" + s.get_ID_Societe() + "</td>";
			String Nom = "<td class='w-15'>" + s.get_Nom() + "</td>";
			String CA = "<td class='w-15'>" + s.get_CA() + "</td>";
			String Act = "<td class='w-15'>" + s.get_Activite() + "</td>";
			String Empl = "<td class='w-15'>" + s.get_Nb_Employes() + "</td>";

			String btnSelect = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
					+ "' class='btn btn-default btnSelect'><i class='fa fa-search'></i></button></td>";

			String btnUpdate = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
					+ "' class='btnUpdate' data-toggle='collapse' data-target='#collapseModifierSociete'"
					+ "aria-expanded='false' aria-controls='collapseExample'><i class='fa fa-pencil'></i></button></td>";

			String btnDelete = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
					+ "' class='btnDelete'><i class='fa fa-remove'></i></button></td>";

			output += "<tr>" + IDSoc + Nom + CA + Act + Empl + btnSelect + btnUpdate + btnDelete + "</tr>";
		}

		return output;
	}

	/**
	 * Générer un formulaire dans lequel l'utilisateur peut modifier les
	 * informations d'une société.
	 * 
	 * @param idSociete
	 * @return
	 */
	public static String formulaireUpdate(int idSociete) {

		String output = "<br><h4>Modifier une société</h4><br><form class='formModifierSociete' method="
				+ "'get' data-idSociete='" + idSociete + "'>";

		DAO_Societe daos = new DAO_Societe();
		Societe s = daos.Read(idSociete);

		String IDSoc = "<div class='form-group row'>"
				+ "<label for='staticIdSociete' class='col-sm-2 col-form-label'>ID Société</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdSociete' value='"
				+ s.get_ID_Societe() + "'>" + "</div></div>";

		String Nom = "<div class='form-group row'>"
				+ "<label for='inputNom' class='col-sm-2 col-form-label'>Nom</label>" + "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputNom' placeholder='" + s.get_Nom() + "'>"
				+ "</div></div>";

		String CA = "<div class='form-group row'>"
				+ "<label for='inputCA' class='col-sm-2 col-form-label'>Chiffre d'affaire</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputCA' min='0' max='1000000' step='0.1'"
				+ "placeholder='" + s.get_CA() + " millions'>" + "</div></div>";

		String Act = "<div class='form-group row'>"
				+ "<label for='selectActivite' class='col-sm-2 col-form-label'>Choisir une activité :</label>"
				+ "<div class='col-sm-8'>" + "<select id='selectActivite' class='form-control'>";

		for (Activites a : Activites.values()) {
			Act += "<option value='" + a + "'>" + a + "</option>";
		}
		Act += "</select></div></div>";

		String button = "<button class='btn btn-success' id='btnModifierSociete' data-idSociete='" + s.get_ID_Societe()
				+ "'>Modifier</button>";

		output += IDSoc + Nom + CA + Act + button + "</form>";

		return output;
	}

	/**
	 * Générer un formulaire dans lequel l'utilisateur peut ajouter une nouvelle
	 * société.
	 * 
	 * @return
	 */
	public static String formulaireAjouterSociete() {

		DAO_Societe daos = new DAO_Societe();

//		Enregistrer l'ID Société le plus élevé pour attribuer un ID à la nouvelle société
		ArrayList<Societe> lstSoc = daos.ReadAll();
		int nouvelID = 0;
		for (Societe s : lstSoc) {
			if (s.get_ID_Societe() > nouvelID)
				nouvelID = s.get_ID_Societe();
		}
		nouvelID += 1;

		String output = "<br><h4>Ajouter une société</h4><br><form class='formAjouterSociete' method="
				+ "'get' data-idSociete='" + nouvelID + "'>";

		String IDSoc = "<div class='form-group row'>"
				+ "<label for='staticIdSociete' class='col-sm-2 col-form-label'>ID Société</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdSociete' value='" + nouvelID
				+ "'>" + "</div></div>";

		String Nom = "<div class='form-group row'>"
				+ "<label for='inputNom' class='col-sm-2 col-form-label'>Nom</label>" + "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputNom' placeholder='(entrez un nom)'>"
				+ "</div></div>";

		String CA = "<div class='form-group row'>"
				+ "<label for='inputCA' class='col-sm-2 col-form-label'>Chiffre d'affaire</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputCA' min='0' max='1000000' step='0.1'"
				+ "placeholder='(en millions)'>" + "</div></div>";

		String Act = "<div class='form-group row'>"
				+ "<label for='selectActivite' class='col-sm-2 col-form-label'>Choisir une activité :</label>"
				+ "<div class='col-sm-8'>" + "<select id='selectActivite' class='form-control'>";

		for (Activites a : Activites.values()) {
			Act += "<option value='" + a + "'>" + a + "</option>";
		}
		Act += "</select></div></div>";

		String button = "<button class='btn btn-primary' id='btnConfirmerAjoutSociete' data-idSociete='" + nouvelID
				+ "'>Confirmer</button>";

		output += IDSoc + Nom + CA + Act + button + "</form>";

		return output;
	}

	/**
	 * Renvoie un tableau HTML des entrées la table "Personne" (dont l'ID Société
	 * est passé en paramètre) sous forme de chaîne de caractères.
	 * 
	 * @param idSociete
	 * @return
	 */
	public static String TableauEmployes(int idSociete) {

		DAO_Personne daop = new DAO_Personne();
		ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

		String output = "<br><h3>Table employés</h3><br>";

		output += "<table class='table'><thead><tr><th>ID Personne</th>"
				+ "<th>Nom</th><th>Prenom</th><th>Taille</th><th>Poids"
				+ "</th><th>Sexe</th><th>ID Société</th><th></th><th></th></tr></thead><tbody>";

		for (Personne p : lstEmployes) {
			String IDPers = "<tr><td>" + p.getID_Personne() + "</td>";
			String Nom = "<td>" + p.getNom() + "</td>";
			String Prenom = "<td>" + p.getPrenom() + "</td>";
			String Taille = "<td>" + p.getTaille() + "</td>";
			String Poids = "<td>" + p.getPoids() + "</td>";
			String Genre = "<td>" + p.getGenre() + "</td>";
			String IDSoc = "<td>" + p.getID_Societe() + "</td>";

			String btnModifier = "<td class='w-5'><button data-idSociete='" + idSociete + "' data-idPersonne='"
					+ p.getID_Personne() + "' class='btnUpdateEmploye' data-toggle='modal' data-target="
					+ "'#modalModifierEmploye'><i class='fa fa-pencil'></i></button></td>";

			String btnSupprimer = "<td class='w-5'><button data-idSociete='" + idSociete + "' data-idPersonne='"
					+ +p.getID_Personne() + "' class='btnDeleteEmploye'><i class='fa fa-remove'></i></button></td>";

			output += IDPers + Nom + Prenom + Taille + Poids + Genre + IDSoc + btnModifier + btnSupprimer;
		}

		output += "</tr></tbody></table>";

		output += "<button type='button' class='btn btn-primary' id="
				+ "'btnAjouterEmploye' data-toggle='modal' data-target="
				+ "'#modalAjoutEmploye'>Ajouter un employé</button>";

		return output;
	}
	
	
	
	
	

	public static String ModifierEmploye() {

		String output = "";
		String formulaire = "";

		DAO_Personne daop = new DAO_Personne();
		Personne employe = daop.Read(idEmploye);

		formulaire = "<form class='formModifierEmploye' method=" + "'get' data-idEmploye='" + idEmploye + "'>";

		String IDEmp = "<div class='form-group row'>"
				+ "<label for='staticIdEmploye' class='col-sm-3 col-form-label'>ID Employe</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdEmploye' value='" + idEmploye
				+ "'></div></div>";

		String Nom = "<div class='form-group row'>"
				+ "<label for='inputNomEmploye' class='col-sm-3 col-form-label'>Nom</label>" + "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputNomEmploye' placeholder='" + employe.getNom() + "'>"
				+ "</div></div>";

		String Prenom = "<div class='form-group row'>"
				+ "<label for='inputPrenomEmploye' class='col-sm-3 col-form-label'>Prenom</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputPrenomEmploye' placeholder='" + employe.getPrenom()
				+ "'>" + "</div></div>";

		String Poids = "<div class='form-group row'>"
				+ "<label for='inputPoidsEmploye' class='col-sm-3 col-form-label'>Poids</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputPoidsEmploye' min='0' max='200' step='0.1'"
				+ "placeholder='" + employe.getPoids() + "'>" + "</div></div>";

		String Taille = "<div class='form-group row'>"
				+ "<label for='inputTailleEmploye' class='col-sm-3 col-form-label'>Taille</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputTailleEmploye' min='0' max='3' step='0.1'"
				+ "placeholder='"+employe.getTaille()+"'>" + "</div></div>";

		String Genre = "<div class='form-group row'>"
				+ "<label for='selectGenre' class='col-sm-3 col-form-label'>Sélectionnez le genre :</label>" + "<div class='col-sm-8'>"
				+ "<select id='selectGenre' class='form-control'>";

		for (Sexe s : Sexe.values()) {
			Genre += "<option value='" + s + "'>" + s + "</option>";
		}
		Genre += "</select></div></div>";

		String IDSoc = "<div class='form-group row'>"
				+ "<label for='staticIdSociete' class='col-sm-3 col-form-label'>ID Societe</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdSociete' value='" + employe.getID_Societe()
				+ "'></div></div>";

		formulaire += IDEmp + Nom + Prenom + Poids + Taille + Genre + IDSoc + "</form>";

		output += "<div class='modal fade' id='modalAjoutEmploye' tabindex="
				+ "'-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>"
				+ "<div class='modal-dialog modal-dialog-centered' role='document'>" + "<div class='modal-content'>"
				+ "<div class='modal-header'>";

		output += "<h5 class='modal-title' id='exampleModalLongTitle'>Modifier un employé</h5>"
				+ "<button type='button' class='close' data-dismiss='modal'>"
				+ "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>";

		output += formulaire;

		output += "</div>" + "<div class='modal-footer'>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>Annuler</button>"
				+ "<button type='button' class='btn btn-primary'>Enregistrer</button>" + "</div></div></div></div>";

		return output;
	}

	
	
	
	
	
	public static String AjouterEmploye() {

		String output = "";
		String formulaire = "";

		DAO_Personne daop = new DAO_Personne();

//		Enregistrer l'ID Employé le plus élevé pour attribuer un ID à la nouvelle société
		ArrayList<Personne> lstEmployes = daop.ReadAll();
		int nouvelID = 0;
		for (Personne emp : lstEmployes) {
			if (emp.getID_Personne() > nouvelID)
				nouvelID = emp.getID_Personne();
		}
		nouvelID += 1;

		formulaire = "<form class='formAjouterEmploye' method=" + "'get' data-idEmploye='" + nouvelID + "'>";

		String IDEmp = "<div class='form-group row'>"
				+ "<label for='staticIdEmploye' class='col-sm-3 col-form-label'>ID Employe</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdEmploye' value='" + nouvelID
				+ "'></div></div>";

		String Nom = "<div class='form-group row'>"
				+ "<label for='inputNomEmploye' class='col-sm-3 col-form-label'>Nom</label>" + "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputNomEmploye' placeholder='(entrez un nom)'>"
				+ "</div></div>";

		String Prenom = "<div class='form-group row'>"
				+ "<label for='inputPrenomEmploye' class='col-sm-3 col-form-label'>Prenom</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputPrenomEmploye' placeholder='(entrez un prénom)'>"
				+ "</div></div>";

		String Poids = "<div class='form-group row'>"
				+ "<label for='inputPoidsEmploye' class='col-sm-3 col-form-label'>Poids</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputPoidsEmploye' min='0' max='200' step='0.1'"
				+ "placeholder='(en kilogrammes)'>" + "</div></div>";

		String Taille = "<div class='form-group row'>"
				+ "<label for='inputTailleEmploye' class='col-sm-3 col-form-label'>Taille</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' id='inputTailleEmploye' min='0' max='3' step='0.1'"
				+ "placeholder='(en mètres)'>" + "</div></div>";

		String Genre = "<div class='form-group row'>"
				+ "<label for='selectGenre' class='col-sm-3 col-form-label'>Genre :</label>" + "<div class='col-sm-8'>"
				+ "<select id='selectGenre' class='form-control'>";

		for (Sexe s : Sexe.values()) {
			Genre += "<option value='" + s + "'>" + s + "</option>";
		}
		Genre += "</select></div></div>";

		String IDSoc = "<div class='form-group row'>"
				+ "<label for='staticIdSociete' class='col-sm-3 col-form-label'>ID Societe</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdSociete' value='" + nouvelID
				+ "'></div></div>";

		formulaire += IDEmp + Nom + Prenom + Poids + Taille + Genre + IDSoc + "</form>";

		output += "<div class='modal fade' id='modalAjoutEmploye' tabindex="
				+ "'-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>"
				+ "<div class='modal-dialog modal-dialog-centered' role='document'>" + "<div class='modal-content'>"
				+ "<div class='modal-header'>";

		output += "<h5 class='modal-title' id='exampleModalLongTitle'>Ajouter un employé</h5>"
				+ "<button type='button' class='close' data-dismiss='modal'>"
				+ "<span aria-hidden='true'>&times;</span></button></div><div class='modal-body'>";

		output += formulaire;

		output += "</div>" + "<div class='modal-footer'>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>Annuler</button>"
				+ "<button type='button' class='btn btn-primary'>Enregistrer</button>" + "</div></div></div></div>";

		return output;
	}
}
