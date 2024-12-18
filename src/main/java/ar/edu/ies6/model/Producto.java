package ar.edu.ies6.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Producto {
	 @Id
	 private String idProducto;
	 @Column
	 private String nombre;
	 @Column
	 private String descripcion;
	 @Column
	 private Double precio;
	 @Column
	 private Integer stock;
	 private String imagenUrl;//A1
	 @Column
	    private Boolean eliminado;  // Nuevo atributo para eliminación lógica
 
	public Producto() {
		this.eliminado = false;  // Por defecto, una compra no está eliminada
	}

	public Producto(String idProducto, String nombre, String descripcion, Double precio, Integer stock,
			String imagenUrl, Boolean eliminado) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagenUrl = imagenUrl;
		this.eliminado = eliminado;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getNombre() {
		return nombre;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
