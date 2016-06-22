/**
 * 
 */
package br.com.financeiro.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financeiro.model.Lancamento;
import br.com.financeiro.util.SessionUtil;

/**
 * @author mendesa
 * 
 */
public class LancamentosDAO {

	private EntityManager manager;

	@Inject
	public LancamentosDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public LancamentosDAO() {
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",
				Lancamento.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
						+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}

	public Lancamento guardar(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}
	

	public List<Lancamento> porMes(int month) {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento l where MONTH(L.dataVencimento) = " + month +
				" AND L.username = '" + SessionUtil.getUserNameSession()+"'",
				Lancamento.class);
		return query.getResultList();
	}
}
