package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Cliente;

public interface ClienteService {
	
	//metodos que resuelven una tarea
		public void guardarCliente (Cliente cliente);
		public void eliminarCliente(String dni);
		public void modificarCliente(Cliente clienteModificado);
		public Cliente consultarCliente(String dni);
		public List<Cliente>listarTodosClientes();
		public List<Cliente>listarTodosClientesActivos();
}
