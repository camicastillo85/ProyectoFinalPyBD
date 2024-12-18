package ar.edu.ies6.controller;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.servlet.ModelAndView;

	import ar.edu.ies6.model.Producto;
	//import ar.edu.ies6.repository.ProductoRepository;
	import ar.edu.ies6.service.ProductoService;

	@Controller
	public class ProductoController {

		@Autowired
	    private ProductoService productoService;
	    
	    @GetMapping("/productos")
	    public ModelAndView listarproductos() {
	        ModelAndView modelAndView = new ModelAndView("listaProductos");
	        
	        
	        modelAndView.addObject("listadoProductos", productoService.listarTodos().stream()
	            .filter(producto -> producto.getEliminado() == null || !producto.getEliminado())
	            .toList());      
	        modelAndView.addObject("band", false); 
	        return modelAndView;
	    }

	    @GetMapping("/productos/nuevo")
	    public ModelAndView nuevoProductoForm() {
	        ModelAndView modelAndView = new ModelAndView("formProductos");
	        modelAndView.addObject("productos", new Producto());
	        return modelAndView;
	    }

	    @PostMapping("/productos/guardar")
	    public ModelAndView guardarProducto(@ModelAttribute Producto producto) {
	        productoService.guardar(producto);
	        ModelAndView modelAndView = new ModelAndView("listaProductos");
	        modelAndView.addObject("listadoProductos", productoService.listarTodos());
	        return modelAndView;
	    }
	    
	    @GetMapping("/productos/editar/{idProducto}")
	    public ModelAndView editarProducto(@PathVariable String idProducto) {
	        ModelAndView modelAndView = new ModelAndView("formProducto");
	        modelAndView.addObject("productos", productoService.obtenerPorId(idProducto));
	        modelAndView.addObject("band",true);
	        return modelAndView;
	    }
	    
	    @GetMapping("/productos/eliminar/{idProducto}")
	    public ModelAndView eliminarProductoLogicamente(@PathVariable String idProducto) {
			Producto producto = productoService.obtenerPorId(idProducto);
	        if (producto != null) {
	        	producto.setEliminado(true);
	        	productoService.guardar(producto);
	        }
	        return new ModelAndView("redirect:/producto");
	    }
	}

