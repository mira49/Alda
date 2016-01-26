package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import ejb.AnnouncementItf;
import ejb.UserItf;
import entities.*;

public abstract class AbstactQueryClass extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	protected AnnouncementItf dao;
	@EJB
	protected UserItf user_dao;
	
	public abstract void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public abstract void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public String sql_create_query(User user) {

		String factor_user = user.getFactor();
		String factor[] = new String[3];
		factor = factor_user.split(";");
		String sql ="SELECT u FROM Annonces u where ";
		
		if (!(StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1]))){
			sql += "u.price between " + factor[0] + " AND " + factor[1] + "AND ";
		}
		else{
			if (!(StringUtils.isBlank(factor[0]))){
				sql += "u.price >=" + factor[0] + " AND ";
			}
			if (!(StringUtils.isBlank(factor[1]))){
				sql += "u.price <=" + factor[1] + " AND ";
			}
		}
		
		if (!(StringUtils.isBlank(factor[2]))){
			sql += "u.postal_code =" + factor[2] + " AND ";
		}
		
		sql += " u.sold = 0 ";
		return sql;

	}

	public String sql_create_query_factor(String price_lower, String price_h, String location, String surface_lower,
			String surface_h) {
		String sql = "SELECT u FROM Annonces u where u.sold = 0 ";

		if (!price_lower.equals("")) {
			if (!price_h.equals(""))
				sql += "AND u.price between " + price_lower + " AND " + price_h;
			else
				sql += "AND u.price >=" + price_lower;
		} else {
			if (!price_h.equals(""))
				sql += "AND u.price <=" + price_h;
		}

		if (!surface_lower.equals("")) {
			if (!surface_h.equals(""))
				sql += "AND u.surface between " + surface_lower + " AND " + surface_h;
			else
				sql += "AND u.surface >=" + surface_lower;
		} else {
			if (!surface_h.equals(""))
				sql += "AND u.surface <=" + surface_h;
		}

		if (!location.equals("")) {
			sql += "AND u.postal_code =" + location;
		}

		return sql;
	}
	
	public List <Annonces> getAnnoncesList(String request){
		List<Annonces> list = new ArrayList<>();
		list = dao.findByFactor(request);
		return list;
	}
	
	public List <Boolean> getBooleanList(List <Annonces> annonces_tmp, User user){
		
		List<Boolean> bool =  new ArrayList<>() ;
		for (Annonces annonce : annonces_tmp) {
			bool.add(dao.findFavorite(user, annonce.getId().toString()));
		}
		return bool;
	}

}
