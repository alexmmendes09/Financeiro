/**
 * 
 */
package br.com.financeiro.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financeiro.model.Pessoa;

/**
 * @author mendesa
 * 
 */
public class PessoasDAO {

	private EntityManager manager;

	@Inject
	public PessoasDAO(EntityManager manager) {
		this.manager = manager;
	}

	public PessoasDAO() {
		// TODO Auto-generated constructor stub
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = manager.createQuery("from Pessoa",
				Pessoa.class);
		return query.getResultList();
	}
}
