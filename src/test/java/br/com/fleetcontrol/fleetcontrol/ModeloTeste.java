package br.com.fleetcontrol.fleetcontrol;
import br.com.fleetcontrol.fleetcontrol.controller.ModeloController;
import br.com.fleetcontrol.fleetcontrol.dto.ModeloDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import br.com.fleetcontrol.fleetcontrol.service.ModeloService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
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



public class ModeloTeste {

    private Validator validador;
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ModeloController controller;

    @Mock
    private ModeloService service;
    @Mock
    private ModeloRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ValidatorFactory fabrica = Validation.buildDefaultValidatorFactory();
        validador = fabrica.getValidator();
    }
    @Test
    public void testNomeEhObrigatorio() {
        Modelo modelo = new Modelo();
        modelo.setMarca(Marca.TOYOTA);

        var violacoes = validador.validate(modelo);

        Assertions.assertFalse(violacoes.isEmpty());
        Assertions.assertTrue(
                violacoes.stream()
                        .anyMatch(violacao -> "nome".equals(violacao.getPropertyPath().toString()))
        );
    }
    @Test
    public void testMarcaEhObrigatoria() {
        Modelo modelo = new Modelo();
        modelo.setNome("MAZDA MIATA");
        var violacoes = validador.validate(modelo);

        Assertions.assertFalse(violacoes.isEmpty());
        Assertions.assertTrue(
                violacoes.stream()
                        .anyMatch(violacao -> "marca".equals(violacao.getPropertyPath().toString()))
        );
    }

    @Test
    public void testModeloValido() {
        Modelo modelo = new Modelo();
        modelo.setNome("Corolla");
        modelo.setMarca(Marca.TOYOTA);

        var violacoes = validador.validate(modelo);

        Assertions.assertTrue(violacoes.isEmpty());
    }
    ////////

    @Test
    void testListarSucesso() {
        List<Modelo> modelosList = new ArrayList<>();
        modelosList.add(new Modelo());
        when(service.listar()).thenReturn(modelosList);
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Modelo> modelosNaResposta = (List<Modelo>) response.getBody();
        assertNotNull(modelosNaResposta);
        assertFalse(modelosNaResposta.isEmpty());
    }

    @Test
    void testListarErro() {

        when(service.listar()).thenThrow(new RuntimeException("Não foi possível localizar nenhum modelo cadastrado!"));
        ResponseEntity<?> response = controller.listar();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Não foi possível localizar nenhum modelo cadastrado!"));
    }

    @Test
    void testBuscarPorIdSucesso() {
        Long idModelo = 1L;
        Modelo modelo = new Modelo();
        when(service.buscarPorId(idModelo)).thenReturn(modelo);

        ResponseEntity<?> response = controller.buscarPorId(idModelo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ModeloDTO modeloDTO = (ModeloDTO) response.getBody();
        assertNotNull(modeloDTO);
    }

    @Test
    void testBuscarPorIdErro() {
        Long idModelo = 0L;
        when(service.buscarPorId(idModelo)).thenThrow(new RuntimeException("Por favor, informe um valor válido!"));
        ResponseEntity<?> response = controller.buscarPorId(idModelo);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Por favor, informe um valor válido!"));
    }

    @Test
    void testListarPorAtivoSucesso() {
        List<Modelo> modelosAtivos = new ArrayList<>();
        modelosAtivos.add(new Modelo());
        when(service.listarPorAtivo()).thenReturn(modelosAtivos);
        ResponseEntity<?> response = controller.listarPorAtivo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Modelo> modelosNaResposta = (List<Modelo>) response.getBody();
        assertNotNull(modelosNaResposta);
        assertFalse(modelosNaResposta.isEmpty());
    }

    @Test
    void testListarPorAtivoErro() {
        when(service.listarPorAtivo()).thenThrow(new RuntimeException("Não foi possível localizar nenhum modelo ativo cadastrado!"));
        ResponseEntity<?> response = controller.listarPorAtivo();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String mensagemErro = (String) response.getBody();
        assertNotNull(mensagemErro);
        assertTrue(mensagemErro.contains("Não foi possível localizar nenhum modelo ativo cadastrado!"));
    }

    @Test
    void testCadastrarSucesso() {
        ModeloDTO modeloDTO = new ModeloDTO();
        doNothing().when(service).cadastrar(modeloDTO);
        ResponseEntity<?> response = controller.cadastrar(modeloDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        String mensagemSucesso = (String) response.getBody();
        assertNotNull(mensagemSucesso);
        assertTrue(mensagemSucesso.contains("modelo cadastrado com sucesso!"));
    }
}
