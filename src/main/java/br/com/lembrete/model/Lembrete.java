package br.com.lembrete.model;

public class Lembrete {
	
	private Integer id;
	private String titulo;
	private String descricao;
	
	// necessário para o Jersey
	public Lembrete() {
		// TODO Auto-generated constructor stub
	}

	public Lembrete(Integer id, String titulo, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
