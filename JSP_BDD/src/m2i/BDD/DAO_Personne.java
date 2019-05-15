package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Personne implements IDAO<Personne> {

	// Constantes statiques qui n'existent qu'en un seul exemplaire
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// On se connecte à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	/**
	 * Instancier la table avec 4 lignes de personnes.
	 * 
	 * @return
	 */
	public int Instanciate() {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'personne' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO personne VALUES (?,?,?,?,?,?,?)";

		ArrayList<Personne> listePersonnes = new ArrayList<Personne>();
		listePersonnes.add(new Personne(1, "Alpha", "Alice", 1.8f, 73, Sexe.FEMININ, 1));
		listePersonnes.add(new Personne(2, "Bravo", "Bernard", 1.5f, 64f, Sexe.MASCULIN, 1));
		listePersonnes.add(new Personne(3, "Charly", "Carole", 1.68f, 92f, Sexe.FEMININ, 1));
		listePersonnes.add(new Personne(4, "Delta", "Denis", 1.91f, 130f, Sexe.MASCULIN, 1));

		for (Personne p : listePersonnes) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, p.getID_Personne());
				ps.setString(2, p.getNom());
				ps.setString(3, p.getPrenom());
				ps.setFloat(4, p.getTaille());
				ps.setFloat(5, p.getPoids());
				ps.setString(6, p.getGenre().name());
				ps.setInt(7, p.getID_Societe());

				output = ps.executeUpdate();

			} catch (SQLException error) {
				System.out.println("DAO_Personne Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}

	// INSERER UNE LIGNE DANS LA TABLE (= nouvelle personne)
	@Override
	public int Create(Personne p) {

		int output = -1;
		String ma_requete = "INSERT INTO Personne VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Complète la requête SQL
			ps.setInt(1, p.getID_Personne());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getPrenom());
			ps.setFloat(4, p.getPoids());
			ps.setFloat(5, p.getTaille());
			ps.setString(6, p.getGenre().name());
			ps.setInt(7, p.getID_Societe());

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

//			DAO_Societe daos = new DAO_Societe();
//			output += daos.Create(new Societe(p.getID_Societe(), "", -99f, Activites.INCONNU));

		} catch (SQLException e) {
			System.out.println("DAO_Personne Create() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	// RENVOYER L'OBJET DONT ON PASSE L'ID EN PARAMETRE
	@Override
	public Personne Read(int id) {

		Personne output = null;
		String ma_requete = "select * from personne where ID_Personne = ?";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output = new Personne(id, nom, prenom, poids, taille, sexe, id_Societe);
				output.toString();
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne Read() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	/**
	 * Lire toutes les entrées de la table.
	 */
	@Override
	public ArrayList<Personne> ReadAll() {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de données
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	// Renvoie une liste d'employés dont l'ID_Société correspond au paramètre
	public ArrayList<Personne> ListeEmployesSociete(int id_soc) {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne WHERE ID_Societe = ?";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ps.setInt(1, id_soc); // Complète la requête SQL
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de données
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Sexe sexe = Sexe.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ListeEmployesSociete() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	/**
	 * Met à jour une ligne de la table à partir d'un objet passé en paramètre.
	 * 
	 */
	@Override
	public int Update(Personne p) {

		int output = -1;
		String ma_requete = "UPDATE Personne SET Nom = ?, Prenom = ?, Poids = ?,"
				+ "Taille = ?, Sexe = ?, ID_Societe = ? WHERE ID_Personne = ?";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Complète la requête SQL
			ps.setString(1, p.getNom());
			ps.setString(2, p.getPrenom());
			ps.setFloat(3, p.getPoids());
			ps.setFloat(4, p.getTaille());
			ps.setString(5, p.getGenre().name());
			ps.setInt(6, p.getID_Societe());
			ps.setInt(7, p.getID_Personne());

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Update() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public int Delete(int id) {

		int output = -1;
		String ma_requete = "DELETE FROM Personne WHERE ID_Personne = ?";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Delete() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	/**
	 * Supprimer tous les employés d'une société dont on passe l'ID en paramètre.
	 * 
	 * @param id_soc
	 * @return
	 */
	public int DeleteAll(int id_soc) {

		int output = -1;

		String ma_requete = "DELETE * FROM Personne WHERE ID_Societe = ?";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id_soc); // Complète la requête SQL

			// Enregistre le nombre de modifs exécutées
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne DeleteAll() error: " + e.getMessage() + "\n");
		}

		return output;
	}

}