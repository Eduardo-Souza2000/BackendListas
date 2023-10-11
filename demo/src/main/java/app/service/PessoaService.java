package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import app.dto.PessoaDTO;
import app.entity.Pessoa;
import app.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<PessoaDTO> listAll(){
		List<Pessoa> lista = pessoaRepository.findAll();
		List<PessoaDTO> listaDTO = new ArrayList<>();

		for(int i=0; i<lista.size(); i++) 
			listaDTO.add(this.toPessoaDTO(lista.get(i)));

		return listaDTO;
	}
	
	public PessoaDTO save(PessoaDTO pessoaDTO){
		Pessoa pessoa = this.toPessoa(pessoaDTO);

		Pessoa pessoasalva = pessoaRepository.save(pessoa);

		return this.toPessoaDTO(pessoasalva);
	}

	public String deletar(Long id) {
		if (id == null) {
			return "ID nulo. A exclusão não é possível.";
		}

		try {
			pessoaRepository.deleteById(id);
			return "Registro deletado com sucesso";
		} catch (EmptyResultDataAccessException e) {
			return "Registro não encontrado. A exclusão não é possível.";
		} catch (Exception e) {
			return "Erro ao excluir o registro: " + e.getMessage();
		}
	}

	public String editar(PessoaDTO pessoaDTO, Long id) {
		if (id == null) {
			return "ID nulo. A edição não é possível.";
		}

		try {
			Pessoa pessoa = pessoaRepository.findById(id).orElse(null);

			if (pessoa == null) {
				return "Registro não encontrado. A edição não é possível.";
			}
			pessoa.setNome(pessoaDTO.getNome());
			pessoa.setIdade(pessoaDTO.getIdade());
			pessoaRepository.save(pessoa);
			return "Editado com sucesso";
		} catch (Exception e) {
			return "Erro ao editar o registro: " + e.getMessage();
		}
	}

	private PessoaDTO toPessoaDTO(Pessoa pessoa) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(pessoa.getId());
		pessoaDTO.setNome(pessoa.getNome());
		pessoaDTO.setIdade(pessoa.getIdade());
		return pessoaDTO;
	}
	
	private Pessoa toPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(pessoaDTO.getId());
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setIdade(pessoaDTO.getIdade());
		return pessoa;
	}

}
