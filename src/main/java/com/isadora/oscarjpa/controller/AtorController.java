package com.isadora.oscarjpa.controller;


import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
