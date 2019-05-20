package m2i.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MaServlet
 */
@WebServlet("/MaServlet")
public class MaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter(); // Permet d'afficher du texte dans la page web via les méthodes println()
												// ou append()
		pw.println("doGet() en action : ");

		int nb1 = Integer.parseInt(request.getParameter("txtNombre1"));
		int nb2 = Integer.parseInt(request.getParameter("txtNombre2"));

		String additionObjet = nb1 + " + " + nb2 + " = " + (nb1 + nb2); // Crée plusieurs objets
		String additionFormat = String.format("%d + %d = %d", nb1, nb2, nb1 + nb2); // Formate le String (plus efficace)

//		Afficher du texte mis en forme par des balises HTML
		String additionHTML = "<table class='table'><tr><th>Nombre 1</th><th>Nombre 2</th><th>Addition</th></tr>";
		additionHTML += "<tr><td>" + nb1 + "</td><td>" + nb2 + "</td><td>" + (nb1 + nb2) + "</td></tr></table>";

		response.setContentType("text/html"); // Pour du HTML, préciser d'abord que le type de contenu est du HTML
												// plutôt que du "plain text"

		pw.print(additionHTML + "\n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("doPost() en action : ");
	}

}
