package br.com.fleetcontrol.fleetcontrol.controller;

import br.com.fleetcontrol.fleetcontrol.entity.Modelo;
import br.com.fleetcontrol.fleetcontrol.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping(value = "/api/modelo")
public class ModeloController {
    @Autowired
    private ModeloService modeloservice;


    //put para cadastro de modelo no banco
    @PostMapping
    public ResponseEntity<?> CadastroModelo(@RequestParam("id") final Modelo modelo){
        try{
            this.modeloservice.salvar(modelo);
            return ResponseEntity.ok("Modelo salvo com sucesso");
        }catch(Exception e ){
            return ResponseEntity.badRequest().body("erro de inserção de modelo");

        }
    }

    //get para id de modelo
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") final Long id) {
        try {
            final Modelo modelo = modeloservice.buscarPorId(id);
            return ResponseEntity.ok(modelo);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    //get para listagem de todos os modelos
    @GetMapping(value = "/listar")
    public ResponseEntity<?> listar(){
        try{
            return ResponseEntity.ok(modeloservice.listar());

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }


    //put para edit de modelo
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Modelo modelonovo) {
        try {
            modeloservice.editar(id, modelonovo);
            return ResponseEntity.ok("Modelo atualizado com sucesso!");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }


    // desativa o modelo caso nao esteja sendo utilizado em nenhuma marca
    @PutMapping(value = "/desativar")
    public ResponseEntity<?> desativar(@RequestParam("id") final Long id){
        try {
            modeloservice.desativar(id);
            return ResponseEntity.ok("Modelo desativado com sucesso!");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}
