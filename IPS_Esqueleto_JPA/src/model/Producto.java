package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.types.CategoriasProducto;

@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_SEQ")
	@SequenceGenerator(name = "PRODUCTOS_SEQ", sequenceName = "PRODUCTOS_SEQ", allocationSize=1)
	private long id;

	private String nombre;
	private double precio;

	@Enumerated(EnumType.STRING)
	private CategoriasProducto categoria = CategoriasProducto.Ninguna;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_posicion")
	private PosicionProducto posicion;

	@OneToMany(mappedBy = "producto")
	private Set<ProductoEnPedido> listaProductosPedidos = new HashSet<ProductoEnPedido>();

	Producto() {
		
	}
	
	public Producto(PosicionProducto posicion) {
		Asociacion.Situar.link(this, posicion);
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public CategoriasProducto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriasProducto categoria) {
		this.categoria = categoria;
	}

	public PosicionProducto getPosicion() {
		return posicion;
	}

	void _setPosicion(PosicionProducto posicion) {
		this.posicion = posicion;
	}

	public Set<ProductoEnPedido> getListaProductosPedidos() {
		return new HashSet<ProductoEnPedido>(listaProductosPedidos);
	}

	Set<ProductoEnPedido> _getListaProductosPedidos() {
		return listaProductosPedidos;
	}
	
	

	@Override
	public String toString() {
		return  nombre + ", precio=" + precio + "  euros  , categoria=" + categoria ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (id ^ (id >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Producto other = (Producto) obj;
		if (id != other.id)
			return false;

		return true;
	}
}
