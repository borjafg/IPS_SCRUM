package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Paquetes")
public class Paquete implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@Column(name="id_paquete")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAQUETES_SEQ")
	@SequenceGenerator(name = "PAQUETES_SEQ", sequenceName = "PAQUETES_SEQ", allocationSize=1)
	private long id;

	private String destinatario;

	private int numCaja;
	
	@Column(name = "direccion")
	private String direccionCompleta;

	@OneToMany(mappedBy = "paquete")
	private Set<ProductoEnPaquete> productosPaquete = new HashSet<ProductoEnPaquete>();

	public Paquete() {

	}

	public long getId() {
		return id;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public int getNumCaja() {
		return numCaja;
	}
	
	public void setNumCaja(int numCaja) {
		this.numCaja = numCaja;
	}

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	public Set<ProductoEnPaquete> getProductosPaquete() {
		return new HashSet<ProductoEnPaquete>(productosPaquete);
	}

	Set<ProductoEnPaquete> _getProductosPaquete() {
		return productosPaquete;
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

		Paquete other = (Paquete) obj;
		if (id != other.id)
			return false;

		return true;
	}
}