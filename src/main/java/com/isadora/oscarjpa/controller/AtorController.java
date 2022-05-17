package com.isadora.oscarjpa.controller;


import com.isadora.oscarjpa.exception.AtorJaCadastradoException;
import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class AtorController {
    @Autowired
    private AtorService atorService;

    @GetMapping
    public List<Ator> litarTodos(){
        return atorService.litarTodos();
    }

    @GetMapping("/busca/{nome}")
    public List<Ator> filterAtor(@PathVariable String  nome ){
        return atorService.buscaNome(nome);
    }
    @PostMapping
    public ResponseEntity salvarAtor(@RequestBody Ator ator){
        this.atorService.salvarNovoAtor(ator);
        ResponseEntity response = new ResponseEntity("Ator criado", HttpStatus.CREATED);

        return response;

    }
    @ExceptionHandler
    public ResponseEntity tratarAtorExistente(AtorJaCadastradoException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return  response;
    }
    @DeleteMapping("{nome}")
    public String deletarAtor(@PathVariable String nome){
        this.atorService.deletar(nome);
        return "Ator Deletado!";
    }

}
