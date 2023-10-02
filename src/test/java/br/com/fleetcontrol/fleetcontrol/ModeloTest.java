package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.ModeloController;
import br.com.fleetcontrol.fleetcontrol.dto.ModeloDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import br.com.fleetcontrol.fleetcontrol.repository.ModeloRepository;
import br.com.fleetcontrol.fleetcontrol.service.ModeloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ModeloTest {
    private MockMvc mockMvc;
    @InjectMocks
    private ModeloController modeloController;
    @MockBean
    private ModeloService modeloService;
    @MockBean
    private ModeloRepository modeloRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(modeloController).build();
    }
   @Test
    void testListaIdSucesso() throws Exception {
       Long id = 1L;
       Modelo modelo = new Modelo("Onix", Marca.CHEVROLET);
       modelo.setNome("Onix");
       modelo.setMarca(Marca.CHEVROLET);
       when(modeloRepository.findById(id))
               .thenReturn(Optional.of(modelo));
       mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/modelo/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Onix"));
    }
    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(modeloRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/modelo/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testLista() throws Exception {
        when(modeloService.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/modelo/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    void testListaAtivo() {
        boolean ativo = true;
        List<Modelo> modelosAtivos = new ArrayList<>();
        when(modeloRepository.findByAtivo(ativo)).thenReturn(modelosAtivos);
        ResponseEntity<List<ModeloDTO>> response = modeloController.listarPorAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(modelosAtivos.size(), response.getBody().size());
    }
    @Test
    void testCadastrarSuccess() throws Exception {
        ModeloDTO modeloDTO = new ModeloDTO();
        when(modeloService.cadastrar(any(Modelo.class)))
                .thenReturn(new Modelo("Onix", Marca.CHEVROLET));

        mockMvc.perform(post("/api/modelo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(modeloDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        ModeloDTO modeloDTO = new ModeloDTO();
        when(modeloService.cadastrar(any(Modelo.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/modelo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(modeloDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        ModeloDTO modeloDTO = new ModeloDTO();
        when(modeloService.cadastrar(any(Modelo.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/modelo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(modeloDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    @Test
    void testAtualizarComSucesso() {

        Long id = 1L;

        when(modeloService.atualizar(eq(id), any())).thenReturn(new Modelo());
        ModeloDTO modeloDTO = new ModeloDTO();
        ResponseEntity<?> response = modeloController.atualizar(id, modeloDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        ModeloDTO modeloDTO = new ModeloDTO("Onix", Marca.CHEVROLET);

        when(modeloService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = modeloController.atualizar(id, modeloDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }
    @Test
    void testDesativarSucesso() throws Exception {
        Long id = 1L;
        doNothing().when(modeloService).desativar(id);
        mockMvc.perform(put("/api/modelo/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Modelos desativada com sucesso!"));
    }
    @Test
    void testDesativarFalha() throws Exception {
        Long id = 1L;
        doThrow(new RuntimeException("Erro ao desativar a empresa")).when(modeloService).desativar(id);
        mockMvc.perform(put("/api/modelo/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Erro ao desativar a empresa"));
    }
    @Test
    void testDeletarSucesso() throws Exception {
        long id = 1L;
        doNothing().when(modeloService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/modelo/deletar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Registro deletado com sucesso!"));
    }
    @Test
    void testDeletarFalha() throws Exception {
        long id = 1L;
        doThrow(new RuntimeException("Erro de deleção")).when(modeloService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/modelo/deletar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Error: Erro de deleção"));
    }
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
