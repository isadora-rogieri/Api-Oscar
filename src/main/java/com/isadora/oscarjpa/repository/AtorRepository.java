package com.isadora.oscarjpa.repository;

import com.isadora.oscarjpa.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long> {
	

	   boolean existsByNome(String nome);
	   Optional<Ator> findById(Long id);
	   Ator findByNome(String nome);


}
