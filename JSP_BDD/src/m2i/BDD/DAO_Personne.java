package m2i.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_Personne implements IDAO {

	final static String url = "jdbc:mysql://localhost:3306/XXXXXXX?serverTimezone=UTC";
	final static String user = "root";
	final static String pwd = "";

	private static Connection _Cnn = Connexion.get_instance(url, user, pwd);

	@Override
	public int Instanciate() {
		if (ReadAll().size() != 0) {
			System.out.println("La table 'Eleve' n'est pas vide, Instanciate() impossible.");
			return -1;
		}

		int output = -1;
		String request = "INSERT INTO XXXXXXX VALUES (?,?,?,?,?,?,?)";

		ArrayList<Personne> listePersonnes = new ArrayList<Personne>();
		listePersonnes.add(new Personne(1, "Alpha", "Alice", 1.8f, 73, Sexe.feminin, 1));
		listePersonnes.add(new Personne(2, "Bravo", "Bernard", 1.5f, 64f, Sexe.masculin, 1));
		listePersonnes.add(new Personne(3, "Charly", "Carole", 1.68f, 92f, Sexe.feminin, 1));
		listePersonnes.add(new Personne(4, "Delta", "Denis", 1.91f, 130f, Sexe.masculin, 1));

		for (Personne p : listePersonnes) {
			try {

				PreparedStatement ps = _Cnn.prepareStatement(request);

				ps.setInt(1, p.getID_Personne());
				ps.setString(2, p.getNom());
				ps.setString(3, p.getPrenom());
				ps.setFloat(4, p.getTaille());
				ps.setFloat(5, p.getPoids());
				ps.setString(6, p.getGenre().name());

				output = ps.executeUpdate();

			} catch (SQLException error) {
				System.out.println("AO_Personne Instanciate() error: " + error.getMessage() + "\n");
			}
		}

		return output;
	}

	@Override
	public int Create(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object Read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList ReadAll() {
		// TODO Auto-generated method stub
		return null;
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
