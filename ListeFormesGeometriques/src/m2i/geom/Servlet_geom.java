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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nGET en action\n");

//		Permettre au code de transmettre du texte à un autre fichier (sources.js)
		response.setContentType("\"application/json\"");
		PrintWriter out = response.getWriter();

		DAO_Formes daof = new DAO_Formes();

		ArrayList<Object> Liste = daof.ReadAll();
		System.out.println("\nListe des Formes :\n" + Liste);
		Gson gson = new GsonBuilder().create();
		String resultat = gson.toJson(Liste);
		

//		Transmettre la liste de sociétés à 'sources.js'
		out.println(resultat);
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
