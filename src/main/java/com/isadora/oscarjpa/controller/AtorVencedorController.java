package com.isadora.oscarjpa.controller;


import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.service.AtorVencedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vencedor")
public class AtorVencedorController {


    @Autowired
    private AtorVencedorService atorVencedorService;


    @GetMapping
    public List<AtorVencedor> litarTodos(){
        return atorVencedorService.litarTodos();
    }

    @GetMapping("/mais-jovem")
    public AtorVencedor atorMaisJovem(){
        return  atorVencedorService.maisJovem();
}
    @GetMapping("/mais-premiada")
    public Ator atrizMaisPremiada(){
        return  atorVencedorService.encontreAtrizMaisPremiada();
    }

    @GetMapping("/mais-premiados")
    public Map<String, Long> litarVencecdoresDeMaisDeUmPremio(){
        return atorVencedorService.listarMaisDeUmPremio();
    }

}
