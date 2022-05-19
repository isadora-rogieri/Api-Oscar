package com.isadora.oscarjpa.service;

import com.isadora.oscarjpa.exception.AtorJaCadastradoException;
import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtorService {
    @Autowired
    private AtorRepository atorRepository;

    public List<Ator> litarTodos() {
        return atorRepository.findAll();
    }

    public List<Ator> buscaNome(String nome) {
        var optional =  atorRepository.findAll();
        List<Ator> result = optional.stream().filter((a) -> a.getNome().toLowerCase()
                .contains(nome.toLowerCase())).collect(Collectors.toList());

        if (result != null){
           return result;
        }
        return List.of();
    }

    public Ator salvarNovoAtor(Ator ator) {

        if(!this.atorRepository.existsByNome(ator.getNome())){
            this.atorRepository.save(ator);
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
