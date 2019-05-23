package m2i.geom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import m2i.geom.Connexion;

public class DAO_Rectangle implements IDAO<Rectangle2> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Rectangle2 obj) {

		int output = -1;
		String req = "INSERT INTO `Rectangle` VALUES (?,?,?,?)";

//		Message d'info
		System.out.println("DAO_Rectangle Create");

		try {

			PreparedStatement ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setInt(1, obj.getID_Forme());
			ps.setFloat(2, obj.get_Largeur());
			ps.setFloat(3, obj.get_Longueur());
			ps.setInt(4, obj.get_coord().getID_Point());

			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Rectangle Create() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public Rectangle2 Read(int id) {

		Rectangle2 output = null;
		String req_Rectangle = "SELECT * FROM 'Rectangle' WHERE ID_Rectangle = ?";
		String req_Point = "SELECT * FROM Point WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Rectangle Read");

		try {
			PreparedStatement ps_Rectangle = _Cnn.prepareStatement(req_Rectangle);

			ps_Rectangle.setInt(1, id); // Complète la requête SQL

			ResultSet rs_Rectangle = ps_Rectangle.executeQuery();

			if (rs_Rectangle.next()) {
				int IdRectangle = rs_Rectangle.getInt("ID_Rectangle");
				float Largeur = rs_Rectangle.getFloat("Largeur");
				float Longueur = rs_Rectangle.getFloat("Longueur");
				int IdPoint = rs_Rectangle.getInt("ID_Point");

				PreparedStatement ps_Point = _Cnn.prepareStatement(req_Point);

				ps_Point.setInt(1, IdPoint); // Complète la requête SQL

				ResultSet rs_Point = ps_Point.executeQuery();

				Point coord = new Point(IdPoint, rs_Point.getInt("X"), rs_Point.getInt("Y"));

				output = new Rectangle2(IdRectangle, coord, Largeur, Longueur);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Rectangle Read() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public ArrayList<Rectangle2> ReadAll() {

		ArrayList<Rectangle2> output = new ArrayList<Rectangle2>();
		String req_Rectangle = "SELECT * FROM `Rectangle`";
		String req_Point = "SELECT * FROM `Point`";

//		Message d'info
		System.out.println("DAO_Rectangle ReadAll");

		try {

			PreparedStatement ps_Rectangle = _Cnn.prepareStatement(req_Rectangle);
			ResultSet rs_Rectangle = ps_Rectangle.executeQuery();

			while (rs_Rectangle.next()) {
				int IdRectangle = rs_Rectangle.getInt("ID_Rectangle");
				float Largeur = rs_Rectangle.getFloat("Largeur");
				float Longueur = rs_Rectangle.getFloat("Longueur");
				int IdPoint = rs_Rectangle.getInt("ID_Point");

				PreparedStatement ps_Point = _Cnn.prepareStatement(req_Point);

				ps_Point.setInt(1, IdPoint); // Complète la requête SQL

				ResultSet rs_Point = ps_Point.executeQuery();

				Point coord = new Point(IdPoint, rs_Point.getInt("X"), rs_Point.getInt("Y"));

				output.add(new Rectangle2(IdRectangle, coord, Largeur, Longueur));
			}

		} catch (SQLException e) {
			System.out.println("DAO_Rectangle ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public int Update(Rectangle2 obj) {

		int output = -1;

		String req = "UPDATE `Rectangle` SET `Largeur`=?,`Longueur`=? WHERE `ID_Rectangle`=?";

//		Message d'info
		System.out.println("DAO_Rectangle Update obj " + obj);

		try {
			PreparedStatement ps;

			ps = _Cnn.prepareStatement(req);

			// Complète la requête SQL
			ps.setFloat(1, obj.get_Largeur());
			ps.setFloat(2, obj.get_Longueur());
			ps.setInt(3, obj.getID_Forme());

			// Exécute la reqûete et enregistre le nombre de modifs
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Rectangle Update() error: " + e.getMessage() + "\n");
		}
		return output;

	}

	@Override
	public int Delete(int id) {

		int output = 0;
		String request = "DELETE FROM 'Rectangle' WHERE 'ID_Rectangle' = ?";

//		Message d'info
		System.out.println("DAO_Rectangle Delete ID " + id);

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Rectangle Delete() error: " + e.getMessage() + "\n");
		}

		return output;

	}

}
