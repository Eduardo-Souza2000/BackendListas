package app.service;
import app.dto.CarroDTO;
import app.entity.Carro;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
