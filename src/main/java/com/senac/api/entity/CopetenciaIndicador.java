package com.senac.api.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "copetencia_indicador")
public class CopetenciaIndicador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "copetencia_indicador_id")
	private Long id;
	@Column(name = "copetencia_indicador_descricao")
	private String descricao;
	@Column(name = "copetencia_indicador_status")
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "copetencia_id", referencedColumnName = "copetencia_id")
	private Copetencia copetenciaId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Copetencia getCopetenciaId() {
		return copetenciaId;
	}
	public void setCopetenciaId(Copetencia copetenciaId) {
		this.copetenciaId = copetenciaId;
	}
	

}
