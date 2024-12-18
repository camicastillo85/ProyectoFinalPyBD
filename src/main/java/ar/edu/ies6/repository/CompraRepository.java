package ar.edu.ies6.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.ies6.model.Compra;

@Repository
public interface CompraRepository extends 

CrudRepository<Compra, String> {
    
	/*
	List<Compra> findByCliente_DniCliente(String clienteId);

	
    List<Compra> findByProducto_IdProducto(String productoId);

	List<Compra> findByEstado(Boolean estado);
   */

}



