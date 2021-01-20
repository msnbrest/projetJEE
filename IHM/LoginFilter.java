package fr.eni.eniEncheres.IHM;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import fr.eni.eniEncheres.BLL.IUtilisateurManager;
import fr.eni.eniEncheres.BLL.UtilisateurSingleton;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/EncheresServlet")
// TODO : faire un vrai lien?
public class LoginFilter implements Filter {
	
	IUtilisateurManager managerBll = UtilisateurSingleton.getInstance();

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
	
	
if (((HttpServletRequest)request).getSession().getAttribute("login")==null ||  ((HttpServletRequest)request).getSession().getAttribute("login").toString().equals("")){
		request.getRequestDispatcher("/LoginServlet").forward(request, response);
		
		if (((HttpServletRequest)request).getSession().getAttribute("login")!=null) {
			
					
			request.getRequestDispatcher("EniEncheres/EncheresServlet").forward(request, response);
		
	}
	}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
