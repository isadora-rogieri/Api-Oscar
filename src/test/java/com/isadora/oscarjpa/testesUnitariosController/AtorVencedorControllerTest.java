package com.isadora.oscarjpa.testesUnitariosController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isadora.oscarjpa.controller.AtorVencedorController;
import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.model.Oscar;
import com.isadora.oscarjpa.model.SexoEnum;
import com.isadora.oscarjpa.service.AtorService;
import com.isadora.oscarjpa.service.AtorVencedorService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AtorVencedorController.class)
public class AtorVencedorControllerTest {

    @MockBean
    private AtorVencedorService atorVencedorService;



    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private AtorVencedor atorVencedor;
    private AtorVencedor atorVencedor2;
    private AtorVencedor atorVencedor4;
    private AtorVencedor atorVencedor5;

    private List<AtorVencedor> atorVencedorList;

    @BeforeEach
    public void inicializar(){

        Ator ator = new Ator("Leonardo", SexoEnum.M);
        Oscar oscar = new Oscar(2020,"Filme teste",40);

        Ator ator2 = new Ator("Juliana",SexoEnum.F);
        Oscar oscar2 = new Oscar(2021,"Filme teste2",30);

        Oscar oscar5 = new Oscar(2022,"Filme teste5",31);

        Ator ator4 = new Ator("Eduardo",SexoEnum.M);
        Oscar oscar4 = new Oscar(2019,"Filme teste4",28);

        atorVencedor = new AtorVencedor(oscar,ator);
        atorVencedor.setId(Long.valueOf(1));

        atorVencedor2 = new AtorVencedor(oscar2,ator2);
        atorVencedor2.setId(Long.valueOf(2));

        atorVencedor4 = new AtorVencedor(oscar4,ator4);
        atorVencedor4.setId(Long.valueOf(4));
        atorVencedor5 = new AtorVencedor(oscar5,ator2);
        atorVencedor5.setId(Long.valueOf(5));

        atorVencedorList = new ArrayList<>();
        atorVencedorList.add(atorVencedor);
        atorVencedorList.add(atorVencedor2);
        atorVencedorList.add(atorVencedor4);

    }

    @Test
    @WithMockUser
    void listarTodos() throws Exception {

        when(atorVencedorService.litarTodos()).thenReturn(atorVencedorList);
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/vencedor")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    @WithMockUser
    void retonarAtorMaisJovem() throws Exception {

        when(atorVencedorService.maisJovem()).thenReturn(atorVencedor4);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/vencedor/mais-jovem")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    @WithMockUser
    void retonarAtrizMaisPremiada() throws Exception {

        when(atorVencedorService.encontreAtrizMaisPremiada()).thenReturn(atorVencedor2.getAtor());
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/vencedor/mais-premiada")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser
    void salvarNovoPremio() throws Exception {

        when(atorVencedorService.salvarNovoPremio(atorVencedor)).thenReturn(atorVencedor);
        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/vencedor")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(atorVencedor))
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
    @Test
    @WithMockUser
    void deletarPremio() throws Exception {

        AtorVencedor aV = new AtorVencedor();
        aV.setId(6L);
        aV.setAtor(new Ator("Fernanda",SexoEnum.M));
        aV.setOscar(new Oscar(2021,"Filme teste6",72));




        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/vencedor/6")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.equalTo("Premio Deletado!")));

        Mockito.verify(atorVencedorService).deletar(aV.getId());

    }

}
