package br.com.financeiro.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@ViewScoped
public class SessionUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static String getUserNameSession() {
		HttpSession session = getRequest().getSession();
		String userNameSession = (String) session.getAttribute("username");
		return userNameSession;
	}
	
}
