package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EventosController;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.*;

import br.com.fleetcontrol.fleetcontrol.service.EventosService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;

import java.util.List;


public class EventosTeste {


    @InjectMocks
    private EventosController controller;

    @Mock
    private EventosService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListarSucesso() {
        List<Eventos> eventosList = new ArrayList<>();
        eventosList.add(new Eventos());
        when(service.listar()).thenReturn(eventosList);
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Eventos> eventosNaResposta = (List<Eventos>) response.getBody();
        assertNotNull(eventosNaResposta);
        assertFalse(eventosNaResposta.isEmpty());
    }

    @Test
    void testListarErro() {

        when(service.listar()).thenThrow(new RuntimeException("Não foi possível localizar nenhum evento cadastrado!"));
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Não foi possível localizar nenhum evento cadastrado!"));
    }
    @Test
    void testBuscarPorIdSucesso() {
        // Simular o comportamento do serviço ao encontrar um evento
        Long idEvento = 1L;
        Eventos evento = new Eventos(); // Crie um evento com ID 1
        when(service.buscarPorId(idEvento)).thenReturn(evento);

        // Chamar o método da controller
        ResponseEntity<?> response = controller.buscarPorId(idEvento);

        // Verificar se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar se o corpo da resposta contém o DTO do evento
        EventoDTO eventoDTO = (EventoDTO) response.getBody();
        assertNotNull(eventoDTO);
    }

    @Test
    void testBuscarPorIdErro() {
        Long idEvento = 0L;
        when(service.buscarPorId(idEvento)).thenThrow(new RuntimeException("Por favor, informe um valor válido!"));
        ResponseEntity<?> response = controller.buscarPorId(idEvento);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Por favor, informe um valor válido!"));
    }

    @Test
    void testListarPorAtivoSucesso() {
        List<Eventos> eventosAtivos = new ArrayList<>();
        eventosAtivos.add(new Eventos());
        when(service.listarPorAtivo()).thenReturn(eventosAtivos);
        ResponseEntity<?> response = controller.listarPorAtivo();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Eventos> eventosNaResposta = (List<Eventos>) response.getBody();
        assertNotNull(eventosNaResposta);
        assertFalse(eventosNaResposta.isEmpty());
    }

    @Test
    void testListarPorAtivoErro() {
        when(service.listarPorAtivo()).thenThrow(new RuntimeException("Não foi possível localizar nenhum evento ativo cadastrado!"));
        ResponseEntity<?> response = controller.listarPorAtivo();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Não foi possível localizar nenhum evento ativo cadastrado!"));
    }
}
