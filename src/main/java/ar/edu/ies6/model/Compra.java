package ar.edu.ies6.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Compra {
    @Id 
    private String idCompra;
    @Column
    private Boolean estado;
    @Column
    private String nombreProducto;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Producto producto;
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    @Column
    private LocalDate fecha;
    @Column
    private Double total;
    @Column(nullable = false)
    private Integer cantidad;
    @Column
    private String metodoPago;
    @Column
    private Boolean eliminado;

    
    public Compra() {
        this.eliminado = false;
    }

    public Compra(String idCompra, Boolean estado, String nombreProducto, Cliente cliente, Producto producto,
                  LocalDate fecha, Double total, Integer cantidad, String metodoPago, Boolean eliminado) {
        this.idCompra = idCompra;
        this.estado = estado;
        this.nombreProducto = nombreProducto;
        this.cliente = cliente;
        this.producto = producto;
        this.fecha = fecha;
        this.total = total;
        this.cantidad = cantidad;
        this.metodoPago = metodoPago;
        this.eliminado = eliminado;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

