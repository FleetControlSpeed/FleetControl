package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.MultaController;
import br.com.fleetcontrol.fleetcontrol.dto.MultaDTO;
import br.com.fleetcontrol.fleetcontrol.entity.*;


import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import br.com.fleetcontrol.fleetcontrol.repository.MultaRepository;
import br.com.fleetcontrol.fleetcontrol.service.MultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testLista() throws Exception {
        when(service.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/multa/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
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

        Multa multa = new Multa(25.0,"Velocidade", LocalDate.of(2000,11,06), usuario);
        multa.setValor(25.0);
        multa.setTipoMulta("Velocidade");
        multa.setDataMulta(LocalDate.of(2000,11,06));
        multa.setUsuario(usuario);
        when(repository.findById(id))
                .thenReturn(Optional.of(multa));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/multa/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.email").value("junin@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.usuario").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.senha").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.primeiroNome").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.sobrenome").value("Almeida"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.cpf").value("12345678900"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.telefone").value("45999559955"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.dataNascimento").value("24/12/2001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario.endereco").value("Rua do diabo"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMulta").value("Velocidade"));

    }
    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(repository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/multa/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testCadastrarSuccess() throws Exception {
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

        MultaDTO multaDTO = new MultaDTO();
        when(service.cadastrar(any(Multa.class)))
                .thenReturn(new Multa(25.0,"Velocidade", LocalDate.of(2000,11,06), usuario));

        mockMvc.perform(post("/api/multa/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(multaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
