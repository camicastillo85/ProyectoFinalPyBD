package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Producto;

public interface ProductoService {
	List<Producto> listarTodos();
    Producto guardar(Producto producto);
    Producto obtenerPorId(String idProducto);
    void eliminar(String idProducto);
	void guardarProducto(Producto producto);

}

