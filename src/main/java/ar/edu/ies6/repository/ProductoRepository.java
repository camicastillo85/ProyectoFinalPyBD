package ar.edu.ies6.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.ies6.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, String>{
	//se creo un nuevo metodo
			List <Producto> findByEliminado (Boolean eliminado);
}
