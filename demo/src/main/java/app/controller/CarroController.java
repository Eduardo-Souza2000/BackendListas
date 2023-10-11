package app.controller;
import app.dto.CarroDTO;
import app.dto.PessoaDTO;
import app.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    private ResponseEntity<List<CarroDTO>> listAll(){
        try {
            List<CarroDTO> lista = carroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<CarroDTO> save(@RequestBody CarroDTO carroDTO){
        try {
            CarroDTO carroSalvo = carroService.save(carroDTO);
            return new ResponseEntity<>(carroSalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<CarroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @PutMapping
    public ResponseEntity<String> editar(@Validated @RequestParam("id") final Long id, @RequestBody final CarroDTO carroDTO){

        try{
            return ResponseEntity.ok( this.carroService.editar(carroDTO, id));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@RequestParam("id") final Long id){
        try{
            String msg =  this.carroService.deletar(id);
            return ResponseEntity.ok(msg);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
