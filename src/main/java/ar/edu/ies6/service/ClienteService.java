package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Cliente;

public interface ClienteService {
	List<Cliente> listarTodos();
    Cliente guardar(Cliente cliente);
    Cliente obtenerPorDni(String dniCliente);
    void eliminar(String dniCliente);
}
