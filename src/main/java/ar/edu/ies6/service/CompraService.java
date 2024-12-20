package ar.edu.ies6.service;

import java.util.List;

import ar.edu.ies6.model.Compra;

public interface CompraService {
	//metodos que resuelven una tarea
		public void guardarCompra (Compra compra);
		public void eliminarCompra(String dniCliente);
		public void modificarCompra(Compra compraModificada);
		public Compra consultarCompraDni(String dniCliente);
		public List<Compra> listarTodasCompras();
		public List<Compra>listarTodasComprasActivas();
		public Compra consultarCompraIdCompra(String idCompra);
}
