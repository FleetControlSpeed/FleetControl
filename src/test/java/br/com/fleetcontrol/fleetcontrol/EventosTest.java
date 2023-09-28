package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EventosController;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Eventos;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class EventosTest {/*
    private MockMvc mockMvc;
    @InjectMocks
    private EventosController eventosController;
    @MockBean
    private EventosService eventosService;
    @MockBean
    private EmpresasRepository empresasRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventosController).build();
    }
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Eventos eventos = new Eventos();
        eventos.setUsuario("Empresa");
        eventos.setVeiculo("Empresa");
        eventos.setDataEvento("Empresa");
        eventos.setLocalPartida("Empresa");
        eventos.setLocalDestino("Empresa");
        eventos.setObservacao("Empresa");
        eventos.setRetorno("Empresa");
        when(empresasRepository.findById(id))
                .thenReturn(Optional.of(eventos));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/eventos/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Empresa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value("85857485"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value("Rua Empresaria"));
    }



    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







*/

}
