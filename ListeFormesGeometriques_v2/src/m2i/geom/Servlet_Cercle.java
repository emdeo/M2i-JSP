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
 * Servlet implementation class Servlet_Cercle
 */
@WebServlet("/Servlet_Cercle")
public class Servlet_Cercle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Cercle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("\nGET_CERCLE en action\n");

		DAO_Cercle daoc = new DAO_Cercle();

		int x = Integer.parseInt(request.getParameter("X"));
		int y = Integer.parseInt(request.getParameter("Y"));
		float rayon = Float.parseFloat(request.getParameter("rayon"));

		
		DAO_Point daop = new DAO_Point();
		int idPt = genererNouvelIDPoint();
		int idForme = genererNouvelIDCercle();
		
		daoc.Create(new Cercle2(idForme, new Point(idPt, x, y), rayon));

////		Permettre au code de transmettre du texte à un autre fichier (sources.js)
//		response.setContentType("application/json");
//
//		PrintWriter out = response.getWriter();
//
//		DAO_Cercle daoc = new DAO_Cercle();
//
////		Liste d'objets
//		ArrayList<Cercle2> lstCercles = daoc.ReadAll();
//		ArrayList<FormeGenerique> output = new ArrayList<FormeGenerique>();
//
//		System.out.println("\nListe des Cercles :\n" + lstCercles);
//
//		for (Cercle2 c : lstCercles) {
//			System.out.println(c + " - " + c.getID_Forme());
//			int ID = c.getID_Forme();
//			int X = c.get_coord().getX();
//			int Y = c.get_coord().getY();
//			float Rayon = c.get_Rayon();
//			float Perimetre = c.Perimetre();
//			float Surface = c.Surface();
//
//			output.add(new FormeGenerique(ID, X, Y, 0, 0, 0, Rayon, Perimetre, Surface));
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
		
		System.out.println("\nPOST_CERCLE en action\n");

		DAO_Cercle daoc = new DAO_Cercle();
		int ID_Point = Integer.parseInt(request.getParameter("IdPoint"));
		
		Cercle2 obj = daoc.ReadPoint(ID_Point);
		System.out.println(obj.get_coord().getID_Point());
		Gson gson = new GsonBuilder().create();
		String resultat = gson.toJson(obj);
		out.println(resultat);
		out.close();
		
	}
	
	/**
	 * Générer un _ID_Forme inexistant dans la table
	 * 
	 * @return
	 */
	public int genererNouvelIDCercle() {

		DAO_Cercle daoc = new DAO_Cercle();
		ArrayList<Integer> listeIDs = new ArrayList<Integer>();

		for (Cercle2 c : daoc.ReadAll())
			listeIDs.add(c.getID_Forme());

		for (int i = 1; i <= listeIDs.size() + 1; i++) {
			if (!listeIDs.contains(i))
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
