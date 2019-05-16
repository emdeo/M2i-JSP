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
					+ "' class='btnUpdate' data-toggle='collapse' data-target='#collapseSociete'"
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

		String output = "<br><h4>Modifier une société</h4><br><form id='formModifierSociete'>";

		DAO_Societe daos = new DAO_Societe();
		Societe s = daos.Read(idSociete);

		String IDSoc = "<div class='form-group row'>"
				+ "<label for='staticIdSociete' class='col-sm-2 col-form-label'>ID Société</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='text' readonly class='form-control-plaintext' id='staticIdSociete' value='"
				+ s.get_ID_Societe() + "'>" + "</div></div>";

		String Nom = "<div class='form-group row'>"
				+ "<label for='inputNom' class='col-sm-2 col-form-label'>Nom</label>" + "<div class='col-sm-8'>"
				+ "<input type='text' class='form-control' name='inputNom' placeholder='" + s.get_Nom() + "'>"
				+ "</div></div>";

		String CA = "<div class='form-group row'>"
				+ "<label for='inputCA' class='col-sm-2 col-form-label'>Chiffre d'affaire</label>"
				+ "<div class='col-sm-8'>"
				+ "<input type='number' class='form-control' name='inputCA' min='0' max='1000000' step='0.1'"
				+ "placeholder='" + s.get_CA() + " millions'>" + "</div></div>";

		String Act = "<div class='form-group row'>"
				+ "<label for='selectActivite' class='col-sm-2 col-form-label'>Choisir une activité :</label>"
				+ "<div class='col-sm-8'>" + "<select name='selectActivite' class='form-control'>";

		for (Activites a : Activites.values()) {
			Act += "<option value='" + a + "'>" + a + "</option>";
		}
		Act += "</select></div></div>";

		String button = "<button type='submit' class='btn btn-success' id='btnModifierSociete' data-idSociete='"
				+ idSociete + "'>Modifier</button>";

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
				+ "</th><th>Sexe</th><th>ID Société</th></tr></thead><tbody>";

		for (Personne p : lstEmployes) {
			output += "<tr><td>" + p.getID_Personne() + "</td><td>" + p.getNom() + "</td><td>" + p.getPrenom()
					+ "</td><td>" + p.getTaille() + "</td><td>" + p.getPoids() + "</td><td>" + p.getGenre()
					+ "</td><td>" + p.getID_Societe() + "</td></tr>";
		}

		output += "</tbody></table>";

		return output;
	}
}
