package m2i.BDD;

public class Principale {

	public static void main(String[] args) {

//		R�initialiser la table 'Societe'
		DAO_Societe daos = new DAO_Societe();
		daos.Truncate();
		daos.Instanciate();

//		R�initialiser la table 'Personne'
		DAO_Personne daop = new DAO_Personne();
		daop.Truncate();
		daop.Instanciate();

	}
}
