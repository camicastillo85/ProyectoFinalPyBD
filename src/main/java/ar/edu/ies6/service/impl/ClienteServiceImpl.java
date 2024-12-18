 package ar.edu.ies6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.repository.ClienteRepository;
import ar.edu.ies6.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerPorDni(String dniCliente) {
        return clienteRepository.findById(dniCliente).orElse(null);
    }

    @Override
    public void eliminar(String dniCliente) {
        clienteRepository.deleteById(dniCliente);
    }
}