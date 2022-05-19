package com.isadora.oscarjpa.repository;

import com.isadora.oscarjpa.model.SexoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isadora.oscarjpa.model.AtorVencedor;

import java.util.List;

public interface AtorVencedorReopository extends JpaRepository<AtorVencedor, Long> {

    List<AtorVencedor> findByAtor_Sexo(SexoEnum sexoEnum);

    AtorVencedor findOneById(Long id);

}
