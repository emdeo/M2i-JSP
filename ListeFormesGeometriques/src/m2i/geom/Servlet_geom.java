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

import m2i.geom.DAO_Formes;;

/**
 * Servlet implementation class Servlet_geom
 */
@WebServlet("/Servlet_geom")
public class Servlet_geom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_geom() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("\nGET en action\n");

//		Permettre au code de transmettre du texte à un autre fichier (sources.js)
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();

		DAO_Formes daof = new DAO_Formes();
		
		ArrayList<Object> Liste = daof.ReadAll();
		System.out.println("\nListe des Formes :\n" + Liste);
		Gson gson = new GsonBuilder().create();
		String resultat = gson.toJson(Liste);

		String type = request.getParameter("type");
		String IdForme = request.getParameter("IdForme");
		
		if (type != null) {

			int X = Integer.parseInt(request.getParameter("X"));
			int Y = Integer.parseInt(request.getParameter("Y"));

			int nouvelID = daof.ReadAll().size() + 1;

			if (type.equals("carre")) {
				
				float cote = Float.parseFloat(request.getParameter("cote"));
				daof.Create(new Carre(nouvelID, X, Y, cote));
				
			} else if (type.equals("cercle")) {
				
				float rayon = Float.parseFloat(request.getParameter("rayon"));
				daof.Create(new Cercle(nouvelID, X, Y, rayon));
				
			} else if (type.equals("rectangle")) {
				
				float longueur = Float.parseFloat(request.getParameter("longueur"));
				float largeur = Float.parseFloat(request.getParameter("largeur"));
				
				daof.Create(new Rectangle(nouvelID, X, Y, longueur, largeur));
				
			}
			
			ArrayList<Object> lstFormes = daof.ReadAll();
			System.out.println("\nListe des Formes :\n" + Liste);
			String resultat2 = gson.toJson(lstFormes);

//			Transmettre la liste de sociétés à 'sources.js'
			out.println(resultat2);
			out.close();
		}
		else if (IdForme != null) {
			
			String resultat3 = null;
			
			int id = Integer.parseInt(IdForme);
			String typeForme = request.getParameter("TypeForme");
			
			if(typeForme.contentEquals("carre")) {
				Carre output = (Carre) daof.Read(id);
				resultat3 = gson.toJson(output);
			}
			else if(typeForme.contentEquals("cercle")) {
				Cercle output = (Cercle) daof.Read(id);
				resultat3 = gson.toJson(output);
			}
			else if(typeForme.contentEquals("rectangle")) {
				Rectangle output = (Rectangle) daof.Read(id);
				resultat3 = gson.toJson(output);
			}
			
			out.println(resultat3);
			out.close();
			
		}
		
//		Transmettre la liste de sociétés à 'sources.js'
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
