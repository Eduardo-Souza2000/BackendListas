package app.controller;

import app.dto.LivroDTO;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    private ResponseEntity<List<LivroDTO>> listAll(){
        try {
            List<LivroDTO> lista = livroService.listAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    private ResponseEntity<LivroDTO> save(@RequestBody LivroDTO livroDTO){
        try {
            LivroDTO livroSalvo = livroService.save(livroDTO);
            return new ResponseEntity<>(livroSalvo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<LivroDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @PutMapping
    public ResponseEntity<String> editar(@Validated @RequestParam("id") final Long id, @RequestBody final LivroDTO livroDTO){

        try{
            return ResponseEntity.ok( this.livroService.editar(livroDTO, id));
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") final Long id){
        try{
            String msg =  this.livroService.deletar(id);
            return ResponseEntity.ok(msg);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
