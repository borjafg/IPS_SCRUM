package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.types.EstanteriaProducto;

@Entity
@Table(name = "PosicionProductos")
public class PosicionProducto implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@Column(name = "id_posicion")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POSICION_PRODUCTOS_SEQ")
	@SequenceGenerator(name = "POSICION_PRODUCTOS_SEQ", sequenceName = "POSICION_PRODUCTOS_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	private int pasillo;

	@Enumerated(EnumType.STRING)
	private EstanteriaProducto estanteria;

	private int altura;

	private int posicionX;

	@OneToOne(mappedBy = "posicion")
	private Producto producto;

	public PosicionProducto() {

	}

	public long getId() {
		return id;
	}

	public int getPasillo() {
		return pasillo;
	}

	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}

	public EstanteriaProducto getEstanteriaPoducto() {
		return estanteria;
	}

	public void setEstanteriaPoducto(EstanteriaProducto estanteria) {
		this.estanteria = estanteria;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public Producto getProducto() {
		return producto;
	}

	void _setProducto(Producto producto) {
		this.producto = producto;
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

		PosicionProducto other = (PosicionProducto) obj;

		if (id != other.id)
			return false;

		return true;
	}
}
