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
 * Servlet implementation class Servlet_Commune
 */
@WebServlet("/Servlet_Commune")
public class Servlet_Commune extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Commune() {
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

//		Permettre au code de transmettre du texte à un autre fichier (sources.js)
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		DAO_Rectangle daor = new DAO_Rectangle();
		DAO_Carre daoca = new DAO_Carre();
		DAO_Cercle daoce = new DAO_Cercle();

//		Liste d'objets
		ArrayList<Rectangle2> lstRectangles = daor.ReadAll();
		ArrayList<Carre2> lstCarres = daoca.ReadAll();
		ArrayList<Cercle2> lstCercles = daoce.ReadAll();

//		Liste d'objets finale (tous objets confondus)
		ArrayList<FormeGenerique> output = new ArrayList<FormeGenerique>();

		for (Rectangle2 r : lstRectangles) {

			int ID = r.get_coord().getID_Point();
			int X = r.get_coord().getX();
			int Y = r.get_coord().getY();
			float Largeur = r.get_Largeur();
			float Longueur = r.get_Longueur();
			float Perimetre = r.Perimetre();
			float Surface = r.Surface();

			output.add(new FormeGenerique(ID, X, Y, Longueur, Largeur, 0, 0, Perimetre, Surface));
		}

		for (Carre2 c : lstCarres) {

			int ID = c.get_coord().getID_Point();
			int X = c.get_coord().getX();
			int Y = c.get_coord().getY();
			float Cote = c.get_Cote();
			float Perimetre = c.Perimetre();
			float Surface = c.Surface();

			output.add(new FormeGenerique(ID, X, Y, 0, 0, Cote, 0, Perimetre, Surface));
		}

		for (Cercle2 c : lstCercles) {

			int ID = c.get_coord().getID_Point();
			int X = c.get_coord().getX();
			int Y = c.get_coord().getY();
			float Rayon = c.get_Rayon();
			float Perimetre = c.Perimetre();
			float Surface = c.Surface();

			output.add(new FormeGenerique(ID, X, Y, 0, 0, 0, Rayon, Perimetre, Surface));
		}

//		System.out.println("Collection de formes :\n" + output);

		Gson gson = new GsonBuilder().create();
		String resultat = gson.toJson(output);

		out.println(resultat);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
