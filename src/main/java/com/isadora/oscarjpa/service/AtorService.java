package com.isadora.oscarjpa.service;

import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {
    @Autowired
    private AtorRepository atorRepository;

    public List<Ator> litarTodos() {
        return atorRepository.findAll();
    }

    public List<Ator> buscaNome(String nome) {
        var optional =  atorRepository.findByNomeContaining(nome);
        if (optional != null ){
           return optional;
        }
        return List.of();
    }
}
