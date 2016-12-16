package br.com.financeiro.model;

public enum Meses {
	
	JANEIRO("JANEIRO",0), 
	FEVEREIRO("FEVEREIRO",1),
	MARCO("MARCO",2),
	ABRIL("ABRIL",3),
	MAIO("MAIO",4),
	JUNHO("JUNHO",5),
	JULHO("JULHO",6),
	AGOSTO("AGOSTO",7),
	SETEMBRO("SETEMBRO",8),
	OUTUBRO("OUTUBRO",9),
	NOVEMBRO("NOVEMBRO",10),
	DEZEMBRO("DEZEMBRO",11);
	
	private String descricao;
	private int mes;

	Meses(String descricao, int mes) {
		this.descricao = descricao;
		this.mes = mes;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public int getMes(){
		return mes;
	}

}
