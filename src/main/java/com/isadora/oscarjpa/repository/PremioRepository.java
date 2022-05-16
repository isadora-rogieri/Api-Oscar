package com.isadora.oscarjpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.Oscar;
@Repository
public interface PremioRepository extends JpaRepository<Oscar, Long> {

	Optional<Oscar> findOneByNomeFilme(String nomeFilme);

}
