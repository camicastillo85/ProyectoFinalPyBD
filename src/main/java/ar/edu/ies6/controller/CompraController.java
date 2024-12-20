package ar.edu.ies6.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.model.Cliente;
import ar.edu.ies6.model.Compra;
import ar.edu.ies6.model.Producto;
import ar.edu.ies6.service.ClienteService;
import ar.edu.ies6.service.CompraService;
import ar.edu.ies6.service.ProductoService;



@Controller
public class CompraController {
	@Autowired
	Compra unaCompra;

    @Autowired
    @Qualifier ("servicioCompraBD")
    CompraService compraService;
    
    @Autowired
    ProductoService productoService;
    
    @Autowired
    ClienteService clienteService;

	@GetMapping("/compra")
	public ModelAndView realizarCompra() {
		ModelAndView transportador = new ModelAndView("compra");
		transportador.addObject("compra",unaCompra); 
		 transportador.addObject("band",true);
		return transportador;
	}

	@GetMapping ("listadoCompra") 
	public ModelAndView getAllCompra () {
		//codigo
		//tranporte  hacia la Vista
		 ModelAndView transportador = new ModelAndView ("listadoCompra");
		transportador.addObject("listado",compraService.listarTodasComprasActivas()); 
		      
		return transportador;     
	}
	
	@PostMapping("/guardarCompra")
    public ModelAndView guardarCompra(Compra compra) {
        ModelAndView modelAndView = new ModelAndView("cliente");
        try {
            compraService.guardarCompra(compra);
            modelAndView.addObject("compra", compra);
            modelAndView.addObject("producto", compra.getProducto());
        } catch (RuntimeException e) {
            modelAndView.setViewName("compra");
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }
	
	@GetMapping("/recibo/{idCompra}")
	public ModelAndView mostrarRecibo(@PathVariable String idCompra) {
	    ModelAndView modelView = new ModelAndView("cliente");
	    try {
	        // Consulta la compra por el ID
	        Compra compra = compraService.consultarCompraIdCompra(idCompra);
	        
	        // Verifica que la compra y el producto existan
	        if (compra != null && compra.getProducto() != null && compra.getCliente() != null) {
	            modelView.addObject("compra", compra);
	            modelView.addObject("producto", compra.getProducto()); // Datos del producto
	            modelView.addObject("cliente", compra.getCliente());   // Datos del cliente
	        } else {
	            modelView.addObject("error", "No se encontró la compra o los datos asociados para el ID: " + idCompra);
	        }
	    } catch (Exception e) {
	        modelView.addObject("error", "Error al procesar la solicitud: " + e.getMessage());
	    }
	    return modelView;
	}
	
	
	@GetMapping("/compra/{idProducto}")
    public ModelAndView mostrarCompra(@PathVariable String idProducto) {
        ModelAndView modelView = new ModelAndView("compra");

        try {
            // Obtén el producto correspondiente al ID
            Producto producto = productoService.consultarProducto(idProducto);

            if (producto != null) {
                modelView.addObject("producto", producto); // Producto relacionado
                modelView.addObject("clientes", clienteService.listarTodosClientesActivos()); // Lista de clientes

            } else {
                modelView.addObject("error", "Producto no encontrado.");
            }
        } catch (RuntimeException e) {
            modelView.addObject("error", e.getMessage());
        }

        return modelView;
    }
	
	
	@PostMapping("/recibo")
	 public ModelAndView procesarCompra(
			@RequestParam String idProducto,
			@RequestParam String dniCliente,
			@RequestParam Integer cantidad,
			@RequestParam String metodoPago,
			@RequestParam String direccion) {
		
		System.out.println("Datos recibidos:");
	    System.out.println("Producto ID: " + idProducto);
	    System.out.println("Cantidad: " + cantidad);
	    System.out.println("Método de Pago: " + metodoPago);
	    System.out.println("Cliente ID: " + dniCliente);
	    System.out.println("Dirección: " + direccion);
	     
	     ModelAndView modelView = new ModelAndView("cliente");
	   
	     
	     try {
	         // Busca el producto y el cliente
	         Producto producto = productoService.consultarProducto(idProducto);
	         Cliente cliente = clienteService.consultarCliente(dniCliente);
	         
	        
	         // Lógica de la compra (puedes guardarla en la base de datos si es necesario)
	         Compra compra = new Compra();
	         compra.setProducto(producto);
	         compra.setCliente(cliente);
	         compra.setCantidad(cantidad);
	         compra.setMetodoPago(metodoPago);
	         compra.setTotal(producto.getPrecio() * cantidad);
	         compra.setFecha(LocalDate.now()); // Asignar la fecha actual
	         // Guarda la compra en la base de datos
	         compraService.guardarCompra(compra);

	         // Pasa los datos a la vista de recibo
	         modelView.addObject("producto", producto);
	         modelView.addObject("cliente", cliente);
	         modelView.addObject("compra", compra);

	     } catch (Exception e) {
	         modelView.addObject("error", "Error al procesar la compra: " + e.getMessage());
	     }

	     return modelView;
	 }
	
	@GetMapping("/index")
    public ModelAndView getViewIndex() {
        return new ModelAndView("index");
    }
	
}

