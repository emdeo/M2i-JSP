package m2i.geom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Servlet_Carre
 */
@WebServlet("/Servlet_Carre")
public class Servlet_Carre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Carre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("\nGET_CARRE en action\n");

		DAO_Carre daoc = new DAO_Carre();

		int x = Integer.parseInt(request.getParameter("X"));
		int y = Integer.parseInt(request.getParameter("Y"));
		float cote = Float.parseFloat(request.getParameter("cote"));

		DAO_Point daop = new DAO_Point();

		int idPt = genererNouvelIDPoint();
		int idForme = genererNouvelIDCarre();

		daoc.Create(new Carre2(idForme, new Point(idPt, x, y), cote));

//		Permettre au code de transmettre du texte à un autre fichier (sources.js)
//		response.setContentType("application/json");
//
//		PrintWriter out = response.getWriter();
//
//		DAO_Carre daoc = new DAO_Carre();

//		Liste d'objets
//		ArrayList<Carre2> lstCarres = daoc.ReadAll();
//		ArrayList<FormeGenerique> output = new ArrayList<FormeGenerique>();
//
//		System.out.println("\nListe des Carres :\n" + lstCarres);
//
//		for (Carre2 c : lstCarres) {
//
//			int ID = c.getID_Forme();
//			int X = c.get_coord().getX();
//			int Y = c.get_coord().getY();
//			float Cote = c.get_Cote();
//			float Perimetre = c.Perimetre();
//			float Surface = c.Surface();
//
//			output.add(new FormeGenerique(ID, X, Y, 0, 0, Cote, 0, Perimetre, Surface));
//		}
//
//		Gson gson = new GsonBuilder().create();
//		String resultat = gson.toJson(output);	
//		
//		out.println(resultat);
//		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		System.out.println("\nPOST_CARRE en action\n");

		DAO_Carre daoc = new DAO_Carre();
		int ID_Point = Integer.parseInt(request.getParameter("IdPoint"));
		
		Carre2 car = daoc.ReadPoint(ID_Point);
		
		Gson gson = new GsonBuilder().create();
		String resultat = gson.toJson(car);
		out.println(resultat);
		out.close();
		
	}

	/**
	 * Générer un _ID_Forme inexistant dans la table
	 * 
	 * @return
	 */
	public int genererNouvelIDCarre() {

		DAO_Carre daoc = new DAO_Carre();
		ArrayList<Integer> listeIDs = new ArrayList<Integer>();

		for (Carre2 c : daoc.ReadAll())
			listeIDs.add(c.getID_Forme());
		
		System.out.println("la liste contient "+listeIDs.size()+" éléments");

		for (int i = 1; i <= (listeIDs.size() + 1); i++) {
			System.out.println("i = "+i);
			if (listeIDs.contains(i) == false)
				return i;
		}
		return -1;
	}

	/**
	 * Générer un _ID_Point inexistant dans la table
	 * 
	 * @return
	 */
	public int genererNouvelIDPoint() {

		DAO_Point daop = new DAO_Point();
		ArrayList<Integer> listeIDs = new ArrayList<Integer>();

		for (Point p : daop.ReadAll())
			listeIDs.add(p.getID_Point());

		for (int i = 1; i <= listeIDs.size() + 1; i++) {
			if (!listeIDs.contains(i))
				return i;
		}
		return -1;
	}

}
