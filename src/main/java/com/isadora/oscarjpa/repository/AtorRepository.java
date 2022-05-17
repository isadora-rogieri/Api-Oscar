package com.isadora.oscarjpa.repository;

import com.isadora.oscarjpa.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
	
	   List<Ator> findByNomeContaining(String nome);


}
