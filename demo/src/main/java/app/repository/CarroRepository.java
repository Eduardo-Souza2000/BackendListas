package app.repository;

import app.entity.Carro;
import app.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
