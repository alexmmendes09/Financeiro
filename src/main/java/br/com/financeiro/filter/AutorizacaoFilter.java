/**
 * 
 */
package br.com.financeiro.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.financeiro.model.Usuario;

/**
 * @author mendesa
 * 
 */
@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

	@Inject
	private Usuario usuario;
	private String REDIRECT_LOGIN = "/manterLogin/login.faces";
	private String RESOURCE = "/javax.faces.resource/";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		if (!usuario.isLogado()
				&& !request.getRequestURI().endsWith(REDIRECT_LOGIN)
				&& !request.getRequestURI().contains(RESOURCE)) {
			response.sendRedirect(request.getContextPath() + REDIRECT_LOGIN);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
