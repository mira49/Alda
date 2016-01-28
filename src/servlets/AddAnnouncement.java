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
public class AddAnnouncement extends HttpServlet {
	private String resultat;
	public static final String VUE = "/WEB-INF/AddAnnouncement.jsp";
	public static final String VUESucess = "/WEB-INF/Connection.jsp";
	private Map<String, String> erreurs = new HashMap<String, String>();
	public static final String VUEAfter = "/WEB-INF/HomeUser.jsp";
	private String file_path;
	
	@EJB
	private AnnouncementItf annoucement;

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* Affichage de la page restreinte */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/");
		file_path = path+"WEB-INF\\images\\";
		if (!new File(file_path).exists())
	        {
	           new File(file_path).mkdirs();
	 	        }
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
						traiteNumber(nomChamp,valeurChamp);
						annonce.setPostal_code(Integer.parseInt(valeurChamp));
						break;
					case "Price":
						traiteNumber(nomChamp,valeurChamp);
						annonce.setPrice(Integer.parseInt(valeurChamp));
						break;
					case "Surface":
						traiteNumber(nomChamp,valeurChamp);
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
									annonce.setImage3(file_path + nameFile);
									break;
								case "picture2":
									annonce.setImage2(file_path + nameFile);
									break;
								case "picture1":
									annonce.setImage1(file_path + nameFile);
									break;

								default:
									break;
								}
						    	
								CopyImage(file,nameFile,file_path);
								
								
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
			resultat = "fail : there is an error in add announcement.";
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

	
	

	

	private void traiteNumber(String nomChamp, String valeurChamp) {
		try{
			Float.parseFloat(valeurChamp);
		}
		catch(NumberFormatException e){
			String error = "Veuillez entrer un chiffre pour le champ :" + nomChamp;
			setErreur( nomChamp, error );
	    }
	}

	private void CopyImage( InputStream file, String namefile, String path ) throws IOException {
		int SIZE_CACHE = 10240;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		File f = new File( path + namefile );
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