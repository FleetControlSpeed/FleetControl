package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EmpresasController;
import br.com.fleetcontrol.fleetcontrol.controller.VeiculoController;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.dto.VeiculoDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cor;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Marca;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Tipo;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import br.com.fleetcontrol.fleetcontrol.repository.VeiculoRepository;
import br.com.fleetcontrol.fleetcontrol.service.EmpresasService;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;
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
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VeiculoTest{
    private MockMvc mockMvc;
    @InjectMocks
    private VeiculoController veiculoController;
    @MockBean
    private VeiculoService veiculoService;
    @MockBean
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(veiculoController).build();
    }
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
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
        when(veiculoRepository.findById(id))
                .thenReturn(Optional.of(veiculo));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/veiculo/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo.nome").value("Onix"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.placa").value("ANUI-5578"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value("2008"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.km").value(200300L));
    }
    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(veiculoRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/veiculo/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testLista() throws Exception {
        when(veiculoService.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/veiculo/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testListarPorAtivo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/veiculo/listarPorAtivo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    @Test
    void testCadastrarSuccess() throws Exception {
        Long id = 1L;
        Modelo modelo = new Modelo("Onix", Marca.CHEVROLET);
        modelo.setNome("Onix");
        modelo.setMarca(Marca.CHEVROLET);

        VeiculoDTO veiculoDTO = new VeiculoDTO();
        when(veiculoService.cadastrar(any(Veiculo.class)))
                .thenReturn(new Veiculo( modelo, "ANUI-5578", 2008, Cor.PRATA, 200300L, Tipo.CARRO));

        mockMvc.perform(post("/api/veiculo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(veiculoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        when(veiculoService.cadastrar(any(Veiculo.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/veiculo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(veiculoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        when(veiculoService.cadastrar(any(Veiculo.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/veiculo/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(veiculoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    @Test
    void testAtualizarComSucesso() {

        Long id = 1L;

        when(veiculoService.atualizar(eq(id), any())).thenReturn(new Veiculo());
        VeiculoDTO veiculoDTO = new VeiculoDTO();
        ResponseEntity<?> response = veiculoController.atualizar(id, veiculoDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        Modelo modelo = new Modelo("Onix", Marca.CHEVROLET);
        modelo.setNome("Onix");
        modelo.setMarca(Marca.CHEVROLET);

        VeiculoDTO veiculoDTO = new VeiculoDTO( modelo, "ANUI-5578", 2008, Cor.PRATA, 200300L, Tipo.CARRO);

        when(veiculoService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = veiculoController.atualizar(id, veiculoDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }
    @Test
    void testDesativarSucesso() throws Exception {
        Long id = 1L;
        doNothing().when(veiculoService).desativar(id);
        mockMvc.perform(put("/api/veiculo/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Evento desativado com sucesso!"));
    }
    @Test
    void testDesativarFalha() throws Exception {
        Long id = 1L;
        doThrow(new RuntimeException("Erro ao desativar a empresa")).when(veiculoService).desativar(id);
        mockMvc.perform(put("/api/veiculo/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Erro ao desativar a empresa"));
    }
    @Test
    void testDeletarSucesso() throws Exception {
        long id = 1L;
        doNothing().when(veiculoService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/veiculo/deletar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Registro deletado com sucesso!"));
    }
    @Test
    void testDeletarFalha() throws Exception {
        long id = 1L;
        doThrow(new RuntimeException("Erro de deleção")).when(veiculoService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/veiculo/deletar")
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
