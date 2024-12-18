package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Compra;

public interface CompraService {
	List<Compra> listarTodos();
    Compra guardar(Compra compra);
    Compra obtenerPorId(String idCompra);
    void eliminar(String idCompra);

}
