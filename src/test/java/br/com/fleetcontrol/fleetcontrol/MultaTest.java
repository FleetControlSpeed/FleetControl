package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.MultaController;
import br.com.fleetcontrol.fleetcontrol.dto.MultaDTO;
import br.com.fleetcontrol.fleetcontrol.entity.*;


import br.com.fleetcontrol.fleetcontrol.repository.MultaRepository;
import br.com.fleetcontrol.fleetcontrol.service.MultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.web.servlet.MockMvc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;




import java.util.ArrayList;

import java.util.List;

public class MultaTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private MultaController controller;

    @Mock
    private MultaService service;
    @Mock
    private MultaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListarSucesso() {
        List<Multa> multasList = new ArrayList<>();
        multasList.add(new Multa());
        when(service.listaCompleta()).thenReturn(multasList);
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Multa> multasNaResposta = (List<Multa>) response.getBody();
        assertNotNull(multasNaResposta);
        assertFalse(multasNaResposta.isEmpty());
    }

    @Test
    void testListarErro() {
        when(service.listaCompleta()).thenThrow(new RuntimeException("Multa não encontrada!"));
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Multa não encontrada!"));
    }

    @Test
    void testBuscarPorIdSucesso() {
        Long idMulta = 1L;
        Multa multa = new Multa();
        when(service.buscarPorId(idMulta)).thenReturn(multa);

        ResponseEntity<?> response = controller.buscarPorId(idMulta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MultaDTO multaDTO = (MultaDTO) response.getBody();
        assertNotNull(multaDTO);
    }

    @Test
    void testBuscarPorIdErro() {
        Long idMulta = 0L;
        when(service.buscarPorId(idMulta)).thenThrow(new RuntimeException("Multa não encontrada!"));
        ResponseEntity<?> response = controller.buscarPorId(idMulta);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Multa não encontrada!"));
    }
    @Test
    void testCadastrarSucesso() {
        MultaDTO multaDTO = new MultaDTO();
        doNothing().when(service).salvar(multaDTO);
        ResponseEntity<?> response = controller.cadastrar(multaDTO);

        verify(service, times(1)).salvar(multaDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
