package br.com.financeiro.service;

import javax.inject.Inject;

import br.com.financeiro.dao.UsuarioDAO;
import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Usuario;

public class UsuarioService {
	
	private UsuarioDAO usuarioDAO;

	@Inject
	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public Usuario consultar(String nomeUsuario, String password ) throws NegocioException {
		return this.usuarioDAO.getUsuario(nomeUsuario, password);
	}
}
