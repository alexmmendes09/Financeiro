/**
 * 
 */
package br.com.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.financeiro.dao.PessoasDAO;
import br.com.financeiro.model.Pessoa;
import br.com.financeiro.util.CDILocator;
import br.com.financeiro.util.JpaUtil;

/**
 * @author mendesa
 * 
 */
@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {
	@Inject
	private Pessoa pessoas;

	public PessoaConverter() {
		this.setPessoas(CDILocator.getBean(Pessoa.class));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Pessoa retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null) {
				PessoasDAO pessoas = new PessoasDAO(manager);
				retorno = pessoas.porId(new Long(value));
			}
			return retorno;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((Pessoa) value).getId().toString();
		}
		return null;
	}

	public Pessoa getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoa pessoas) {
		this.pessoas = pessoas;
	}
}
