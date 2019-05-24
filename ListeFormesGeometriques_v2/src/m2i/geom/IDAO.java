package m2i.geom;

import java.util.ArrayList;

// Data Access Object, agit sur des objets de type T
public interface IDAO<T> {

	/**
	 * Extrait les données de l'objet T et renvoie le nombre de modifications dans la table.
	 * 
	 * @param obj
	 * @return
	 */
	int Create(T obj);
	
	/**
	 * Renvoie un objet de type T, celui dont l'ID est passé en paramètre.
	 * 
	 * @param id
	 * @return
	 */
	T Read(int id);
	
	/**
	 * Renvoie une toute la collection d'objets T.
	 * 
	 * @return
	 */
	ArrayList<T> ReadAll();

	/**
	 * Extrait les données de l'objet T et renvoie le nombre de modifications.
	 * 
	 * @param obj
	 * @return
	 */
	int Update(T obj);

	/**
	 * Supprime l'entrée dont on passe l'ID en paramètre.
	 * 
	 * @param id
	 * @return
	 */
	int Delete(int id);
}