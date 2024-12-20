 package ar.edu.ies6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.repository.ClienteRepository;
import ar.edu.ies6.service.ClienteService;

@Service
@Qualifier("servicioClienteBD")
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
				//nos dice q todos los docentes igresados se guardaran como activos por defecto
				cliente.setEliminado(true);
				
				clienteRepository.save(cliente);
	}

	@Override
	public void eliminarCliente(String dniCliente) {
		// TODO Auto-generated method stub
		Optional<Cliente> clienteEncontrado = clienteRepository.findById(dniCliente);
		clienteEncontrado.get().setEliminado(false);
		clienteRepository.save(clienteEncontrado.get());
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente consultarCliente(String dniCliente) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(dniCliente).get();
	}

	@Override
	public List<Cliente> listarTodosClientes() {
		// TODO Auto-generated method stub
		return (List <Cliente>) clienteRepository.findAll();
	}

	@Override
	public List<Cliente> listarTodosClientesActivos() {
		// TODO Auto-generated method stub
		return (List <Cliente>) clienteRepository.findByEliminado(true);  //muestra todo los que estan activos
	}

	
}