package com.isadora.oscarjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Ator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Nome obrigatório")
	@Size(min = 5, max = 100)
	private String nome;
			
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotBlank(message = "Sexo obrigatório")
	private SexoEnum sexo;

	public Ator(String nome, SexoEnum sexo) {
		super();
		this.nome = nome;
		this.sexo = sexo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public  SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Ator() {
		super();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ator ator = (Ator) o;
		return nome.equals(ator.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
