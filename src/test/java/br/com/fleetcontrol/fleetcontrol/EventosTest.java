package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EventosController;
import br.com.fleetcontrol.fleetcontrol.dto.EventoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.*;

import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import br.com.fleetcontrol.fleetcontrol.repository.EventosRepository;
import br.com.fleetcontrol.fleetcontrol.service.EventosService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;


public class EventosTest {

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
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListarSucesso() {
        List<Eventos> eventosList = new ArrayList<>();
        eventosList.add(new Eventos());
        when(service.listartudo()).thenReturn(eventosList);
        ResponseEntity<?> response = controller.lista();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Eventos> eventosNaResposta = (List<Eventos>) response.getBody();
        assertNotNull(eventosNaResposta);
        assertFalse(eventosNaResposta.isEmpty());
    }
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Usuario usuario = new Usuario("junin@hotmail.com","Junin","123456", Cargo.MOTORISTA,"Junin","Almeida","12345678900","45999559955","24/12/2001", "Rua do diabo", new ArrayList<>());
        usuario.setEmail("junin@hotmail.com");
        usuario.setUsuario("Junin");
        usuario.setSenha("123456");
        usuario.setCargo(Cargo.MOTORISTA);
        usuario.setPrimeiroNome("Junin");
        usuario.setSobrenome("Almeida");
        usuario.setCpf("12345678900");
        usuario.setTelefone("45999559955");
        usuario.setDataNascimento("24/12/2001");
        usuario.setEndereco("Rua do diabo");

        Modelo modelo = new Modelo("Onix", Marca.CHEVROLET);
        modelo.setNome("Onix");
        modelo.setMarca(Marca.CHEVROLET);

        Veiculo veiculo = new Veiculo();
        veiculo.setModelo(modelo);
        veiculo.setPlaca("ANUI-5578");
        veiculo.setAno(2008);
        veiculo.setCor(Cor.PRATA);
        veiculo.setKm(200300L);
        veiculo.setTipo(Tipo.CARRO);

        Empresas empresas = new Empresas("Empresa", "85857485", "Rua Empresaria");
        empresas.setNome("Empresa");
        empresas.setCep("85857485");
        empresas.setEndereco("Rua Empresaria");

        Empresas empresaChegada = new Empresas("Empresa Chegada", "85857488", "Rua Empresaria 2");
        empresaChegada.setNome("Empresa Chegada");
        empresaChegada.setCep("85857488");
        empresaChegada.setEndereco("Rua Empresaria 2");

        Eventos eventos = new Eventos();
        LocalDateTime dataEvento = LocalDateTime.now();
        eventos.setUsuario(usuario);
        eventos.setVeiculo(veiculo);
        eventos.setDataEvento(dataEvento);
        eventos.setLocalPartida(empresas);
        eventos.setLocalDestino(empresaChegada);
        eventos.setObservacao("Sem firula");
        eventos.setRetorno(dataEvento);

        when(repository.findById(id))
                .thenReturn(Optional.of(eventos));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/eventos/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.usuario").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.email").value("junin@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.usuario").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.senha").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.primeiroNome").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.sobrenome").value("Almeida"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.cpf").value("12345678900"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.telefone").value("45999559955"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.dataNascimento").value("24/12/2001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.endereco").value("Rua do diabo"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo.modelo.nome").value("Onix"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo.placa").value("ANUI-5578"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo.ano").value("2008"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.veiculo.km").value(200300L))

                .andExpect(MockMvcResultMatchers.jsonPath("$.localPartida.nome").value("Empresa"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localPartida.cep").value("85857485"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localPartida.endereco").value("Rua Empresaria"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.localDestino.nome").value("Empresa Chegada"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDestino.cep").value("85857488"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localDestino.endereco").value("Rua Empresaria 2"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.observacao").value("Sem firula"));
    }

    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(repository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/eventos/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testListaAtivo() {
        boolean ativo = true;
        List<Eventos> eventosAtivos = new ArrayList<>();
        when(repository.findByAtivo(ativo)).thenReturn(eventosAtivos);
        ResponseEntity<List<EventoDTO>> response = controller.listarPorAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(eventosAtivos.size(), response.getBody().size());
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
