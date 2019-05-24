package m2i.geom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import m2i.geom.Connexion;

public class DAO_Point implements IDAO<Point> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Point obj) {

		int output = -1;
		String req = "INSERT INTO `point` VALUES (?,?,?)";

//		Message d'info
		System.out.println("DAO_Point Create");

		try {
			
				PreparedStatement ps = _Cnn.prepareStatement(req);

				// Complète la requête SQL
				ps.setInt(1, obj.getID_Point());
				ps.setInt(2, obj.getX());
				ps.setInt(3, obj.getY());

				// Exécute la reqûete et enregistre le nombre de modifs
				output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Point Create() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public Point Read(int id) {

		Point output = null;
		String ma_requete = "SELECT * FROM Point WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Point Read");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int Id = rs.getInt("ID_Point");
				int X = rs.getInt("X");
				int Y = rs.getInt("Y");

				output = new Point(Id, X, Y);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Read() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public ArrayList<Point> ReadAll() {

		ArrayList<Point> output = new ArrayList<Point>();
		String request = "SELECT * FROM `Point`";

//		Message d'info
		System.out.println("DAO_Point ReadAll");

		System.out.println(request);
		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int Id = rs.getInt("ID_Point");
				int X = rs.getInt("X");
				int Y = rs.getInt("Y");

				output.add(new Point(Id, X, Y));

				System.out.println(output);
			}

		} catch (SQLException e) {
			System.out.println("DAO_Point ReadAll() error: " + e.getMessage() + "\n");
		}

		return output;

	}

	@Override
	public int Update(Point obj) {

		int output = -1;

		String req = "UPDATE `Point` SET `X`=?,`Y`=? WHERE `ID_Point`=?";

//		Message d'info
		System.out.println("DAO_Point Update obj " + obj);

		try {
			PreparedStatement ps;

				ps = _Cnn.prepareStatement(req);

				// Complète la requête SQL
				ps.setInt(1, obj.getX());
				ps.setInt(2, obj.getY());
				ps.setInt(4, obj.getID_Point());

				// Exécute la reqûete et enregistre le nombre de modifs
				output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Point Update() error: " + e.getMessage() + "\n");
		}
		return output;

	}

	@Override
	public int Delete(int id) {

		int output = 0;
		String request = "DELETE FROM Point WHERE ID_Point = ?";

//		Message d'info
		System.out.println("DAO_Point Delete ID " + id);

		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ps.setInt(1, id);
			output = ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("DAO_Point Delete() error: " + e.getMessage() + "\n");
		}

		return output;

	}

}
