package ar.edu.ies6.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.ies6.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, String> {
	//se creo un nuevo metodo
		List<Compra> findByEstado (Boolean estado);
		Optional<Compra> findByIdCompra(String idCompra);
}



