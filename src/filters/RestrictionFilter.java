package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(filterName = "RestrictionFilter",
urlPatterns = {"/*"})
public class RestrictionFilter implements Filter {
	   public static final String ACCES_CONNEXION  = "/WEB-INF/Connection.jsp";
	    public static final String ATT_SESSION_USER = "user";
		private static final String ACCES_Inscription = "/WEB-INF/Inscription.jsp";
		private static final String ACCES_Home_buyer = "/WEB-INF/homeSite.jsp";
		private static final String ACCES_contactB = "/WEB-INF/ContactBuyer.jsp";
		private static final String ACCES_Oubli = "/WEB-INF/Forgetting.jsp";
		private static final String ACCES_ConnectionDashboard = "/WEB-INF/ConnectionDashboard.jsp";
		private static final String ATT_SESSION_admin = "admin";


	    public void init( FilterConfig config ) throws ServletException {
	    }

	    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
	            ServletException {
	        /* Cast des objets request et response */
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;

	        /* Non-filtrage des ressources statiques */
	        String chemin = request.getRequestURI().substring( request.getContextPath().length() );

	        HttpSession session = request.getSession();

	        if ( chemin.startsWith( "/inc" ) ) {
	            chain.doFilter( request, response );
	            return;
	        }
	        
	        if ( chemin.startsWith( "/CSS" ) ) {
	            chain.doFilter( request, response );
	            return;
	        }
	        
	        if ( chemin.startsWith( "/images" ) ) {
	            chain.doFilter( request, response );
	            return;
	        }

	        if ( chemin.startsWith( "/dashboard" ) ) {
	        	 if ( chemin.startsWith( "/dashboardconnection" ) ) 
	 	            chain.doFilter( request, response );
	        	if ( session.getAttribute( ATT_SESSION_admin ) == null ) {
	                /* Redirection vers la page publique */
	                request.getRequestDispatcher( ACCES_ConnectionDashboard ).forward( request, response );
	            } else {
	                /* Affichage de la page restreinte */
	                chain.doFilter( request, response );
	            }
	        }
	        else
	        
       System.out.println(chemin);
        if ( chemin.equals( "/registration" ) ) {
        	chain.doFilter( request, response );

        }
        
        else if ( chemin.equals( "/forgetting" ) ) {
        	chain.doFilter( request, response );

        }
        else if ( chemin.equals( "/homeBuyer" ) ) {
        	chain.doFilter( request, response );

        }
        else if ( chemin.equals( "/contactBuyer" ) ) {
        	chain.doFilter( request, response );

        }
        else if ( chemin.equals( "/connexion" ) ) {
        	chain.doFilter( request, response );

        }
        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connect�.
         */
        else  if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            request.getRequestDispatcher( ACCES_CONNEXION ).forward( request, response );
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( request, response );}
        
    }

    public void destroy() {
    }
}