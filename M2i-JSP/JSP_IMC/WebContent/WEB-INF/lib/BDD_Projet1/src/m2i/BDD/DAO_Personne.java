package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Personne implements IDAO<Personne> {

	// Constantes statiques qui n'existent qu'en un seul exemplaire
	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// On se connecte � la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	// INSERER UNE LIGNE DANS LA TABLE (= nouvelle personne)
	@Override
	public int Create(Personne p) {

		int output = -1;
		String ma_requete = "INSERT INTO Personne VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Compl�te la requ�te SQL
			ps.setInt(1, p.get_ID_Personne());
			ps.setString(2, p.get_Nom());
			ps.setString(3, p.get_Prenom());
			ps.setFloat(4, p.get_Poids());
			ps.setFloat(5, p.get_Taille());
			ps.setString(6, p.get_Sexe().name());
			ps.setInt(7, p.get_ID_Societe());

			// Enregistre le nombre de modifs ex�cut�es
			output = ps.executeUpdate();

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

			ps.setInt(1, id); // Compl�te la requ�te SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output = new Personne(id, nom, prenom, poids, taille, sexe, id_Societe);
				output.toString();
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne Read() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	@Override
	public ArrayList<Personne> ReadAll() {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de donn�es
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	// Renvoie une liste d'employ�s dont l'ID_Soci�t� correspond au param�tre
	public ArrayList<Personne> ListeEmployesSociete(int id_soc) {

		ArrayList<Personne> output = new ArrayList<Personne>();
		String ma_requete = "SELECT * FROM Personne WHERE ID_Societe = ?";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);
			ps.setInt(1, id_soc); // Compl�te la requ�te SQL
			ResultSet rs = ps.executeQuery();

			// WHILE : on ajoute un nouvel objet tant qu'il y a encore un ligne de donn�es
			while (rs.next()) {
				int id = rs.getInt("ID_Personne");
				String nom = rs.getString("Nom");
				String prenom = rs.getString("Prenom");
				float poids = rs.getFloat("Poids");
				float taille = rs.getFloat("Taille");
				Genre sexe = Genre.valueOf(rs.getString("Sexe"));
				int id_Societe = rs.getInt("ID_Societe");

				output.add(new Personne(id, nom, prenom, poids, taille, sexe, id_Societe));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ListeEmployesSociete() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	// MET A JOUR UNE LIGNE DE LA TABLE A PARTIR D'UN OBJET EN PARAMETRE
	@Override
	public int Update(Personne p) {

		int output = -1;
		String ma_requete = "UPDATE Personne SET Nom = ?, Prenom = ?, Poids = ?,"
				+ "Taille = ?, Sexe = ?, ID_Societe = ? WHERE ID_Personne = ?";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			// Compl�te la requ�te SQL
			ps.setString(1, p.get_Nom());
			ps.setString(2, p.get_Prenom());
			ps.setFloat(3, p.get_Poids());
			ps.setFloat(4, p.get_Taille());
			ps.setString(5, p.get_Sexe().name());
			ps.setInt(6, p.get_ID_Societe());
			ps.setInt(7, p.get_ID_Personne());

			// Enregistre le nombre de modifs ex�cut�es
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

			ps.setInt(1, id); // Compl�te la requ�te SQL

			// Enregistre le nombre de modifs ex�cut�es
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Delete() error: " + e.getMessage() + "\n");
		}

		return output;
	}
	
	// Supprimer tous les employ�s d'une soci�t� dont on pass l'ID en param�tre
	public int SupprimerTousEmployes(int id_soc) {
		
		int output = -1;
		
		String ma_requete = "DELETE * FROM Personne WHERE ID_Societe = ?";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id_soc); // Compl�te la requ�te SQL

			// Enregistre le nombre de modifs ex�cut�es
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Personne Delete() error: " + e.getMessage() + "\n");
		}
		
		return output;
	}

}