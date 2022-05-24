package com.isadora.oscarjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Oscar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "Ano obrigatório")
	private Integer ano;

	@Column(nullable = false)
	@NotBlank(message = "Nome filme obrigatório")
	@Size(min = 2, max = 100)
	private String nomeFilme;

	@Column(nullable = false)
	@NotNull(message = "Idade do Ator obrigatório")
	private Integer idadeAtor;

	public Oscar(Integer ano, String nomeFilme, Integer idadeAtor) {
		super();
		
		this.ano = ano;
		this.nomeFilme = nomeFilme;
		this.idadeAtor = idadeAtor;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
    }

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public Integer getIdadeAtor() {
		return idadeAtor;
	}

	public void setIdadeAtor(int idadeAtor) {
		this.idadeAtor = idadeAtor;
	}

	public Oscar() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Oscar oscar = (Oscar) o;
		return nomeFilme.equals(oscar.nomeFilme);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeFilme);
	}
}
