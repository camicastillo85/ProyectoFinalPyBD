package ar.edu.ies6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Compra;
import ar.edu.ies6.repository.CompraRepository;
import ar.edu.ies6.service.CompraService;

@Service
@Qualifier("servicioCompraBD")
public class CompraServiceImpl implements CompraService {

	@Autowired
	CompraRepository compraRepository;
	
	@Override
	public void guardarCompra(Compra compra) {
		// TODO Auto-generated method stub
		compra.setEstado(true);
		compraRepository.save(compra);
	}

	@Override
	public void eliminarCompra(String dniCliente) {
		// TODO Auto-generated method stub
		Optional<Compra> compraEncontrado =compraRepository.findById(dniCliente);
		compraEncontrado.get().setEstado(false);
		if (compraEncontrado.isPresent()) {
            Compra compra = compraEncontrado.get();
            compra.setEstado(false); // Cambia el estado a falso
            compraRepository.save(compra);
        } else {
            throw new RuntimeException("Compra no encontrada para el DNI: " + dniCliente);
        }
	}

	@Override
	public void modificarCompra(Compra compraModificada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compra consultarCompraDni(String dniCliente) {
		// TODO Auto-generated method stub
		return compraRepository.findById(dniCliente)
        .orElseThrow(() -> new RuntimeException("Compra no encontrada para el DNI: " + dniCliente));

	}

	@Override
	public List<Compra> listarTodasCompras() {
		// TODO Auto-generated method stub
		return (List<Compra>) compraRepository.findAll();
	}

	@Override
	public List<Compra> listarTodasComprasActivas() {
		// TODO Auto-generated method stub
		return (List<Compra>) compraRepository.findByEstado(true);
	}

	@Override
	public Compra consultarCompraIdCompra(String idCompra) {
		// TODO Auto-generated method stub
		 return compraRepository.findByIdCompra(idCompra)
		         .orElseThrow(() -> new RuntimeException("Compra no encontrada para el ID: " + idCompra));
	}
   

}
