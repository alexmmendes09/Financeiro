/**
 * 
 */
package br.com.financeiro.bean.manterLogin;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Usuario;
import br.com.financeiro.service.UsuarioService;
import br.com.financeiro.util.SessionUtil;
import br.com.financeiro.util.Transactional;

/**
 * @author mendesa
 * 
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String REDIRECT_LOGIN = "/manterLogin/login?faces-redirect=true";
	private String REDIRECT_SUCCESS = "/manterLancamentos/ConsultaLancamentos?faces-redirect=true";
	private String ERRO_USER = "Usuário e/ou senha inválidos!";
	private String ERROR_LOGIN = "Erro no login!";
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private Usuario usuario;

	@Transactional
	public String envia() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		usuario = usuarioService.consultar(this.usuario.getNomeUsuario(),
				this.usuario.getPassword());
		if (this.usuario != null) {
			HttpSession session = SessionUtil.getSession();
			session.setAttribute("username", usuario.getNomeUsuario());
			return REDIRECT_SUCCESS;
		} else {
			usuario = new Usuario();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							ERROR_LOGIN,ERRO_USER));
			return null;
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return REDIRECT_LOGIN;
	}

}