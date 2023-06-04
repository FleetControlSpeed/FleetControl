package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;

@RestController
@RequestMapping(value = "api/veiculos")
public class VeiculoController {
     private VeiculoService veiculoService;
    @Autowired
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id){
        try{
            this.veiculoService.findById(id);
            return ResponseEntity.ok().body(this.veiculoService.findById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Id não encontrado!");
        }
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        try{
            return ResponseEntity.ok(this.veiculoService.listaCompleta());
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Lista de veiculos indisponivel!" + e.getMessage());
        }
    }

    @GetMapping("/lista/ativos")
    public ResponseEntity<?> listaAtivos() {
        try {
            return  ResponseEntity.ok(this.veiculoService.listaVeiculosAtivos());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Nenhum veiculo ativo no momento!");
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated final Veiculo veiculo){
        try {
            this.veiculoService.cadastrar(veiculo);
            return ResponseEntity.ok().body("Veiculo Cadastrado com Sucesso!");
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Veiculo já cadastrado!");
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id,
                                    @Validated @RequestBody final Veiculo veiculo){
        Veiculo veiculoBanco = veiculoService.findById(id);
        veiculoBanco.setAno(veiculo.getAno());
        veiculoBanco.setPlaca(veiculo.getPlaca());
        veiculoBanco.setModelo(veiculo.getModelo());
        veiculoBanco.setCor(veiculo.getCor());
        veiculoBanco.setTipo(veiculo.getTipo());

        try {
            this.veiculoService.atualizar(veiculoBanco);
            return ResponseEntity.ok("Veiculo alterado com sucesso!");
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Erro ao alterar veiculo!");
        }
    }

    @PutMapping("/desativar/{idVeiculo}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idVeiculo
    ){
        try{
            this.veiculoService.desativar(idVeiculo);
            return ResponseEntity.ok().body("Veiculo desativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Erro ao desativar!");
        }
    }

    @PutMapping("/ativar/{idVeiculo}")
    public ResponseEntity<?> ativar(
            @PathVariable Long idVeiculo
    ){
        try{
            this.veiculoService.ativar(idVeiculo);
            return ResponseEntity.ok().body("Veiculo ativado com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Nenhum veiculo encontrado!");
        }
    }
}

