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

		String btnAction = request.getParameter("btnAction");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

//		Afficher la table des employés quand le bouton "Select" est cliqué
		if (btnAction.equals("Select")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));
			out.append(HTMLDynamique.TableauEmployes(idSociete));

		}
//		Créer un formulaire dans l'élément "collapseSociete"
		else if (btnAction.contentEquals("Update")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));
			out.append(HTMLDynamique.formulaireUpdate(idSociete));

		}
//		Modifier une entrée du tableau de sociétés
		else if (btnAction.contentEquals("ModifierSociete")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));

//			Récupérer les nouvelles valeurs de la société
			String nom = request.getParameter("newNom");
			float CA = Float.parseFloat(request.getParameter("newCA"));
			Activites act = Activites.valueOf(request.getParameter("newActivite"));

//			Modifier l'entrée de la table 'Societe'
			DAO_Societe daos = new DAO_Societe();

			Societe s = daos.Read(idSociete);
			s.set_Nom(nom);
			s.set_CA(CA);
			s.set_Activite(act);
			s.set_Nb_Employes(0);

			System.out.println("Nouvelle société : " + s.toString());

			daos.Update(s);

//			Supprimer tous les employés dont l'ID Société est celui passé en paramètre
			DAO_Personne daop = new DAO_Personne();
			daop.DeleteAll(idSociete);

//			Modifier le tableau de sociétés dans la page HTML
			out.append(HTMLDynamique.TableauSocietes());
		}
//		Supprimer la table des employés + la ligne société quand le bouton "Delete" est cliqué
		else if (btnAction.equals("Delete")) {

			int idSociete = Integer.parseInt(request.getParameter("IdSociete"));

			DAO_Societe daos = new DAO_Societe();
			daos.Delete(idSociete);

//			Récupérer les employés des sociétés enregistrées
			DAO_Personne daop = new DAO_Personne();
			ArrayList<Personne> lstEmployes = daop.ListeEmployesSociete(idSociete);

			out.append(HTMLDynamique.TableauSocietes());
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doDelete(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
	}
}
