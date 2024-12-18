package ar.edu.ies6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.ies6.model.Compra;
import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.ClienteService;
import ar.edu.ies6.service.CompraService;
import ar.edu.ies6.service.ProductoService;

public class CompraController {
	@Autowired
	private CompraService compraService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProductoService productoService;

	@GetMapping("/compras")
	public ModelAndView listarCompras() {
		ModelAndView modelAndView = new ModelAndView("listaCompras");
		modelAndView.addObject("listadoCompras", compraService.listarTodos().stream()
				.filter(compra -> compra.getEliminado() == null || !compra.getEliminado()).toList());
		modelAndView.addObject("band", false);
		return modelAndView;
	}

	@GetMapping("/compras/nueva")
	public ModelAndView nuevaCompraForm() {
		ModelAndView modelAndView = new ModelAndView("formCompra");
		modelAndView.addObject("compra", new Compra());
		modelAndView.addObject("clientes", clienteService.listarTodos());
		modelAndView.addObject("productos", productoService.listarTodos());
		return modelAndView;
	}

	@PostMapping("/compras/guardar")
	public ModelAndView guardarCompra(@ModelAttribute Compra compra, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("listaCompras");
		try {
			Producto producto = productoService.obtenerPorId(compra.getProducto().getIdProducto());
			if (producto == null) {
				throw new IllegalArgumentException("El producto seleccionado no existe.");
			}
			if (compra.getCantidad() > producto.getStock()) {
				throw new IllegalArgumentException("Stock insuficiente para la cantidad solicitada.");
			}
			compra.setTotal(compra.getCantidad() * producto.getPrecio());
			producto.setStock(producto.getStock() - compra.getCantidad());
			productoService.guardarProducto(producto);
			compra.setEstado(true);
			compraService.guardar(compra);
			redirectAttributes.addFlashAttribute("success", "Compra registrada exitosamente.");
			modelAndView.setViewName("redirect:/compras");
		} catch (IllegalArgumentException e) {
			modelAndView.setViewName("formCompra");
			modelAndView.addObject("compra", compra);
			modelAndView.addObject("clientes", clienteService.listarTodos());
			modelAndView.addObject("productos", productoService.listarTodos());
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping("/compras/editar/{idCompra}")
	    public ModelAndView editarCompra(@PathVariable String idCompra) {
	        ModelAndView modelAndView = new ModelAndView("formCompra");
	modelAndView.addObject("compra", compraService.obtenerPorId(idCompra));
	modelAndView.addObject("clientes", clienteService.listarTodos());
	  modelAndView.addObject("productos", productoService.listarTodos());
	        modelAndView.addObject("band", true);
	        return modelAndView;
	}
	 
	@GetMapping("/compras/eliminar/{idCompra}")
	public ModelAndView eliminarCompraLogicamente(@PathVariable String idCompra) {
		Compra compra = compraService.obtenerPorId(idCompra);
		if (compra != null) {
			compra.setEliminado(true);
			compraService.guardar(compra);
		}
		return new ModelAndView("redirect:/compras");
	}
}

