package br.com.fleetcontrol.fleetcontrol;

import br.com.fleetcontrol.fleetcontrol.controller.EmpresasController;
import br.com.fleetcontrol.fleetcontrol.controller.UsuarioController;
import br.com.fleetcontrol.fleetcontrol.dto.EmpresasDTO;
import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.entity.Usuario;
import br.com.fleetcontrol.fleetcontrol.entity.enums.Cargo;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import br.com.fleetcontrol.fleetcontrol.repository.UsuarioRepository;
import br.com.fleetcontrol.fleetcontrol.service.EmpresasService;
import br.com.fleetcontrol.fleetcontrol.service.UsuarioService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioTest {
    private MockMvc mockMvc;
    @InjectMocks
    private UsuarioController usuarioController;
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
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
        when(usuarioRepository.findById(id))
                .thenReturn(Optional.of(usuario));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/condutores/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("junin@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usuario").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senha").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargo").value(Cargo.MOTORISTA))
                .andExpect(MockMvcResultMatchers.jsonPath("$.primeironome").value("Junin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sobrenome").value("Almeida"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678900"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value("45999559955"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.datanascimento").value("24/12/2001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endereco").value("Rua do diabo"));
                }
    /*@Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(empresasRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/empresas/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testLista() throws Exception {
        when(empresasService.listar()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/empresas/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testListarPorAtivo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/empresas/listarPorAtivo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    @Test
    void testCadastrarSuccess() throws Exception {
        EmpresasDTO empresasDTO = new EmpresasDTO();
        when(empresasService.cadastrar(any(Empresas.class)))
                .thenReturn(new Empresas("Empresa", "85857485", "Rua Empresaria"));

        mockMvc.perform(post("/api/empresas/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(empresasDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        EmpresasDTO empresasDTO = new EmpresasDTO();
        when(empresasService.cadastrar(any(Empresas.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/empresas/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(empresasDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        EmpresasDTO empresasDTO = new EmpresasDTO();
        when(empresasService.cadastrar(any(Empresas.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/empresas/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(empresasDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    @Test
    void testAtualizarComSucesso() {

        Long id = 1L;

        when(empresasService.atualizar(eq(id), any())).thenReturn(new Empresas());
        EmpresasDTO empresasDTO = new EmpresasDTO();
        ResponseEntity<?> response = empresasController.atualizar(id, empresasDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        EmpresasDTO empresasDTO = new EmpresasDTO("Empresa", "85857485", "Rua Empresaria");

        when(empresasService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = empresasController.atualizar(id, empresasDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }
    @Test
    void testDesativarSucesso() throws Exception {
        Long id = 1L;
        doNothing().when(empresasService).desativar(id);
        mockMvc.perform(put("/api/empresas/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Empresa desativada com sucesso!"));
    }
    @Test
    void testDesativarFalha() throws Exception {
        Long id = 1L;
        doThrow(new RuntimeException("Erro ao desativar a empresa")).when(empresasService).desativar(id);
        mockMvc.perform(put("/api/empresas/desativar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Erro ao desativar a empresa"));
    }
    @Test
    void testDeletarSucesso() throws Exception {
        long id = 1L;
        doNothing().when(empresasService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/empresas/deletar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Registro deletado com sucesso!"));
    }
    @Test
    void testDeletarFalha() throws Exception {
        long id = 1L;
        doThrow(new RuntimeException("Erro de deleção")).when(empresasService).deletar(id);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/empresas/deletar")
                        .param("id", String.valueOf(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Error: Erro de deleção"));
    }*/
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
