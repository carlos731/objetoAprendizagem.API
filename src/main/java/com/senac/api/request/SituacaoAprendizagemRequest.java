package com.senac.api.request;

import jakarta.validation.constraints.NotNull;

public class SituacaoAprendizagemRequest{

	private Long id;
	@NotNull(message = "O campo titulo deve ser preenchido!!")
	private String titulo;
	@NotNull(message = "O campo descricao deve ser preenchido!!")
	private String descricao;
	@NotNull(message = "O campo ordem deve ser preenchido!!")
	private Boolean ordem;
	@NotNull(message = "O campo status deve ser preenchido!!")
	private Integer status;
	@NotNull(message = "O campo planejamento da uc deve ser preenchido!!")
	private Long planejamentoUcId;
	@NotNull(message = "O campo graud e dificuldade deve ser preenchido!!")
	private Long grauDificuldadeId;
	@NotNull(message = "O campo badge deve ser preenchido!!")
	private Long badgeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Boolean getOrdem() {
		return ordem;
	}
	public void setOrdem(Boolean ordem) {
		this.ordem = ordem;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPlanejamentoUcId() {
		return planejamentoUcId;
	}
	public void setPlanejamentoUcId(Long planejamentoUcId) {
		this.planejamentoUcId = planejamentoUcId;
	}
	public Long getGrauDificuldadeId() {
		return grauDificuldadeId;
	}
	public void setGrauDificuldadeId(Long grauDificuldadeId) {
		this.grauDificuldadeId = grauDificuldadeId;
	}
	public Long getBadgeId() {
		return badgeId;
	}
	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

}
