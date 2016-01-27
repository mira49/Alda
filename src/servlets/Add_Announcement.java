package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ejb.AnnouncementItf;
import entities.Annonces;
import entities.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

@WebServlet(name = "add_Announcement", urlPatterns = { "/add_Announcement" })
@MultipartConfig
public class Add_Announcement extends HttpServlet {
	private String resultat;
	public static final String VUE = "/WEB-INF/Add_Announcement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	private Map<String, String> erreurs = new HashMap<String, String>();
	public static final String VUEAfter = "/WEB-INF/Home_user.jsp";
	private String filename;
	
	@EJB
	private AnnouncementItf annoucement;

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Affichage de la page restreinte */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/");
		filename = path+"WEB-INF\\images";
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
					String nameFile = FilenameUtils.getName(item.getName());
					
					if(nameFile != null && !nameFile.isEmpty()){
						nameFile = nameFile.substring(nameFile.lastIndexOf('/')+1).substring( nameFile.lastIndexOf('\\')+1);
						if (nameFile.lastIndexOf(".") > 0) {
						    String ext = nameFile.substring(nameFile.lastIndexOf("."));
						    if (ext.equals(".png") || ext.equals(".jpeg") || ext.equals(".jpg")) {
						    	InputStream file = item.getInputStream();
						    	
						    	switch (nomChamp) {

								case "picture3":
									annonce.setImage3(nameFile);
									break;
								case "picture2":
									annonce.setImage2(nameFile);
									break;
								case "picture1":
									annonce.setImage1(nameFile);
									break;

								default:
									break;
								}
						    	
								writeFile(file,nameFile,filename);
								
								
						    }else{
						    	String error = "Unsupported format, the supported formats are .jpg and .png";
								setErreur( nomChamp, error );
								
						    }
						} else {
							String error = "Unsupported format, the supported formats are .jpg and .png";
							setErreur( nomChamp, error );
							
						}
					}
					
					
					
					
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("echec de l'analyse de la requete multipart.", e);
		}
		
		try {

			if (erreurs.isEmpty()) {

				resultat = "Succes.";

			} else {
				resultat = "echec.";
			}
		} catch (ejb.DAOException e) {
			resultat = "echec : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace();
		}
		if ("Succes.".equals(resultat)) {
			Calendar calendar = Calendar.getInstance();
			Date dt =  calendar.getTime();

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

	
	
	private void writeFile( InputStream file, String nomFichier, String chemin ) throws IOException {
		int SIZE_CACHE = 10240;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		System.out.println(chemin + nomFichier );

		File f = new File( chemin + nomFichier );
		try {
			in = new BufferedInputStream( file, SIZE_CACHE );
			out = new BufferedOutputStream( new FileOutputStream( f ),SIZE_CACHE );

			byte[] cache = new byte[SIZE_CACHE];
			int size = in.read(cache);

			while ( size > 0 ) {
				out.write( cache, 0, size );
				size = in.read(cache);
			}

			out.close();
			in.close();

		} catch ( IOException ignore ) {}
	}
	
	
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}
	
}