package br.com.financeiro.manterLancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import br.com.financeiro.dao.CategoriasDAO;
import br.com.financeiro.dao.LancamentosDAO;
import br.com.financeiro.dao.MonthDAO;
import br.com.financeiro.dao.PessoasDAO;
import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Categoria;
import br.com.financeiro.model.Lancamento;
import br.com.financeiro.model.Month;
import br.com.financeiro.model.Pessoa;
import br.com.financeiro.model.TipoLancamento;
import br.com.financeiro.model.Usuario;
import br.com.financeiro.service.LancamentosService;
import br.com.financeiro.util.SessionUtil;
import br.com.financeiro.util.Transactional;
import br.com.financeiro.util.UtilFormatter;

@Named
@ViewScoped
public class ManterLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private PessoasDAO pessoas;
	@Inject
	private CategoriasDAO categorias;
	@Inject
	private Lancamento lancamento;
	private List<Pessoa> todasPessoas;
	private List<Categoria> todasCategorias;
	@Inject
	private LancamentosDAO lancamentosRepository;
	private List<Lancamento> lancamentos;
	@Inject
	private LancamentosService lancamentosService;
	private Lancamento lancamentoSelecionado;
	private String EXCLUSAO = "Lançamento excluído com sucesso!";
	private String INCLUSAO = "Lançamento efetuado com sucesso";
	private String CONSULTA = "/manterLancamentos/ConsultaLancamentos?faces-redirect=true";
	private String SUCESS = "Ação realizada com sucesso!";
	private String ERROR = "Ação não permitida!";
	private int currentTab = 1;
	private Integer activeTab;
	@Inject
	private Usuario usuario;
	private List<Month> listaMonths;
	@Inject
	private MonthDAO monthDAO;

	public List<Month> getListaMonths() {
		this.listaMonths = monthDAO.todos();
		return listaMonths;
	}

	public void setListaMonths(List<Month> listaMonths) {
		this.listaMonths = listaMonths;
	}

	public int getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}
	public Integer getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(Integer activeTab) {
        this.activeTab = activeTab;
    }

	/** Getters and Setters **/
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public List<Pessoa> getTodasPessoas() {
		this.todasPessoas = pessoas.todas();
		return todasPessoas;
	}

	public void setTodasPessoas(List<Pessoa> todasPessoas) {
		this.todasPessoas = todasPessoas;
	}

	public List<Categoria> getTodasCategorias() {
		this.todasCategorias = categorias.todas();
		return todasCategorias;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentosRepository.descricoesQueContem(descricao);
	}

	public PessoasDAO getPessoas() {
		return pessoas;
	}

	public void setPessoas(PessoasDAO pessoas) {
		this.pessoas = pessoas;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public LancamentosService getLancamentosService() {
		return lancamentosService;
	}

	public void setLancamentosService(LancamentosService lancamentosService) {
		this.lancamentosService = lancamentosService;
	}

	public String getSomaValores() {
		BigDecimal total = new BigDecimal(0);
		for (Lancamento lancamento : getLancamentosService().porMes(currentTab)) {
			if (lancamento.getTipo().getDescricao().equals("Receita")) {
				total = total.add(lancamento.getValor());
			} else {
				total = total.subtract(lancamento.getValor());
			}
		}
		return UtilFormatter.formatReal(total.toString());
	}

	/** Methods **/

	@PostConstruct
	public void consultar() throws NegocioException {
		setLancamentos(getLancamentosService().porMes(getCurrentTab()));
		SessionUtil.getUserNameSession();
		
	}

	@Transactional
	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			getLancamentoSelecionado().setUsername(SessionUtil.getUserNameSession());
			this.lancamentosService.excluir(getLancamentoSelecionado());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, SUCESS, EXCLUSAO));
			consultar();
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			context.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR, ERROR, mensagem.toString()));
		}
	}

	public void prepararCadastro() {
		getLancamento();
		setTodasPessoas(getPessoas().todas());
	}

	public void lancamentoPagoModificado(ValueChangeEvent event) {
		this.lancamento.setIsPago((Boolean) event.getNewValue());
		this.lancamento.setDataPagamento(null);
		FacesContext.getCurrentInstance().renderResponse();
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

	public void registrarLogCadastro(ActionEvent event) throws NegocioException {
		System.out.println("Cadastrando...");
		System.out.println("Tipo: " + this.lancamento.getTipo());
		System.out.println("Pessoa: " + this.lancamento.getPessoa().getNome());
		System.out.println("Categoria"
				+ this.lancamento.getCategoria().getDescricao());
		System.out.println("Descrição: " + this.lancamento.getDescricao());
		System.out.println("Valor: " + this.lancamento.getValor());
		System.out.println("Data vencimento: "
				+ this.lancamento.getDataVencimento());
		System.out.println("Conta paga: " + this.lancamento.getIsPago());
		System.out.println("Data pagamento: "
				+ this.lancamento.getDataPagamento());
		System.out.println("Usuário:" + this.lancamento.getUsername());
		getLancamentosService().guardar(this.lancamento);
	}

	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Novo valor: " + event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void onTabChange(TabChangeEvent event) {
		TabView tv = (TabView) event.getComponent();
		this.currentTab = tv.getActiveIndex() + 1;
		setActiveTab(activeTab);
		setLancamentos(getLancamentosService().porMes(getCurrentTab()));
	}
}