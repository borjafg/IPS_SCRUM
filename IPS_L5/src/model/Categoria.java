package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categoria implements Serializable {

	
	private static final long serialVersionUID = -96965276980991617L;

	@Id
	@Column(name = "id_categoria")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIAS_SEQ")
	@SequenceGenerator(name = "CATEGORIAS_SEQ", sequenceName = "CATEGORIAS_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	private String nombre;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_categoria_padre", referencedColumnName = "id_categoria", nullable = true)
	private Categoria categoriaPadre;

	@OneToMany(mappedBy = "categoriaPadre")
	private Set<Categoria> subcategorias = new HashSet<Categoria>();

	@OneToMany(mappedBy = "categoria")
	private Set<Producto> productos = new HashSet<Producto>();
	
	/**
	 * Para una categoria sin categoria padre; es decir, usar para una categoria
	 * de nivel superior.
	 */
	public Categoria() {

	}

	/**
	 * Para una categoria que tiene una categoria padre
	 * 
	 * @param categoriaPadre
	 *            categoria superior a la que se quiere crear
	 * 
	 */
	public Categoria(Categoria categoriaPadre) {
		Asociacion.NuevaCategoria.link(this, categoriaPadre);
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
	
	public Set<Categoria> getSubCategorias() {
		return new HashSet<Categoria>(subcategorias);
	}
	
	public Set<Categoria> _getSubCategorias() {
		return subcategorias;
	}
	
	public Categoria getCategoriaPadre() {
		return categoriaPadre;
	}
	
	void _setCategoriaPadre(Categoria categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}
	
	public Set<Producto> getProductos(){
		return new HashSet<Producto>(productos);
	}
	
	Set<Producto> _getProductos() {
		return productos;
	}
	
	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + "]";
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
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
}