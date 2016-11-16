package br.com.financeiro.manterCategoria;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.persistence.PostLoad;

import br.com.financeiro.dao.CategoriasDAO;
import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Categoria;
import br.com.financeiro.model.Lancamento;
import br.com.financeiro.service.CategoriasService;
import br.com.financeiro.service.LancamentosService;
import br.com.financeiro.util.SessionUtil;
import br.com.financeiro.util.Transactional;
import br.com.financeiro.util.UtilFormatter;

public class ManterCategoriaBean {

	private Categoria categoria;
	@Inject
	private CategoriasDAO categorias;
	private List<Categoria> todasCategorias;
	@Inject
	private CategoriasService categoriasService;
	private Categoria categoriaSelecionada;
	private String EXCLUSAO = "Categoria excluída com sucesso!";
	private String INCLUSAO = "Categoria incluída com sucesso";
	private String CONSULTA = "/manterCategoria/ConsultaCategorias?faces-redirect=true";
	private String SUCESS = "Ação realizada com sucesso!";
	private String ERROR = "Ação não permitida!";
	
	/*
	@PostLoad
	public void loadComponents() throws NegocioException{
		consultar();
		SessionUtil.getUserNameSession();
	}
	
	@PostConstruct
	public void consultar() throws NegocioException {
		setLancamentos(this.categoriasService().consultar();
	}

	@Transactional
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.categoriaSelecionada.setUsername(SessionUtil.getUserNameSession());
			this.categoriasService.excluir(categoriaSelecionada);
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, SUCESS, EXCLUSAO));
			consultar();
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, ERROR, mensagem.toString()));
		}
	}

	@Transactional
	public String salvar() throws NegocioException {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			getLancamento().setUsername(SessionUtil.getUserNameSession());
			getLancamentosService().guardar(getLancamento());
			setLancamento(new Lancamento());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, SUCESS, INCLUSAO));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, ERROR, mensagem.toString()));
		}
		return CONSULTA;
	}
*/
}
