package ar.edu.ies6.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.repository.ProductoRepository;
import ar.edu.ies6.service.ProductoService;

@Service
@Qualifier ("servicioProductoBD")
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Override
	public void guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		producto.setEliminado(true);
		productoRepository.save(producto);

	}

	@Override
	public void eliminarProducto(String idProducto) {
		// TODO Auto-generated method stub
		Optional<Producto> productoEncontrado = productoRepository.findById(idProducto);
		productoEncontrado.get().setEliminado(false);
		productoRepository.save(productoEncontrado.get());	
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto consultarProducto(String idProducto) {
		// TODO Auto-generated method stub
		return productoRepository.findById(idProducto).get();
	}

	@Override
	public List<Producto> listarTodosProductos() {
		// TODO Auto-generated method stub
		return (List <Producto>) productoRepository.findAll();
	}

	@Override
	public List<Producto> listarTodosProductosActivos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoRepository.findByEliminado(true); //muestra todo los q estan activos
	}
	
	
}

