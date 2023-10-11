package app.controller;

import app.dto.LivroDTO;
import app.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
