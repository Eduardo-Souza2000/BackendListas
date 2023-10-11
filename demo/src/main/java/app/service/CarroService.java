package app.service;
import app.dto.CarroDTO;
import app.dto.PessoaDTO;
import app.entity.Carro;
import app.entity.Pessoa;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> listAll(){
        List<Carro> lista = carroRepository.findAll();
        List<CarroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.toCarroDTO(lista.get(i)));

        return listaDTO;
    }

    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = this.toCarro(carroDTO);
        Carro carroSalvo = carroRepository.save(carro);
        return this.toCarroDTO(carroSalvo);
    }

    public String deletar(Long id) {
        if (id == null) {
            return "ID nulo. A exclusão não é possível.";
        }

        try {
            carroRepository.deleteById(id);
            return "Registro deletado com sucesso";
        } catch (EmptyResultDataAccessException e) {
            return "Registro não encontrado. A exclusão não é possível.";
        } catch (Exception e) {
            return "Erro ao excluir o registro: " + e.getMessage();
        }
    }

    public String editar(CarroDTO carroDTO, Long id) {
        if (id == null) {
            return "ID nulo. A edição não é possível.";
        }

        try {
            Carro carro = carroRepository.findById(id).orElse(null);

            if (carro == null) {
                return "Registro não encontrado. A edição não é possível.";
            }
            carro.setNome(carroDTO.getNome());
            carro.setAno(carroDTO.getAno());
            carroRepository.save(carro);
            return "Editado com sucesso";
        } catch (Exception e) {
            return "Erro ao editar o registro: " + e.getMessage();
        }
    }

    private CarroDTO toCarroDTO(Carro carro ) {
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setNome(carro.getNome());
        carroDTO.setAno(carro.getAno());
        return carroDTO;
    }

    private Carro toCarro(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setId(carroDTO.getId());
        carro.setNome(carroDTO.getNome());
        carro.setAno(carroDTO.getAno());
        return carro;
    }
}
