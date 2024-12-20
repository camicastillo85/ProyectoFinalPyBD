package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Producto;

public interface ProductoService {
	//metodos que resuelven una tarea
	
	public void guardarProducto (Producto producto);
	public void eliminarProducto(String idProducto);
	public void modificarProducto(Producto productoModificado);
	public Producto consultarProducto(String idProducto);
	public List<Producto>listarTodosProductos();
	public List<Producto>listarTodosProductosActivos();
}

