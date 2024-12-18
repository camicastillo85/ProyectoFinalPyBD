package ar.edu.ies6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Compra;
import ar.edu.ies6.repository.CompraRepository;
import ar.edu.ies6.service.CompraService;

@Service
public class CompraServiceImpl implements CompraService {
    @Autowired
private CompraRepository compraRepository;

    @Override
public List<Compra> listarTodos() {
        return (List<Compra>) compraRepository.findAll();
    }

    @Override
public Compra guardar(Compra compra) {
        if (compra.getProducto() == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo en una compra.");
        }
        return compraRepository.save(compra);
    }

    @Override
public Compra obtenerPorId(String idCompra) {
        return compraRepository.findById(idCompra).orElse(null);
    }

    @Override
    public void eliminar(String idCompra) {
        compraRepository.deleteById(idCompra);
    }

}
