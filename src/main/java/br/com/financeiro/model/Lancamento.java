/**
 * 
 */
package br.com.financeiro.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author mendesa
 *
 */
@Entity
@Table(name = "lancamento")
public class Lancamento implements Cloneable {
	
	private Long id;
	private Pessoa pessoa;
	private Categoria categoria;
	private String descricao;
	private BigDecimal valor;
	private TipoLancamento tipo;
	private Date dataVencimento;
	private Date dataPagamento;
	private Boolean isPago;
	private String username;
	private Integer parcelas;
	private TipoPagto tipoPagto;
	private String session_id;
	private Integer num_parcelas;
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "pessoa_id")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "categoria_id")
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@NotEmpty
	@Column(length = 80, nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@NotNull
	@DecimalMin("0")
	@Column(precision = 10, scale = 2, nullable = false)
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoLancamento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", nullable = false)
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", nullable = true)
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	@NotNull
	@Column(name = "isPago")
	public Boolean getIsPago() {
		return isPago;
	}
	public void setIsPago(Boolean isPago) {
		this.isPago = isPago;
	}
	
	@NotEmpty
	@Column(length = 80, nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(length = 5, nullable = true)
	public Integer getParcelas() {
		return parcelas;
	}
	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public TipoPagto getTipoPagto() {
		return tipoPagto;
	}
	public void setTipoPagto(TipoPagto tipoPagto) {
		this.tipoPagto = tipoPagto;
	}
	
	@NotEmpty
	@Column(length = 255, nullable = false)
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	@Column(length = 5, nullable = true)
	public Integer getNum_parcelas() {
		return num_parcelas;
	}
	public void setNum_parcelas(Integer num_parcelas) {
		this.num_parcelas = num_parcelas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
}
