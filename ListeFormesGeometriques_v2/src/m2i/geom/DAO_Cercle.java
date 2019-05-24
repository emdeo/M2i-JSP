package m2i.geom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import m2i.geom.Connexion;

public class DAO_Cercle implements IDAO<Cercle2> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Cercle2 obj) {

		int output = -1;
		String req = "INSERT INTO `Cercle` VALUES (?,?,?)";

//		Message d'info
		System.out.println("DAO_Cercle Create");

		try {
			
//			Créer une entrée dans la table 'Point'
			DAO_Point daop = new DAO_Point();
			daop.Create(obj.get_coord());

			PreparedStatement ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setInt(1, obj.getID_Forme());
			ps.setFloat(2, obj.get_Rayon());
			ps.setInt(3, obj.get_coord().getID_Point());

			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Cercle Create() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public Cercle2 Read(int id) {

		Cercle2 output = null;
		String req_Cercle = "SELECT * FROM Cercle WHERE ID_Cercle = ?";
		String req_Point = "SELECT * FROM Point WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Cercle Read");

		try {
			PreparedStatement ps_Cercle = _Cnn.prepareStatement(req_Cercle);

			ps_Cercle.setInt(1, id); // Complète la requête SQL

			ResultSet rs_Cercle = ps_Cercle.executeQuery();

			if (rs_Cercle.next()) {
				int IdCercle = rs_Cercle.getInt("ID_Cercle");
				float rayon = rs_Cercle.getFloat("Rayon");
				int IdPoint = rs_Cercle.getInt("ID_Point");

				PreparedStatement ps_Point = _Cnn.prepareStatement(req_Point);

				ps_Point.setInt(1, IdPoint); // Complète la requête SQL

				ResultSet rs_Point = ps_Point.executeQuery();

				Point coord = new Point(IdPoint, rs_Point.getInt("X"), rs_Point.getInt("Y"));

				output = new Cercle2(IdCercle, coord, rayon);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Cercle Read() error: " + e.getMessage() + "\n");
		}

		return output;

	}
	
	/**
	 * Lire l'entrée du tableau à partir de son ID_Point
	 */
	public Cercle2 ReadPoint(int id) {

		Cercle2 output = null;
		String req_Cercle = "SELECT * FROM Cercle WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Cercle ReadPoint");

		try {
			PreparedStatement ps_Cercle = _Cnn.prepareStatement(req_Cercle);

			ps_Cercle.setInt(1, id); // Complète la requête SQL

			ResultSet rs_Cercle = ps_Cercle.executeQuery();

			if (rs_Cercle.next()) {
				int IdCercle = rs_Cercle.getInt("ID_Cercle");
				float rayon = rs_Cercle.getFloat("Rayon");

				DAO_Point daop = new DAO_Point();
				Point coord = daop.Read(id);

				output = new Cercle2(IdCercle, coord, rayon);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Cercle ReadPoint() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public ArrayList<Cercle2> ReadAll() {

		ArrayList<Cercle2> output = new ArrayList<Cercle2>();
		String req_Cercle = "SELECT * FROM `Cercle`";

//		Message d'info
		System.out.println("DAO_Cercle ReadAll");

		try {

			PreparedStatement ps_Cercle = _Cnn.prepareStatement(req_Cercle);
			ResultSet rs_Cercle = ps_Cercle.executeQuery();
			
			while (rs_Cercle.next()) {
				System.out.println("Une ligne");
				int IdCercle = rs_Cercle.getInt("ID_Cercle");
				float rayon = rs_Cercle.getFloat("Rayon");
				int IdPoint = rs_Cercle.getInt("ID_Point");

				DAO_Point daop = new DAO_Point();
				Point coord = daop.Read(IdPoint);

				output.add(new Cercle2(IdCercle, coord, rayon));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Cercle ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public int Update(Cercle2 obj) {

		int output = -1;

		String req = "UPDATE `Cercle` SET `Rayon`=? WHERE `ID_Cercle`=?";

//		Message d'info
		System.out.println("DAO_Cercle Update obj " + obj);

		try {
			PreparedStatement ps;

			ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setFloat(1, obj.get_Rayon());
			ps.setInt(2, obj.getID_Forme());

			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Cercle Update() error: " + e.getMessage() + "\n");
		}
		return output;

	}

	@Override
	public int Delete(int id) {

		int output = 0;
		String request = "DELETE FROM Cercle WHERE ID_Cercle = ?";

//		Message d'info
		System.out.println("DAO_Cercle Delete ID " + id);

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Cercle Delete() error: " + e.getMessage() + "\n");
		}

		return output;

	}

}
