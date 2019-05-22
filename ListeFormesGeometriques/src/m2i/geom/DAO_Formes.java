package m2i.geom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import m2i.geom.Connexion;

public class DAO_Formes implements IDAO<Object> {

	final static String url = "jdbc:mysql://localhost:3306/dp_formation?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	// Connexion à la BD "dp_formation"
	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Create(Object obj) {

		int output = -1;
		String req_Carre = "INSERT INTO `formes`(`ID_Forme`, `X`, `Y`, `Cote`) VALUES (?,?,?,?)";
		String req_Rectangle = "INSERT INTO `formes`(`ID_Forme`, `X`, `Y`, `Longueur`, `Largeur`) VALUES (?,?,?,?,?)";
		String req_Cercle = "INSERT INTO `formes`(`ID_Forme`, `X`, `Y`, `Rayon`) VALUES (?,?,?,?)";

//		Message d'info
		System.out.println("DAO_Formes Create");

		try {

			if (obj instanceof Carre) {
				PreparedStatement ps = _Cnn.prepareStatement(req_Carre);

				// Complète la requête SQL
				ps.setInt(1, ((Carre) obj).get_ID_Forme());
				ps.setInt(2, ((Carre) obj).get_X());
				ps.setInt(3, ((Carre) obj).get_Y());
				ps.setFloat(4, ((Carre) obj).get_Cote());

				// Exécute la reqûete et enregistre le nombre de modifs
				output = ps.executeUpdate();
			} else if (obj instanceof Rectangle) {
				PreparedStatement ps = _Cnn.prepareStatement(req_Rectangle);

				// Complète la requête SQL
				ps.setInt(1, ((Rectangle) obj).get_ID_Forme());
				ps.setInt(2, ((Rectangle) obj).get_X());
				ps.setInt(3, ((Rectangle) obj).get_Y());
				ps.setFloat(4, ((Rectangle) obj).get_Longueur());
				ps.setFloat(5, ((Rectangle) obj).get_Largeur());

				// Exécute la reqûete et enregistre le nombre de modifs
				output = ps.executeUpdate();
			} else if (obj instanceof Cercle) {
				PreparedStatement ps = _Cnn.prepareStatement(req_Cercle);

				// Complète la requête SQL
				ps.setInt(1, ((Cercle) obj).get_ID_Forme());
				ps.setInt(2, ((Cercle) obj).get_X());
				ps.setInt(3, ((Cercle) obj).get_Y());
				ps.setFloat(4, ((Cercle) obj).get_Rayon());

				// Exécute la requête et enregistre le nombre de modifs
				output = ps.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("DAO_Formes Create() error: " + e.getMessage());
		}

		return output;

	}

	@Override
	public Object Read(int id) {

		Object output = null;
		String ma_requete = "SELECT * FROM Formes WHERE ID_Forme = ?";

//		Message d'info
		System.out.println("DAO_Formes Read");

		try {
			PreparedStatement ps = _Cnn.prepareStatement(ma_requete);

			ps.setInt(1, id); // Complète la requête SQL

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int Id = rs.getInt("ID_Forme");
				int X = rs.getInt("X");
				int Y = rs.getInt("Y");
				float Longueur = rs.getFloat("Longueur");
				float Largeur = rs.getFloat("Largeur");
				float Cote = rs.getFloat("Cote");
				float Rayon = rs.getFloat("Rayon");

				System.out.println("Forme n°" + Id + " : " + X + " " + Y + " " + Longueur + " " + Largeur + " " + Cote
						+ " " + Rayon);
				
				if(Longueur > 0) {
					output = new Rectangle(Id, X, Y, Longueur, Largeur);
				}
				else if (Cote > 0) {
					output = new Carre(Id, X, Y, Cote);
				}
				else if (Rayon > 0) {
					output = new Cercle(Id, X, Y, Rayon);
				}
			}

		} catch (SQLException e) {
			System.out.println("DAO_Societe  Read() error: " + e.getMessage());
		}

		return output;

	}

	@Override
	public ArrayList<Object> ReadAll() {

		ArrayList<Object> output = new ArrayList<Object>();
		String request = "SELECT * FROM `formes`";

//		Message d'info
		System.out.println("DAO_Formes ReadAll");

		System.out.println(request);
		try {

			PreparedStatement ps = _Cnn.prepareStatement(request);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int Id = rs.getInt("ID_Forme");
				int X = rs.getInt("X");
				int Y = rs.getInt("Y");
				float Longueur = rs.getFloat("Longueur");
				float Largeur = rs.getFloat("Largeur");
				float Cote = rs.getFloat("Cote");
				float Rayon = rs.getFloat("Rayon");

				System.out.println("Forme n°" + Id + " : " + X + " " + Y + " " + Longueur + " " + Largeur + " " + Cote
						+ " " + Rayon);
				
				if(Longueur > 0) {
					output.add(new Rectangle(Id, X, Y, Longueur, Largeur));
				}
				else if (Cote > 0) {
					output.add(new Carre(Id, X, Y, Cote));
				}
				else if (Rayon > 0) {
					output.add(new Cercle(Id, X, Y, Rayon));
				}

			}

		} catch (SQLException e) {
			System.out.println("DAO_Formes ReadAll() error: " + e.getMessage());
		}

		return output;
		
	}

	@Override
	public int Update(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
