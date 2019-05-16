package m2i.BDD;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idSociete = Integer.parseInt(request.getParameter("IdSociete"));
		String btnAction = request.getParameter("btnAction");

		if (btnAction.equals("Select")) {
			DAO_Personne daop = new DAO_Personne();
			ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String thead = "<thead><tr><th>ID Personne</th><th>Nom</th><th>Prenom</th><th>Taille</th><th>Poids"
					+ "</th><th>Sexe</th><th>ID Société</th></tr></thead>";

			String tbody = "<tbody>";
			for (Personne p : lstEmployes) {
				tbody += "<tr><td>" + p.getID_Personne() + "</td><td>" + p.getNom() + "</td><td>" + p.getPrenom()
						+ "</td><td>" + p.getTaille() + "</td><td>" + p.getPoids() + "</td><td>" + p.getGenre()
						+ "</td><td>" + p.getID_Societe() + "</td></tr>";
			}
			tbody += "</tbody>";

			out.append(thead + tbody);
		} else if (btnAction.equals("Delete")) {

			PrintWriter pw = response.getWriter(); // Permet d'afficher du texte dans la page web via les méthodes println()
			// ou append()
			pw.println("idSociete :" + idSociete);

			DAO_Societe daos = new DAO_Societe();
			daos.Delete(idSociete);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

//		Récupérer les employés des sociétés enregistrées
			DAO_Personne daop = new DAO_Personne();
			ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

			ArrayList<Societe> lstSoc = daos.ReadAll();
			for (Societe s : lstSoc) {
				String IDSoc = "<td class='w-15'>" + s.get_ID_Societe() + "</td>";
				String Nom = "<td class='w-15'>" + s.get_Nom() + "</td>";
				String CA = "<td class='w-15'>" + s.get_CA() + "</td>";
				String Act = "<td class='w-15'>" + s.get_Activite() + "</td>";
				String Empl = "<td class='w-15'>" + s.get_Nb_Employes() + "</td>";

				String btnUpdate = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
						+ "' class='btnUpdate'><i class='fa fa-bars'></i></button></td>";

				String btnDelete = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
						+ "'class='btnDelete'><i class='fa fa-trash'></i></button></td>";

				out.println("<tr>" + IDSoc + Nom + CA + Act + Empl + btnUpdate + btnDelete + "</tr>");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doDelete(req, resp);

		int idSociete = Integer.parseInt(req.getParameter("IdSociete"));

		PrintWriter pw = resp.getWriter(); // Permet d'afficher du texte dans la page web via les méthodes println()
		// ou append()
		pw.println("idSociete :" + idSociete);

		DAO_Societe daos = new DAO_Societe();
		daos.Delete(idSociete);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

//		Récupérer les employés des sociétés enregistrées
		DAO_Personne daop = new DAO_Personne();
		ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

		ArrayList<Societe> lstSoc = daos.ReadAll();
		for (Societe s : lstSoc) {
			String IDSoc = "<td class='w-15'>" + s.get_ID_Societe() + "</td>";
			String Nom = "<td class='w-15'>" + s.get_Nom() + "</td>";
			String CA = "<td class='w-15'>" + s.get_CA() + "</td>";
			String Act = "<td class='w-15'>" + s.get_Activite() + "</td>";
			String Empl = "<td class='w-15'>" + s.get_Nb_Employes() + "</td>";

			String btnUpdate = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
					+ "' class='btnUpdate'><i class='fa fa-bars'></i></button></td>";

			String btnDelete = "<td class='w-5'><button data-idSociete='" + s.get_ID_Societe()
					+ "'class='btnDelete'><i class='fa fa-trash'></i></button></td>";

			out.println("<tr>" + IDSoc + Nom + CA + Act + Empl + btnUpdate + btnDelete + "</tr>");
		}
	}

}
