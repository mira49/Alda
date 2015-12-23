package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import Entities.User;

public abstract class AbstactQueryClass extends HttpServlet {
	
	public abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public abstract void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	public String sql_create_query(User user) {

		String factor_user = user.getFactor();
		String sql = "SELECT u FROM Annonces u where u.sold = 0";
		String factor[] = new String[3];
		factor = factor_user.split(";");

		if (!(StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.price >=" + factor[0] + " AND u.sold = 0";
		}

		if ((StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.price <=" + factor[1] + " AND u.sold = 0";
		}

		if ((StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.postal_code =" + factor[2]+ " AND u.sold = 0";
		}

		if (!(StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && (StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.price between " + factor[0] + " AND " + factor[1]+ " AND u.sold = 0";
		}

		if (!(StringUtils.isBlank(factor[0])) && (StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.price >=" + factor[0] + " AND u.postal_code =" + factor[2]+ " AND u.sold = 0";
		}

		if ((StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
			sql = "SELECT u FROM Annonces u where u.price <=" + factor[1] + " AND u.postal_code =" + factor[2]+ " AND u.sold = 0";
		}
		  
		if (!(StringUtils.isBlank(factor[0])) && !(StringUtils.isBlank(factor[1])) && !(StringUtils.isBlank(factor[2]))) {
		  sql= "SELECT u FROM Annonces u where u.price between " + factor[0] +" AND " + factor[1] +" AND u.postal_code =" + factor[2]+ " AND u.sold = 0";
		 }

		return sql;

	}
	
	public String sql_create_query_factor(String price_lower, String price_h, String location, String surface_lower, String surface_h) {
		String sql = "SELECT u FROM Annonces u where u.sold = 0 ";
		
		if(!price_lower.equals(""))
			{
			if(!price_h .equals(""))
				sql += "AND u.price between " + price_lower +" AND " + price_h ;
			else sql += "AND u.price >=" + price_lower;
			}
		else {if(!price_h.equals(""))  sql += "AND u.price <=" + price_h;}
		
		if(!surface_lower.equals(""))
			{
			if(!surface_h.equals(""))
							sql += "AND u.surface between " + surface_lower +" AND " + surface_h ;
			else sql += "AND u.surface >=" + surface_lower;
			}
		else {if(!surface_h.equals(""))  sql += "AND u.surface <=" + surface_h;}
		
		if(!location.equals("")){
			sql+= "AND u.postal_code =" + location;
		}
		
	
		return sql;

	}
}
