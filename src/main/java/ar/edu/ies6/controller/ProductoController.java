package ar.edu.ies6.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Producto;
//import ar.edu.ies6.repository.ProductoRepository;
import ar.edu.ies6.service.ProductoService;


@Controller
public class ProductoController {

	//aqui lo primero que hace es instanciar por unica vez a producto.
			//esto es una inyeccion de dependencia
	@Autowired
	Producto unProducto; //unProducto es un objeto de la clase producto. Producto es una clase java

	@Qualifier ("servicioProductoBD")
	@Autowired
	ProductoService productoService;

	//recibe las peticiones de listaproducto

	@GetMapping ("/registroProducto")
	public ModelAndView getIndexWithProducto() {

	ModelAndView transportador = new ModelAndView ("registroProducto");
	transportador.addObject("producto", unProducto);
	transportador.addObject("band", false);

	return transportador;
	}


	//luego que se cargan los datos viene por post
	@PostMapping(value="/guardarProducto", consumes="multipart/form-data")
	public ModelAndView guardarProducto (Producto producto, @RequestParam ("file") MultipartFile [] imagen) {
	ModelAndView transportador = new ModelAndView("listaProducto");

	try{
		byte [] contenido=imagen [0].getBytes();
		String base64= Base64.getEncoder().encodeToString(contenido);
		producto.setImagen(base64);
		productoService.guardarProducto (producto);
	} catch
	(Exception e) {
	transportador.addObject("Error", e.getMessage()); 	
	}
	transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());
	return transportador;

	}


	//Eliminar producto
	@GetMapping("/eliminarProducto/{idProducto}") 
	public ModelAndView deleteProducto (@PathVariable String idProducto) {
	productoService.eliminarProducto(idProducto);

	//muestra el nuevo listado
	ModelAndView modelView = new ModelAndView("listaProducto");
	modelView.addObject("listadoProducto", productoService.listarTodosProductosActivos());
	return modelView;
	}

	 

	//Modificar
	@GetMapping("/modificarProducto/{idProducto}") 
	public ModelAndView modificarProducto (@PathVariable String idProducto) {
		//el parametro ModelAndView es la vista html
		
		ModelAndView modelView = new ModelAndView("registroProducto");
		
		modelView.addObject("producto", productoService.consultarProducto(idProducto) );
		modelView.addObject("band", true);
		return modelView;
	}


	@GetMapping("/listadoProducto") 
	public ModelAndView getAllProducto () {

	ModelAndView transportador = new ModelAndView("listaProducto");
	transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());

	return transportador;
	}


	@GetMapping("/producto")
	public ModelAndView mostrarClienteConProductos() {
	    ModelAndView transportador = new ModelAndView("producto");
	    // Pasar la lista de productos al modelo
	    transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());
	    return transportador;
	}



	@GetMapping("/producto-compra/{idProducto}")
	public ModelAndView mostrarCompra(@PathVariable String idProducto) {
	    ModelAndView modelView = new ModelAndView("compra");
	    try {
	        Producto producto = productoService.consultarProducto(idProducto); // Consulta el producto basado en el ID

	        if (producto != null) {
	            modelView.addObject("producto", producto); // Enviar el producto al modelo
	        } else {
	            modelView.addObject("error", "Producto no encontrado para el ID: " + idProducto);
	        }
	    } catch (RuntimeException e) {
	        modelView.addObject("error", "Error al obtener el producto: " + e.getMessage());
	    }
	    return modelView;
	}




}
