package ar.edu.ies6.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.ies6.model.Cliente;

public interface ClienteRepository extends 
	CrudRepository<Cliente, String> {

}


