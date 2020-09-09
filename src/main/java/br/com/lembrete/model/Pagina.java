package br.com.lembrete.model;

import java.util.List;

public class Pagina {
	private List<Lembrete> dados;
	
	public Pagina() {
		// TODO Auto-generated constructor stub
	}

	public Pagina(List<Lembrete> dados) {
		this.dados = dados;
	}
	
	public List<Lembrete> getDados() {
		return dados;
	}
	public void setDados(List<Lembrete> dados) {
		this.dados = dados;
	}
}


















