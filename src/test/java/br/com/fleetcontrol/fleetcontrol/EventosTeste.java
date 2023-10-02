package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EventosController;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.*;

import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;

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



public class EventosTeste {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private EventosController controller;

    @Mock
    private EventosService service;
    @Mock
    private EventosRepository repository;

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
        Long idEvento = 1L;
        Eventos evento = new Eventos();
        when(service.buscarPorId(idEvento)).thenReturn(evento);

        ResponseEntity<?> response = controller.buscarPorId(idEvento);
        assertEquals(HttpStatus.OK, response.getStatusCode());
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

    @Test
    void testCadastrarSucesso() {
        // Simular o cadastro bem-sucedido no service
        EventoDTO eventoDTO = new EventoDTO(); // Crie um DTO válido
        doNothing().when(service).cadastrar(eventoDTO);

        // Chamar o método da controller
        ResponseEntity<?> response = controller.cadastrar(eventoDTO);

        // Verificar se o status da resposta é OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar se a mensagem de sucesso está presente no corpo da resposta
        String mensagemSucesso = (String) response.getBody();
        assertNotNull(mensagemSucesso);
        assertTrue(mensagemSucesso.contains("Evento cadastrado com sucesso!"));
    }



    @Test
    public void testDesativarEventoComSucesso() {
        Long id = 1L;

        doNothing().when(service).desativar(id);

        ResponseEntity<?> response = controller.desativar(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eventos desativada com sucesso!", response.getBody());
        verify(service, times(1)).desativar(id);
    }

    @Test
    public void testDesativarEventoComErro() {
        Long id = 2L;

        doThrow(new RuntimeException("Evento informado já está desativado!")).when(service).desativar(id);

        ResponseEntity<?> response = controller.desativar(id);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Evento informado já está desativado!", response.getBody());
        verify(service, times(1)).desativar(id);
    }

    @Test
    public void testAtivarEventoComSucesso() {
        Long id = 1L;
        doNothing().when(service).ativar(id);
        ResponseEntity<?> response = controller.ativar(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Eventos ativada com sucesso!", response.getBody());
        verify(service, times(1)).ativar(id);
    }

    @Test
    public void testAtivarEventoComErro() {
        Long id = 2L;

        doThrow(new RuntimeException("Evento informado já está ativado!")).when(service).ativar(id);

        ResponseEntity<?> response = controller.ativar(id);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Evento informado já está ativado!", response.getBody());

        verify(service, times(1)).ativar(id);
    }

    @Test
    public void testDeletarRegistroComSucesso() {
        Long id = 1L;
        doNothing().when(service).desativar(id);
        ResponseEntity<?> response = controller.deletar(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registro deletado com sucesso!", response.getBody());
        verify(service, times(1)).desativar(id);
    }

    @Test
    public void testDeletarRegistroComErro() {
        Long id = 2L;
        doThrow(new RuntimeException("Erro ao deletar registro!")).when(service).desativar(id);
        ResponseEntity<?> response = controller.deletar(id);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Erro ao deletar registro!", response.getBody());
        verify(service, times(1)).desativar(id);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
