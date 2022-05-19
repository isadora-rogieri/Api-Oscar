package com.isadora.oscarjpa.controller;


import com.isadora.oscarjpa.exception.AtorJaCadastradoException;
import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.service.AtorVencedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/buscaNomeAtor/{nome}")
    public List<AtorVencedor> filterAtor(@PathVariable String  nome ){
        return atorVencedorService.buscaNomeAtor(nome);
    }
    @PostMapping
    public ResponseEntity salvarAtorVencedor(@RequestBody AtorVencedor atorVencedor){
        this.atorVencedorService.salvarNovoPremio(atorVencedor);
        ResponseEntity response = new ResponseEntity("Premio criado", HttpStatus.CREATED);

        return response;

    }
    @ExceptionHandler
    public ResponseEntity tratarAtorExistente(AtorJaCadastradoException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return  response;
    }
    @DeleteMapping("{id}")
    public String deletarAtorVencedor(@PathVariable("id") Long id) {
        this.atorVencedorService.deletar(id);
        return "Premio Deletado!";
    }
    @PutMapping("/{id}")
    public String alterarAtorVencedor(@PathVariable("id") Long id, @RequestBody AtorVencedor atorVencedor){
        this.atorVencedorService.alterar(id, atorVencedor);

        return "Alterado com sucesso!";

    }

}
