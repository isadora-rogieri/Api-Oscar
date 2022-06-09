package com.isadora.oscarjpa.testesDeIntegracaoController;

import com.isadora.oscarjpa.controller.AtorVencedorController;
import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.model.Oscar;
import com.isadora.oscarjpa.model.SexoEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtorVencedorRestControllerIntegTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    AtorVencedorController atorVencedorController;

    private AtorVencedor atorVencedor;
    private AtorVencedor atorVencedor2;
    private AtorVencedor atorVencedor4;
    private List<AtorVencedor> atorVencedorList;


    @BeforeEach
    public void inicializar(){

        Ator ator = new Ator("Leonardo", SexoEnum.M);
        Oscar oscar = new Oscar(2020,"Filme teste",40);

        Ator ator2 = new Ator("Juliana",SexoEnum.F);
        Oscar oscar2 = new Oscar(2021,"Filme teste2",30);


        Ator ator4 = new Ator("Eduardo",SexoEnum.M);
        Oscar oscar4 = new Oscar(2019,"Filme teste4",28);

        atorVencedor = new AtorVencedor(oscar,ator);
        atorVencedor.setId(Long.valueOf(1));

        atorVencedor2 = new AtorVencedor(oscar2,ator2);
        atorVencedor2.setId(Long.valueOf(2));

        atorVencedor4 = new AtorVencedor(oscar4,ator4);
        atorVencedor4.setId(Long.valueOf(4));

        atorVencedorList = new ArrayList<>();

        atorVencedorList.add(atorVencedor);
        atorVencedorList.add(atorVencedor2);
        atorVencedorList.add(atorVencedor4);

    }

    @Test
    void listarTodos(){


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<AtorVencedor> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<AtorVencedor[]> response = this.restTemplate
                .exchange("/vencedor", HttpMethod.GET, httpEntity, AtorVencedor[].class);

        Assertions.assertEquals(200, response.getStatusCodeValue());

    }

   @Test
    void deletarAtorVencedor(){
       Ator ator6 = new Ator("Rafael",SexoEnum.M);
       Oscar oscar6 = new Oscar(2021,"Filme teste6",44);
       AtorVencedor atorVencedor6 = new AtorVencedor(oscar6, ator6);
       atorVencedor6.setId(Long.valueOf(6));


        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(atorVencedor6, httpHeaders);

        ResponseEntity<AtorVencedor> response = this.restTemplate
                .exchange("/vencedor/6", HttpMethod.DELETE, httpEntity, AtorVencedor.class);

        Assertions.assertEquals(200,response.getStatusCodeValue());

    }
}
