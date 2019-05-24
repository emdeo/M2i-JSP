package m2i.geom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import m2i.geom.Connexion;

public class DAO_Carre implements IDAO<Carre2> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Carre2 obj) {

		int output = -1;
		String req = "INSERT INTO `Carre` VALUES (?,?,?)";

//		Message d'info
		System.out.println("DAO_Carre Create");

		try {

//			Créer une entrée dans la table 'Point'
			DAO_Point daop = new DAO_Point();
			daop.Create(obj.get_coord());
			
			PreparedStatement ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setInt(1, obj.getID_Forme());
			ps.setFloat(2, obj.get_Cote());
			ps.setInt(3, obj.get_coord().getID_Point());
			
			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Carre Create() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public Carre2 Read(int id) {

		Carre2 output = null;
		String req_Carre = "SELECT * FROM Carre WHERE ID_Carre = ?";
		String req_Point = "SELECT * FROM Point WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Carre Read");

		try {
			PreparedStatement ps_Carre = _Cnn.prepareStatement(req_Carre);

			ps_Carre.setInt(1, id); // Complète la requête SQL

			ResultSet rs_Carre = ps_Carre.executeQuery();

			if (rs_Carre.next()) {
				int IdCarre = rs_Carre.getInt("ID_Carre");
				float Cote = rs_Carre.getFloat("Cote");
				int IdPoint = rs_Carre.getInt("ID_Point");

				PreparedStatement ps_Point = _Cnn.prepareStatement(req_Point);

				ps_Point.setInt(1, IdPoint); // Complète la requête SQL

				ResultSet rs_Point = ps_Point.executeQuery();

				Point coord = new Point(IdPoint, rs_Point.getInt("X"), rs_Point.getInt("Y"));

				output = new Carre2(IdCarre, coord, Cote);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Carre Read() error: " + e.getMessage() + "\n");
		}

		return output;

	}
	
	/**
	 * Lier une entrée à partir de son ID Point
	 * @param id
	 * @return
	 */
	public Carre2 ReadPoint(int id) {

		Carre2 output = null;
		String req_Carre = "SELECT * FROM Carre WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Carre ReadPoint");

		try {
			PreparedStatement ps_Carre = _Cnn.prepareStatement(req_Carre);

			ps_Carre.setInt(1, id); // Complète la requête SQL

			ResultSet rs_Carre = ps_Carre.executeQuery();

			if (rs_Carre.next()) {
				int IdCarre = rs_Carre.getInt("ID_Carre");
				float Cote = rs_Carre.getFloat("Cote");
				
				DAO_Point daop = new DAO_Point();
				Point coord = daop.Read(id);

				output = new Carre2(IdCarre, coord, Cote);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Carre ReadPoint() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public ArrayList<Carre2> ReadAll() {

		ArrayList<Carre2> output = new ArrayList<Carre2>();
		String req_Carre = "SELECT * FROM `Carre`";

//		Message d'info
		System.out.println("DAO_Carre ReadAll");

		try {

			PreparedStatement ps_Carre = _Cnn.prepareStatement(req_Carre);
			ResultSet rs_Carre = ps_Carre.executeQuery();

			while (rs_Carre.next()) {
				int IdCarre = rs_Carre.getInt("ID_Carre");
				float Cote = rs_Carre.getFloat("Cote");
				int IdPoint = rs_Carre.getInt("ID_Point");

				DAO_Point daop = new DAO_Point();
				Point coord = daop.Read(IdPoint);

				output.add(new Carre2(IdCarre, coord, Cote));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Carre ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public int Update(Carre2 obj) {

		int output = -1;

		String req = "UPDATE `Carre` SET `Cote`=? WHERE `ID_Carre`=?";

//		Message d'info
		System.out.println("DAO_Carre Update obj " + obj);

		try {
			PreparedStatement ps;

			ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setFloat(1, obj.get_Cote());
			ps.setInt(2, obj.getID_Forme());

			// Exécute la requête et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Carre Update() error: " + e.getMessage() + "\n");
		}
		return output;

	}

	@Override
	public int Delete(int id) {

		int output = 0;
		String req_Carre = "DELETE FROM 'Carre' WHERE 'ID_Carre' = ?";
		String req_Point = "DELETE FROM 'Point' WHERE 'ID_Point' = ?";

//		Message d'info
		System.out.println("DAO_Carre Delete ID " + id);

		try {

			PreparedStatement ps = _Cnn.prepareStatement(req_Carre);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Carre Delete() error: " + e.getMessage() + "\n");
		}

		return output;

	}

}
