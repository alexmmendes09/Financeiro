package br.com.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.financeiro.dao.CategoriasDAO;
import br.com.financeiro.model.Categoria;
import br.com.financeiro.util.CDILocator;
import br.com.financeiro.util.JpaUtil;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

	@Inject
	private Categoria categoria;
	
	public CategoriaConverter() {
		this.setCategoria(CDILocator.getBean(Categoria.class));
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Categoria retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			if (value != null) {
				CategoriasDAO categorias = new CategoriasDAO(manager);
				retorno = categorias.porId(new Long(value));
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
			return ((Categoria) value).getId().toString();
		}
		return null;
	}


}
