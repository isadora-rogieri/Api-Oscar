package com.isadora.oscarjpa.service;

import com.isadora.oscarjpa.exception.AtorJaCadastradoException;
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

    public Ator salvarNovoAtor(Ator ator) {

        if(!this.atorRepository.existsByNome(ator.getNome())){
            //return this.atorRepository.save(ator);
        }else{
           throw  new AtorJaCadastradoException();
        }
        return ator;
    }

    public void deletar(String nome) {
        Ator ator = this.atorRepository.findByNome(nome);
        this.atorRepository.delete(ator);
    }
}
