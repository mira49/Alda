package Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.mail.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.AnnouncementDAO;
import DAO.FormValidationException;
import Entities.Annonces;
import Entities.User;
import eu.medsea.mimeutil.MimeUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "add_Announcement", urlPatterns = { "/add_Announcement" }, initParams = {
		@WebInitParam(name = "chemin", value = "/fichiers/images/") })
public class Add_Announcement extends HttpServlet {
	private String resultat;
	public static final String VUE = "/WEB-INF/Add_Announcement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	private Map<String, String> erreurs = new HashMap<String, String>();
	public static final String VUEAfter = "/WEB-INF/Home_user.jsp";
	private static final int TAILLE_TAMPON = 10240;// 10ko
	private String filename;

	@EJB
	private AnnouncementDAO annoucement;

	@Override
	public void init() throws ServletException {
		filename = getInitParameter("chemin");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();

		/* Affichage de la page restreinte */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Annonces annonce = new Annonces();
		
		
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {
					/*
					 * Traiter les champs classiques ici (input
					 * type="text|radio|checkbox|etc", select, etc).
					 */
					String nomChamp = item.getFieldName();
					String valeurChamp = item.getString();
					switch (nomChamp) {
					
					case "Description":
						annonce.setDescription(valeurChamp);
						break;
					case "postal_code":
						annonce.setPostal_code(Integer.parseInt(valeurChamp));
						break;
					case "Price":
						annonce.setPrice(Integer.parseInt(valeurChamp));
						break;
					case "Surface":
						annonce.setSurface(Integer.parseInt(valeurChamp));
						break;
					case "Town":
						annonce.setTown(valeurChamp);
						break;
					case "Name":
						annonce.setName(valeurChamp);
						break;
					case "id":
						System.out.println("la valeur du champ est:"  +valeurChamp);
						annonce.setId(Long.parseLong(valeurChamp));
						break;
					default:
						break;
					}

				} else {
					/*
					 * Traiter les champs de type fichier (input type="file").
					 */
					String nomChamp = item.getFieldName();
					String nomFichier = FilenameUtils.getName(item.getName());
					InputStream contenuFichier = item.getInputStream();

					switch (nomChamp) {

					case "picture3":
						annonce.setImage3(nomFichier);
						break;
					case "picture2":
						annonce.setImage2(nomFichier);
						break;
					case "picture1":
						annonce.setImage1(nomFichier);
						break;

					default:
						break;
					}
					traiterImage(request, filename, nomFichier, contenuFichier, nomChamp);
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("�chec de l'analyse de la requ�te multipart.", e);
		}
		try {

			if (erreurs.isEmpty()) {

				resultat = "Succ�s.";

			} else {
				resultat = "�chec.";
			}
		} catch (DAO.DAOException e) {
			resultat = "�chec : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace();
		}
		if ("Succ�s.".equals(resultat)) {

			String format = "dd/MM/yy H:mm:ss";
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
			java.util.Date dt = new java.util.Date();

			annonce.setUser((User) session.getAttribute("user"));
			annonce.setDate(dt);
			annonce.setSold(0);
						
			annoucement.create(annonce);
			this.getServletContext().getRequestDispatcher(VUEAfter).forward(request, response);
		} else {
			request.setAttribute("erreur", erreurs);
			request.setAttribute("resultat", resultat);

			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

		}

	}

	private void traiterImage(HttpServletRequest request, String chemin, String nom, InputStream co, String nomChamp) {
		/*
		 * String image = null; try { image = validationImage( request, chemin ,
		 * nom, co, nomChamp); } catch ( FormValidationException e ) {
		 * setErreur( nomChamp, e.getMessage() ); }
		 */
	}

	private String validationImage(HttpServletRequest request, String chemin, String nom, InputStream co,
			String nomChamp) throws FormValidationException {
		/*
		 * R�cup�ration du contenu du champ image du formulaire. Il faut ici
		 * utiliser la m�thode getPart().
		 */
		String nomFichier = nom;
		InputStream contenuFichier = co;
		try {
			Part part = (Part) request.getPart(nomChamp);
			/*
			 * Si la m�thode getNomFichier() a renvoy� quelque chose, il s'agit
			 * donc d'un champ de type fichier (input type="file").
			 */
			if (nomFichier != null && !nomFichier.isEmpty()) {
				/*
				 * Antibug pour Internet Explorer, qui transmet pour une raison
				 * mystique le chemin du fichier local � la machine du client...
				 * 
				 * Ex : C:/dossier/sous-dossier/fichier.ext
				 * 
				 * On doit donc faire en sorte de ne s�lectionner que le nom et
				 * l'extension du fichier, et de se d�barrasser du superflu.
				 */
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
						.substring(nomFichier.lastIndexOf('\\') + 1);

				/* Extraction du type MIME du fichier depuis l'InputStream */
				MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
				Collection<?> mimeTypes = MimeUtil.getMimeTypes(contenuFichier);

				/*
				 * Si le fichier est bien une image, alors son en-t�te MIME
				 * commence par la cha�ne "image"
				 */
				if (mimeTypes.toString().startsWith("image")) {
					/* �criture du fichier sur le disque */
					ecrireFichier(contenuFichier, nomFichier, chemin);
				} else {
					throw new FormValidationException("Le fichier envoy� doit �tre une image.");
				}
			}
		} catch (IllegalStateException e) {
			/*
			 * Exception retourn�e si la taille des donn�es d�passe les limites
			 * d�finies dans la section <multipart-config> de la d�claration de
			 * notre servlet d'upload dans le fichier web.xml
			 */
			e.printStackTrace();
			throw new FormValidationException("Le fichier envoy� ne doit pas d�passer 1Mo.");
		} catch (IOException e) {
			/*
			 * Exception retourn�e si une erreur au niveau des r�pertoires de
			 * stockage survient (r�pertoire inexistant, droits d'acc�s
			 * insuffisants, etc.)
			 */
			e.printStackTrace();
			throw new FormValidationException("Erreur de configuration du serveur.");
		} catch (ServletException e) {
			/*
			 * Exception retourn�e si la requ�te n'est pas de type
			 * multipart/form-data.
			 */
			e.printStackTrace();
			throw new FormValidationException(
					"Ce type de requ�te n'est pas support�, merci d'utiliser le formulaire pr�vu pour envoyer votre fichier.");
		}

		System.out.println(nomFichier);

		return nomFichier;
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private void ecrireFichier(InputStream contenuFichier, String nomFichier, String chemin)
			throws FormValidationException {
		System.out.println("hhh");

		/* Pr�pare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(contenuFichier, TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			/*
			 * Lit le fichier re�u et �crit son contenu dans un fichier sur le
			 * disque.
			 */
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} catch (Exception e) {
			throw new FormValidationException("Erreur lors de l'�criture du fichier sur le disque.");
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

}
