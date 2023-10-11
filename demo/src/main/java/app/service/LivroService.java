package app.service;

import app.dto.CarroDTO;
import app.dto.LivroDTO;
import app.entity.Carro;
import app.entity.Livro;
import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listAll(){
        List<Livro> lista = livroRepository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toLivroDTO(lista.get(i)));

        return listaDTO;
    }

    public LivroDTO save(LivroDTO livroDTO){
        Livro livro = this.toLivro(livroDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return this.toLivroDTO(livroSalvo);
    }

    public String deletar(Long id) {
        if (id == null) {
            return "ID nulo. A exclusão não é possível.";
        }

        try {
            livroRepository.deleteById(id);
            return "Registro deletado com sucesso";
        } catch (EmptyResultDataAccessException e) {
            return "Registro não encontrado. A exclusão não é possível.";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    public String editar(LivroDTO livroDTO, Long id) {
        if (id == null) {
            return "ID nulo. A edição não é possível.";
        }
        try {
            Livro livro = livroRepository.findById(id).orElse(null);

            if (livro == null) {
                return "Registro não encontrado. A edição não é possível.";
            }
            livro.setTitulo(livroDTO.getTitulo());
            livro.setAutor(livroDTO.getAutor());
            livroRepository.save(livro);
            return "Editado com sucesso";
        } catch (Exception e) {
            return "Erro ao editar o registro: " + e.getMessage();
        }
    }


    private LivroDTO toLivroDTO(Livro livro ) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        return livroDTO;
    }

    private Livro toLivro(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        return livro;
    }
}
