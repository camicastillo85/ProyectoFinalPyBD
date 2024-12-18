package ar.edu.ies6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.ies6.model.Producto;
import ar.edu.ies6.repository.ProductoRepository;
import ar.edu.ies6.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	 @Autowired
	    private ProductoRepository productoRepository;

	    @Override
	    public List<Producto> listarTodos() {
	        return (List<Producto>) productoRepository.findAll();
	    }

	    @Override
	    public Producto guardar(Producto producto) {
	        return productoRepository.save(producto);
	    }

	    @Override
	    public Producto obtenerPorId(String idProducto) {
	        return productoRepository.findById(idProducto).orElse(null);
	    }

	    @Override
	    public void eliminar(String idProducto) {
	        productoRepository.deleteById(idProducto);
	    }

		@Override
		public void guardarProducto(Producto producto) {
			// TODO Auto-generated method stub
			
		}
}

