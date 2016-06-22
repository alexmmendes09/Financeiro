package br.com.financeiro.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financeiro.model.Month;

public class MonthDAO {

	private EntityManager manager;

	@Inject
	public MonthDAO(EntityManager manager) {
		this.manager = manager;
	}

	public MonthDAO() {
	}

	public Month porId(Long id) {
		return manager.find(Month.class, id);
	}

	public List<Month> todos() {
		TypedQuery<Month> query = manager.createQuery("from Month",
				Month.class);
		return query.getResultList();
	}

}
