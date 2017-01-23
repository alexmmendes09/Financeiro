package br.com.financeiro.manterLancamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PostLoad;

import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

import br.com.financeiro.dao.CategoriasDAO;
import br.com.financeiro.dao.LancamentosDAO;
import br.com.financeiro.dao.PessoasDAO;
import br.com.financeiro.exception.NegocioException;
import br.com.financeiro.model.Categoria;
import br.com.financeiro.model.Lancamento;
import br.com.financeiro.model.Meses;
import br.com.financeiro.model.Pessoa;
import br.com.financeiro.model.TipoLancamento;
import br.com.financeiro.model.TipoPagto;
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
	private List<String> listaAnosValidos;
	@Inject
	private LancamentosService lancamentosService;
	private Lancamento lancamentoSelecionado;
	private String EXCLUSAO = "Lançamento excluído com sucesso!";
	private String INCLUSAO = "Lançamento efetuado com sucesso";
	private String CONSULTA = "/manterLancamentos/ConsultaLancamentos?faces-redirect=true";
	private int currentTab = UtilFormatter.mesAtual();
	private Integer activeTab;
	private boolean parcelasDisable;
	private boolean qtdadeParcelas;
	private String tabAtualConsulta;
	@Inject
	private Usuario usuario;

	/**
	 * {@value}getters and setters
	 */

	public Meses[] getListaMeses() {
		return Meses.values();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public String getTabAtualConsulta() {
		return tabAtualConsulta;
	}

	public void setTabAtualConsulta(String tabAtualConsulta) {
		this.tabAtualConsulta = tabAtualConsulta;
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

	public TipoPagto[] getTiposPagtos() {
		return TipoPagto.values();
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

	public boolean isParcelasDisable() {
		return parcelasDisable;
	}

	public void setParcelasDisable(boolean parcelasDisable) {
		this.parcelasDisable = parcelasDisable;
	}

	public boolean isQtdadeParcelas() {
		return qtdadeParcelas;
	}

	public void setQtdadeParcelas(boolean qtdadeParcelas) {
		this.qtdadeParcelas = qtdadeParcelas;
	}

	/**
	 * {@code}Methods
	 */
	@PostLoad
	public void loadComponents() throws NegocioException {
		consultar();
	}

	@PostConstruct
	public void consultar() throws NegocioException {
		if (getListaAnosValidos().size() == 1) {
			setLancamentos(getLancamentosService().porMesAno(getListaAnosValidos().get(0)));
		} else if (getListaAnosValidos().size() == 0) {
			setLancamentos(getLancamentosService().porMes(0));
		} else {
			setLancamentos(getLancamentosService().porMesAno(getListaAnosValidos().get(getCurrentTab())));
		}
	}

	public void prepararCadastro() {
		if (getLancamento().getId() != null) {
			String msg = "Forma de pagamento não será mais modificada!";
			addMessage(msg);
			getLancamento();
			if (getLancamento().getTipoPagto().getDescricao().equals("Parcelado")) {
				setQtdadeParcelas(true);
			}
			setTodasPessoas(getPessoas().todas());
		}
	}

	private void addMessage(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentosRepository.descricoesQueContem(descricao);
	}

	public String getSomaValores() {
		BigDecimal total = new BigDecimal(0);
		if (getListaAnosValidos().size() <= 1) {
			for (Lancamento lancamento : getLancamentosService().porMesAno(listaAnosValidos.get(0))) {
				total = validaSomaValores(total, lancamento);
			}
		} else {
			for (Lancamento lancamento : getLancamentosService().porMesAno(listaAnosValidos.get(getCurrentTab()))) {
				total = validaSomaValores(total, lancamento);
			}
		}
		return UtilFormatter.formatReal(total.toString());
	}

	public List<String> getListaAnosValidos() {
		this.listaAnosValidos = this.lancamentosService.descricoesAnosValidos();
		return listaAnosValidos;
	}

	private BigDecimal validaSomaValores(BigDecimal total, Lancamento lancamento) {
		if (lancamento.getTipo().getDescricao().equals("Receita") && lancamento.getIsPago().equals(true)) {
			total = total.add(lancamento.getValor());
		} else {
			total = total.subtract(lancamento.getValor());
		}
		return total;
	}

	@Transactional
	public String salvar() throws NegocioException, CloneNotSupportedException {
		try {
			getLancamento().setUsername(SessionUtil.getUserNameSession());
			if (getLancamento().getTipoPagto().getDescricao().equals("Parcelado")) {
				Calendar c;
				Lancamento lancamentoClone;
				Random gerador = new Random();
				Integer complemento = gerador.nextInt();
				for (int i = 1; i <= lancamento.getParcelas(); i++) {
					c = Calendar.getInstance();
					c.setTime(lancamento.getDataVencimento());
					c.add(Calendar.MONTH, i);
					lancamentoClone = (Lancamento) lancamento.clone();
					lancamentoClone.setDataVencimento(c.getTime());
					lancamentoClone.setSession_id(SessionUtil.getSession().getId()+complemento.toString());
					lancamentoClone.setNum_parcelas(i);
					getLancamentosService().guardar(lancamentoClone);
				}
			} else {
				if (getLancamento().getId() == null) {
					getLancamento().setSession_id(SessionUtil.getSession().getId());
				}
				getLancamentosService().guardar(getLancamento());
			}
			setLancamento(new Lancamento());
			addMessage(INCLUSAO);
		} catch (NegocioException e) {
			addMessage(e.getMessage());
		}
		return CONSULTA;
	}

	@Transactional
	public void excluir() {
		try {
			getLancamentoSelecionado().setUsername(SessionUtil.getUserNameSession());
			this.lancamentosService.excluir(getLancamentoSelecionado());
			addMessage(EXCLUSAO);
			if (getListaAnosValidos().size() != 0) {
				setCurrentTab(0);
				setActiveTab(getCurrentTab());
			}
			loadComponents();
		} catch (NegocioException e) {
			addMessage(e.getMessage());
		}
	}

	@Transactional
	public void excluirParcelas() {
		try {
			getLancamentoSelecionado().setUsername(SessionUtil.getUserNameSession());
			this.lancamentosService.excluirParcelas(getLancamentoSelecionado());
			addMessage(EXCLUSAO);
			if (getListaAnosValidos().size() != 0) {
				setCurrentTab(0);
				setActiveTab(getCurrentTab());
			}
			loadComponents();
		} catch (NegocioException e) {
			addMessage(e.getMessage());
		}
	}

	public void lancamentoPagoModificado(ValueChangeEvent event) {
		this.lancamento.setIsPago((Boolean) event.getNewValue());
		this.lancamento.setDataPagamento(null);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void tipoPagtoModificado(ValueChangeEvent event) {
		String selectedVal = event.getNewValue().toString();
		if (selectedVal.equals("PARCELADO")) {
			setParcelasDisable(true);
		} else {
			setParcelasDisable(false);
		}
		this.lancamento.setParcelas(0);
		FacesContext.getCurrentInstance().renderResponse();
	}

	public void onTabChange(TabChangeEvent event) throws NegocioException {
		TabView tv = (TabView) event.getComponent();
		this.currentTab = tv.getActiveIndex();
		setTabAtualConsulta(event.getData().toString());
		setActiveTab(currentTab);
		setLancamentos(getLancamentosService().porMesAno(event.getData().toString()));
	}

}