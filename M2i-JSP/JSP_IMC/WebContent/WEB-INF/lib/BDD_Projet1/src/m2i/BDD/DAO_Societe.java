package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Societe {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// On se connecte � la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	public int Create(Societe s) {

		int output = -1;
		String request1 = "INSERT INTO Societe VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = _Cnn.prepareStatement(request1);

			// Compl�te la requ�te SQL
			ps.setInt(1, s.get_ID_Societe());
			ps.setString(2, s.get_Nom());
			ps.setFloat(3, s.get_CA());
			ps.setString(4, s.get_Activite().name());

			// Ex�cute la req�ete et enregistre le nombre de modifs
			output = ps.executeUpdate();

			// Ajouter les employ�s enregistr�s dans l'objet Societe dans la table
			// "Personne"
			DAO_Personne daop = new DAO_Personne();
			for (Personne p : s.get_lstEmployes()) {
				daop.Create(p);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe Create() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	public Societe Read(int id) {

		Societe output = null;
		String ma_requete = "SELECT * FROM Societe WHERE ID_Societe = ?";

		System.out.println("Sending MySQL request : " + ma_requete + "\n");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Compl�te la requ�te SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("Nom");
				float ca = rs.getFloat("CA");
				Activites activite = Activites.valueOf(rs.getString("Activite"));

				output = new Societe(id, nom, ca, activite);
				output.toString();
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne Read() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	public ArrayList<Societe> ReadAll() {

		ArrayList<Societe> output = new ArrayList<Societe>();
		String request = "SELECT * FROM Societe";

		System.out.println("Sending SQL request: " + request + "\n");

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID_Societe");
				String nom = rs.getString("Nom");
				float ca = rs.getFloat("CA");
				Activites act = Activites.valueOf(rs.getString("Activite"));

				output.add(new Societe(id, nom, ca, act));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Personne ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;
	}

	public int Update(Societe s) {

		int output = -1;

		// Si la soci�t� qu'on veut modifier n'existe pas, on la cr�e et la m�thode
		// s'arr�te
		if (this.Read(s.get_ID_Societe()) == null) {
			return this.Create(s);
		}

		String request = "UPDATE Societe SET Nom = ?, CA = ?, Activite = ? WHERE ID_Societe = ?";

		try {
			// Charger la requ�te SQL
			PreparedStatement ps = _Cnn.prepareStatement(request);

			// Compl�ter la requ�te
			ps.setString(1, s.get_Nom());
			ps.setFloat(2, s.get_CA());
			ps.setString(3, s.get_Activite().name());
			ps.setInt(4, s.get_ID_Societe());
			
			// Supprimer tous les les employ�s de la soci�t� et les remplacer par les nouveaux
			DAO_Personne daop = new DAO_Personne();
			daop.SupprimerTousEmployes(s.get_ID_Societe());
			
			for(Personne p:s.get_lstEmployes()) {
				daop.Create(p);
			}

			// Ex�cuter la requ�te et enregistrer son r�sultat
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Societe Update() error: " + e.getMessage() + "\n");
		}
		return output;
	}

	public int Delete(int id) {

		int output = 0;
		String request = "DELETE FROM Societe WHERE ID_Societe = ?";

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(("DAO_Societe Delete() error: " + e.getMessage() + "\n"));
		}

		return output;
	}

}