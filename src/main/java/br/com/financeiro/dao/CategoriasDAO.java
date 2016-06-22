package br.com.financeiro.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financeiro.model.Categoria;

public class CategoriasDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private EntityManager manager;
	
	@Inject
	public CategoriasDAO(EntityManager manager) {
		this.manager = manager;
	}

	public CategoriasDAO() {
	}

	public Categoria porId(Long id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> todas() {
		TypedQuery<Categoria> query = manager.createQuery("from Categoria",
				Categoria.class);
		return query.getResultList();
	}

}
