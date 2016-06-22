/**
 * 
 */
package br.com.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.financeiro.dao.LancamentosDAO;
import br.com.financeiro.model.Lancamento;
import br.com.financeiro.util.CDILocator;

/**
 * @author mendesa
 * 
 */
@FacesConverter(forClass = Lancamento.class)
public class LancamentosConverter implements Converter {
	// @Inject (ainda não é suportado)
	private LancamentosDAO lancamentos;

	public LancamentosConverter() {
		this.lancamentos = CDILocator.getBean(LancamentosDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Lancamento retorno = null;
		if (value != null) {
			retorno = this.lancamentos.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Lancamento lancamento = ((Lancamento) value);
			return lancamento.getId() == null ? null : lancamento.getId()
					.toString();
		}
		return null;
	}
}