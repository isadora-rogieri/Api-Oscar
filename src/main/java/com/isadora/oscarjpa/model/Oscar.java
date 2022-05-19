package com.isadora.oscarjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Oscar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Ano obrigatório")
	@Size(min = 4, max = 4)
	private int ano;

	@Column(nullable = false)
	@NotBlank(message = "Nome filme obrigatório")
	@Size(min = 2, max = 100)
	private String nomeFilme;

	@Column(nullable = false)
	@NotBlank(message = "Idade do Ator obrigatório")
	@Size(min = 2, max = 3)
	private int idadeAtor;

	public Oscar(int ano, String nomeFilme, int idadeAtor) {
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

	public int getAno() {
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

	public int getIdadeAtor() {
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
