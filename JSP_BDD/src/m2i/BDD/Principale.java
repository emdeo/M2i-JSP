package m2i.BDD;

public class Principale {

	public static void main(String[] args) {

//		Réinitialiser la table 'Societe'
		DAO_Societe daos = new DAO_Societe();
		daos.Truncate();
		daos.Instanciate();

//		Réinitialiser la table 'Personne'
		DAO_Personne daop = new DAO_Personne();
		daop.Truncate();
		daop.Instanciate();

	}
}
