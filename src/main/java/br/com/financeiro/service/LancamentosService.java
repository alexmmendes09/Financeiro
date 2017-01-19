/**
 * 
 */
package br.com.financeiro.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.financeiro.dao.LancamentosDAO;
import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Lancamento;

/**
 * @author mendesa
 * 
 */
public class LancamentosService {

	private LancamentosDAO lancamento;

	@Inject
	public LancamentosService(LancamentosDAO lancamento) {
		this.lancamento = lancamento;
	}

	public void guardar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null
				&& lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura");
		}
		this.lancamento.guardar(lancamento);
	}

	public List<Lancamento> consultar() throws NegocioException {
		return this.lancamento.todos();
	}

	public void porId(Long id) {
		this.lancamento.porId(id);
	}

	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamento.porId(lancamento.getId());
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException(
					"Não é possível excluir um lançamento pago!");
		}
		this.lancamento.remover(lancamento);
	}

	public List<Lancamento> porMesAno(String monthYear) {
		return this.lancamento.porMesAno(monthYear);
	}
	
	public List<Lancamento> porMes(int month) {
		return this.lancamento.porMes(month);
	}
	
	public List<Lancamento> porMesAnoRecorrente(int month, int year) {
		return this.lancamento.porMesAnoRecorrente(month,year);
	}
	
	public List<String> descricoesAnosValidos(){
		return this.lancamento.descricoesAnosValidos();
	}

}
