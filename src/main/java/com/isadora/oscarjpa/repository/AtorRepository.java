package com.isadora.oscarjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isadora.oscarjpa.model.Ator;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
	
	   Optional<Ator> findOneByNome(String nome);

}
