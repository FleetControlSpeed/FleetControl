package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Empresas;
import br.com.fleetcontrol.fleetcontrol.Service.EmpresasService;
import br.com.fleetcontrol.fleetcontrol.repository.EmpresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/empresas")
public class EmpresasController {
    @Autowired
    private EmpresasService empresasService;
    @Autowired
    private EmpresasRepository empresasRepository;


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
            final Empresas empresas = this.empresasRepository.findById(id).orElse(null);
            return ResponseEntity.ok(empresas);

    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestParam("id") final Empresas empresas) {
        try {
            this.empresasRepository.save(empresas);
            return ResponseEntity.ok("Empresa cadastrado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Empresas empresasNovo) {
        try {
            empresasService.editar(id, empresasNovo);
            return ResponseEntity.ok("Evento atualizado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
    @PutMapping(value = "/desativar")
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        try {
            empresasService.deletar(id);
            return ResponseEntity.ok("Empresa deletada com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}
