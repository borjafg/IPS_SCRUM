package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.types.EstadoPaquete;

@Entity
@Table(name = "Paquetes")
public class Paquete implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@Column(name = "id_paquete")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAQUETES_SEQ")
	@SequenceGenerator(name = "PAQUETES_SEQ", sequenceName = "PAQUETES_SEQ", allocationSize = 1, initialValue = 1)
	private long id;

	private String destinatario = "";

	private int numCaja;

	@Enumerated(EnumType.STRING)
	private EstadoPaquete estado = EstadoPaquete.ABIERTO;

	@Column(name = "direccion")
	private String direccionCompleta = "";

	@OneToMany(mappedBy = "paquete")
	private Set<ProductoEnPaquete> productosPaquete = new HashSet<ProductoEnPaquete>();

	@ManyToOne
	@JoinColumn(name = "id_ordenTrabajo", referencedColumnName = "id_ordenTrabajo")
	private OrdenTrabajo ordenTrabajo;

	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_envio", referencedColumnName = "id_envio")
	private Envio envio;

	// ==============================================
	// Constructores
	// ==============================================

	protected Paquete() {

	}

	public Paquete(OrdenTrabajo ordenTrabajo) {
		Asociacion.NuevoPaquete_OrdenTrabajo.link(this, ordenTrabajo);
	}

	// ==============================================
	// Id del paquete
	// ==============================================

	public long getId() {
		return id;
	}

	// ==============================================
	// Destinatario del paquete
	// ==============================================

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	// ==============================================
	// Numero de caja del paquete
	// ==============================================

	public int getNumCaja() {
		return numCaja;
	}

	public void setNumCaja(int numCaja) {
		this.numCaja = numCaja;
	}

	// ==============================================
	// Direccion de envio del paquete
	// ==============================================

	public String getDireccionCompleta() {
		return direccionCompleta;
	}

	public void setDireccionCompleta(String direccionCompleta) {
		this.direccionCompleta = direccionCompleta;
	}

	// ==============================================
	// Estado del paquete
	// ==============================================

	public EstadoPaquete getEstado() {
		return estado;
	}

	public void setEstado(EstadoPaquete estado) {
		this.estado = estado;
	}

	// ==============================================
	// Productos que contiene el paquete
	// ==============================================

	public Set<ProductoEnPaquete> getProductosPaquete() {
		return new HashSet<ProductoEnPaquete>(productosPaquete);
	}

	Set<ProductoEnPaquete> _getProductosPaquete() {
		return productosPaquete;
	}

	// ==============================================
	// Orden de trabajo a la que está asociada
	// ==============================================

	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	}

	void _setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	// ==============================================
	// Pedido al que está asociado
	// ==============================================

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	// ==============================================
	// Envio al que pertenece el paquete
	// ==============================================

	public Envio getEnvio() {
		return envio;
	}

	public void setEnvio(Envio envio) {
		Asociacion.AsignarPaquete_Envio(this, envio);
	}

	void _setEnvio(Envio envio) {
		this.envio = envio;
	}

	// ==============================================
	// HashCode - Equals
	// ==============================================

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