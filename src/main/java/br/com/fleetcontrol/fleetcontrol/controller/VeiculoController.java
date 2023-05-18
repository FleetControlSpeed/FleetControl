package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.Entity.Veiculo;
import br.com.fleetcontrol.fleetcontrol.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fleetcontrol.fleetcontrol.service.VeiculoService;

@RestController
@RequestMapping(value ="/api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        try {
            Veiculo placa = this.veiculoService.findById(id);
            return ResponseEntity.ok().body(placa);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error veiculo não encontrado");
        }

    }
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Veiculo veiculo){
        try {
            this.veiculoService.create(veiculo);
            return ResponseEntity.ok("Usuario Cadastrado com Sucesso");

        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
     
    }
    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody Veiculo veiculo) {
        try {
            this.veiculoService.update(veiculo);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao Atualizar");

        }


    }


}
