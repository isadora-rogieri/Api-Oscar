package com.isadora.oscarjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Oscar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int ano;

	@Column(nullable = false)
	private String nomeFilme;

	@Column(nullable = false)
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
