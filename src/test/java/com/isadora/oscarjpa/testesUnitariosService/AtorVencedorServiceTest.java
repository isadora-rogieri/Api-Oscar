package com.isadora.oscarjpa.testesUnitariosService;

import com.isadora.oscarjpa.model.Ator;
import com.isadora.oscarjpa.model.AtorVencedor;
import com.isadora.oscarjpa.model.Oscar;
import com.isadora.oscarjpa.model.SexoEnum;
import com.isadora.oscarjpa.repository.AtorRepository;
import com.isadora.oscarjpa.repository.AtorVencedorReopository;
import com.isadora.oscarjpa.service.AtorVencedorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtorVencedorServiceTest {

    @InjectMocks
    private AtorVencedorService atorVencedorService;

    @Mock
    AtorVencedorReopository atorVencedorReopository;

    @Mock
    AtorRepository atorRepository;

    private AtorVencedor atorVencedor;
    private AtorVencedor atorVencedor2;

    private List<AtorVencedor> atorVencedorList;

    @BeforeEach
    public void inicializar(){

        Ator ator = new Ator("",SexoEnum.M);
        Oscar oscar = new Oscar(2020,"Filme teste",40);

        Ator ator2 = new Ator("Juliana",SexoEnum.F);
        Oscar oscar2 = new Oscar(2021,"Filme teste2",30);

        atorVencedor = new AtorVencedor(oscar,ator);
        atorVencedor.setId(Long.valueOf(1));

        atorVencedor2 = new AtorVencedor(oscar2,ator2);
        atorVencedor2.setId(Long.valueOf(2));

        atorVencedorList = new ArrayList<>();
        atorVencedorList.add(atorVencedor);
        atorVencedorList.add(atorVencedor2);

    }


    @Test
    void salvarAtorVencedorTest(){


    when(atorVencedorReopository.save(atorVencedor)).thenReturn(atorVencedor);

    atorVencedor = atorVencedorService.salvarNovoPremio(atorVencedor);

    assertEquals(1, atorVencedor.getId());
    assertNotNull(atorVencedor.getAtor());
}

    @Test
    void deletarAtorVencedorTest(){

      when(atorVencedorReopository.findOneById(atorVencedor.getId())).thenReturn(atorVencedor);

        doNothing().when(atorVencedorReopository).delete(atorVencedor);
        atorVencedorService.deletar(atorVencedor.getId());

        verify(atorVencedorReopository, times(1)).delete(atorVencedor);
    }
    @Test
    void ListarTodosAtorVencedorTest(){

        when(atorVencedorReopository.findAll()).thenReturn(atorVencedorList);

       List<AtorVencedor> artistas = atorVencedorService.litarTodos();

        assertNotNull(atorVencedorList);
        assertEquals(2,atorVencedorList.size());
    }
    @Test
    void BuscarPorNomeAtorVencedorTest(){

        when(atorVencedorReopository.findAll()).thenReturn(atorVencedorList);


       List<AtorVencedor> a = atorVencedorService.buscaNomeAtor(atorVencedor2.getAtor().getNome());

        assertNotNull(a);
        Assertions.assertTrue(a.contains(atorVencedor2));
        Assertions.assertFalse(a.contains(atorVencedor));

    }
    @Test
    void atualizarAtorVencedorTest(){
        Ator ator3 = new Ator("Valeria",SexoEnum.F);
        Oscar oscar3 = new Oscar(2018,"Filme teste3",55);

        AtorVencedor atorVencedor3 = new AtorVencedor(oscar3,ator3);


        when(atorVencedorReopository.findOneById(atorVencedor.getId())).thenReturn(atorVencedor);
        atorVencedorService.alterar(Long.valueOf(1),atorVencedor3);

        assertEquals(ator3.getNome(), atorVencedor.getAtor().getNome());
        assertEquals(oscar3, atorVencedor.getOscar());
        assertEquals(55, atorVencedor.getOscar().getIdadeAtor());




    }

}
