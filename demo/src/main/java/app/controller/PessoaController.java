package app.controller;

import java.util.List;

import app.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import app.dto.PessoaDTO;
import app.service.PessoaService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	private ResponseEntity<List<PessoaDTO>> listAll(){
		try {		
			List<PessoaDTO> lista = pessoaService.listAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	private ResponseEntity<PessoaDTO> save(@RequestBody PessoaDTO pessoaDTO){
		try {		
			PessoaDTO pessoaSalva = pessoaService.save(pessoaDTO);
			return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("erro")
	private ResponseEntity<List<PessoaDTO>> exemploErro(){
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<String> editar(@Validated @PathVariable("id") final Long id, @RequestBody final PessoaDTO pessoaDTO){

		try{
			return ResponseEntity.ok( this.pessoaService.editar(pessoaDTO, id));
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletar(@PathVariable("id") final Long id){
		try{
			String msg =  this.pessoaService.deletar(id);
			return ResponseEntity.ok(msg);
		}
		catch (Exception e)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
