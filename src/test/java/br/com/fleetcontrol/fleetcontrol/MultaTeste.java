package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.MultaController;
import br.com.fleetcontrol.fleetcontrol.entity.*;
import org.springframework.data.domain.Page;

import br.com.fleetcontrol.fleetcontrol.service.MultaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.data.domain.Pageable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;




import java.util.ArrayList;

import java.util.List;

public class MultaTeste {

    @InjectMocks
    private MultaController controller;

    @Mock
    private MultaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBuscarPorId() {
        Long idMulta = 1L;
        Multa multa = new Multa();
        when(service.buscarPorId(idMulta)).thenReturn(multa);

        ResponseEntity<?> response = controller.buscarPorId(idMulta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Multa multaNaResposta = (Multa) response.getBody();
        assertNotNull(multaNaResposta);
    }

    @Test
    void testListar() {
        List<Multa> multasList = new ArrayList<>();
        multasList.add(new Multa());
        when(service.listaCompleta(any())).thenReturn(Page.empty());
        ResponseEntity<?> response = controller.listar(Pageable.unpaged());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Multa> multasNaResposta = (List<Multa>) response.getBody();
        assertNotNull(multasNaResposta);
        assertFalse(multasNaResposta.isEmpty());
    }

    @Test
    void testCadastrar() {
        Multa multa = new Multa();
        when(service.salvar(multa)).thenReturn(multa);

        ResponseEntity<?> response = controller.cadastrar(multa);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Multa multaCriada = (Multa) response.getBody();
        assertNotNull(multaCriada);
    }


    @Test
    void testAtualizar() {
        Long idMulta = 1L;
        Multa multa = new Multa();
        when(service.buscarPorId(idMulta)).thenReturn(multa);
        doNothing().when(service).atualizar(idMulta, multa);

        ResponseEntity<?> response = controller.atualizar(idMulta, multa);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Multa alterada com sucesso!", response.getBody());
        verify(service, times(1)).atualizar(idMulta, multa);
    }

    @Test
    void testDesativar() {
        Long idMulta = 1L;
        Multa multa = new Multa();
        when(service.buscarPorId(idMulta)).thenReturn(multa);
        doNothing().when(service).desativar(idMulta);

        ResponseEntity<?> response = controller.desativar(idMulta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Multa desativada com sucesso!", response.getBody());
        verify(service, times(1)).desativar(idMulta);
    }

    @Test
    void testAtivar() {
        Long idMulta = 1L;
        Multa multa = new Multa();
        when(service.buscarPorId(idMulta)).thenReturn(multa);
        doNothing().when(service).ativar(idMulta);

        ResponseEntity<?> response = controller.ativar(idMulta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Multa ativada com sucesso!", response.getBody());
        verify(service, times(1)).ativar(idMulta);
    }
}
