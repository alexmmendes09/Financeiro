package br.com.financeiro.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.financeiro.model.Usuario;

public class UsuarioDAO {
	private EntityManager manager;

	@Inject
	public UsuarioDAO(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario getUsuario(String nomeUsuario, String password) {
		try {
			Usuario usuario = (Usuario) manager
					.createQuery(
							"SELECT u from Usuario u where u.nomeUsuario = :userName and u.password = :password")
					.setParameter("userName", nomeUsuario)
					.setParameter("password", password).getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean inserirUsuario(Usuario usuario) {
		try {
			manager.persist(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			manager.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
