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
		this.manager.close();
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
						+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		this.manager.close();
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

	public List<Lancamento> porMesAno(String monthYear) {
		TypedQuery<Lancamento> query = null;
		try {
			query = manager.createQuery(
					"from Lancamento l where DATE_FORMAT(data_vencimento, '%M/%Y') = "
							+ "'"+monthYear + "'" + " AND L.username = '"
							+ SessionUtil.getUserNameSession() + "'",
					Lancamento.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.manager.close();
			e.printStackTrace();
		}
		return query.getResultList();
	}
	
	public List<Lancamento> porMes(int month) {
		TypedQuery<Lancamento> query = null;
		try {
			query = manager.createQuery(
					"from Lancamento l where MONTH(L.dataVencimento) = " + month
							+ " AND L.username = '"
							+ SessionUtil.getUserNameSession() + "'",
					Lancamento.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.manager.close();
			e.printStackTrace();
		}
		return query.getResultList();
	}

	public List<String> descricoesAnosValidos() {
		TypedQuery<String> query = manager
				.createQuery(
						"select concat(UPPER(DATE_FORMAT(data_vencimento,'%M')), '/' , Year(data_vencimento))"
								+ "From Lancamento GROUP BY data_vencimento order by data_vencimento ",
						String.class);
		return query.getResultList();
	}
}
