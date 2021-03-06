package com.isadora.oscarjpa.service;

import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.model.SexoEnum;
import com.isadora.oscarjpa.repository.AtorRepository;
import com.isadora.oscarjpa.repository.AtorVencedorReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class AtorVencedorService {
    @Autowired
    private  AtorVencedorReopository atorVencedorReopository;

    @Autowired
    private AtorRepository atorRepository;

    public AtorVencedor maisJovem() {
        List<AtorVencedor> maisJovem =this.atorVencedorReopository.findByAtor_Sexo(SexoEnum.M);
        AtorVencedor atorVencedor = maisJovem.stream().min(Comparator.comparingInt(a -> a.getOscar().getIdadeAtor())).orElseThrow();
    return  atorVencedor;
    }
    public List<AtorVencedor> litarTodos(){
        return atorVencedorReopository.findAll();
    }

    public Ator encontreAtrizMaisPremiada() {
        List<AtorVencedor> maisPremiada =this.atorVencedorReopository.findByAtor_Sexo(SexoEnum.F);
        Map<String, Long> nomes = maisPremiada.stream()
                .map(a -> a.getAtor().getNome())
                .collect(Collectors.groupingBy(oscar -> oscar, Collectors.counting()));
        Ator atriz  =  new Ator();
        nomes.entrySet().stream()
                 .max(Comparator.comparingLong(Entry::getValue))
                 .ifPresent(v -> {
                     atriz.setNome(v.getKey() + " - Ganhou: "+ v.getValue() + " Premios ");
                     atriz.getSexo();

                 } );

       return atriz ;
    }


    public Map<String, Long> listarMaisDeUmPremio() {
        List<AtorVencedor> maisPremiados = atorVencedorReopository.findAll();
        Map<String, Long> artistas = maisPremiados.stream()
                .map(a -> a.getAtor().getNome())
                .collect(Collectors.groupingBy(oscar -> oscar, Collectors.counting()));
        var map = artistas.entrySet().stream().filter(a -> a.getValue() >1)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return  map;
    }

    public List<AtorVencedor> buscaNomeAtor(String nome) {
        var optional =  atorVencedorReopository.findAll();
        List<AtorVencedor> result = optional.stream().filter((a) -> a.getAtor().getNome().toLowerCase()
                .contains(nome.toLowerCase())).collect(Collectors.toList());

        if (result != null ){
            return result;
        }
        return List.of();
    }

    public AtorVencedor salvarNovoPremio(AtorVencedor atorVencedor) {
        AtorVencedor premio;
        if(!this.atorRepository.existsByNome(atorVencedor.getAtor().getNome())){
            atorVencedor.setAtor(atorVencedor.getAtor());
            premio =   this.atorVencedorReopository.save(atorVencedor);
        }else{
            atorVencedor.setAtor(this.atorRepository.findByNome(atorVencedor.getAtor().getNome()));
             premio=  this.atorVencedorReopository.save(atorVencedor);
        }
        return premio;
    }

    public void deletar(Long id) {
        AtorVencedor atorVencedor = this.atorVencedorReopository.findOneById(id);
        this.atorVencedorReopository.delete(atorVencedor);
    }

    public void alterar(Long id, AtorVencedor atorVencedor) {
        AtorVencedor atorVencedor1 = this.atorVencedorReopository.findOneById(id);
        atorVencedor1.setAtor(atorVencedor.getAtor());
        atorVencedor1.setOscar(atorVencedor.getOscar());
        this.atorVencedorReopository.save(atorVencedor1);

    }
}

