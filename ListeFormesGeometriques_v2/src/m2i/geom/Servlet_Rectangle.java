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
 * Servlet implementation class Servlet_Rectangle
 */
@WebServlet("/Servlet_Rectangle")
public class Servlet_Rectangle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Rectangle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("\nGET_RECTANGLE en action\n");
		
		DAO_Rectangle daor = new DAO_Rectangle();

		int x = Integer.parseInt(request.getParameter("X"));
		int y = Integer.parseInt(request.getParameter("Y"));
		float largeur = Float.parseFloat(request.getParameter("largeur"));
		float longueur = Float.parseFloat(request.getParameter("longueur"));

		DAO_Point daop = new DAO_Point();
		int idPt = genererNouvelIDPoint();
		int idForme = genererNouvelIDRectangle();
		
		daor.Create(new Rectangle2(idForme, new Point(idPt, x, y), largeur, longueur));

////		Permettre au code de transmettre du texte à un autre fichier (sources.js)
//		response.setContentType("application/json");
//
//		PrintWriter out = response.getWriter();
//
//		DAO_Rectangle daor = new DAO_Rectangle();
//
////		Liste d'objets
//		ArrayList<Rectangle2> lstRectangles = daor.ReadAll();
//		ArrayList<FormeGenerique> output = new ArrayList<FormeGenerique>();
//
//		System.out.println("\nListe des Rectangles :\n" + lstRectangles);
//
//		for (Rectangle2 r : lstRectangles) {
//
//			int ID = r.getID_Forme();
//			int X = r.get_coord().getX();
//			int Y = r.get_coord().getY();
//			float Largeur = r.get_Largeur();
//			float Longueur = r.get_Longueur();
//			float Perimetre = r.Perimetre();
//			float Surface = r.Surface();
//
//			output.add(new FormeGenerique(ID, X, Y, Longueur, Largeur, 0, 0, Perimetre, Surface));
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
		
		System.out.println("\nPOST_RECTANGLE en action\n");

		DAO_Rectangle daoc = new DAO_Rectangle();
		int ID_Point = Integer.parseInt(request.getParameter("IdPoint"));
		
		Rectangle2 obj = daoc.ReadPoint(ID_Point);
		
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
	public int genererNouvelIDRectangle() {

		DAO_Rectangle daor = new DAO_Rectangle();
		ArrayList<Integer> listeIDs = new ArrayList<Integer>();

		for (Rectangle2 c : daor.ReadAll())
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
