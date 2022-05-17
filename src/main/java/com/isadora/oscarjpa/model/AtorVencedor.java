package com.isadora.oscarjpa.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "\"Ator_Vencedor\"", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class AtorVencedor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Oscar_id",referencedColumnName = "id")
	private Oscar oscar;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn (name = "Ator_id", referencedColumnName = "id")
	private Ator ator;
	
	
	public AtorVencedor(Oscar oscar, Ator ator) {
		super();
		
		this.oscar = oscar;
		this.ator = ator;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Oscar getOscar() {
		return oscar;
	}
	public void setOscar(Oscar oscar) {
		this.oscar = oscar;
	}
	public Ator getAtor() {
		return ator;
	}
	public void setAtor(Ator ator) {
		this.ator = ator;
	}
	public AtorVencedor() {
		super();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AtorVencedor that = (AtorVencedor) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
