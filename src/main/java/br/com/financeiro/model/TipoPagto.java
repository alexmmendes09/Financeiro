package br.com.financeiro.model;

public enum TipoPagto {
	
	AVISTA("À Vista"), 
	PARCELADO("Parcelado");
	
	private String descricao;

	TipoPagto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
