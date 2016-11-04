package model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.types.EstadoOrdenTrabajo;

@Entity
@Table(name = "OrdenesTrabajo")
public class OrdenTrabajo implements Serializable {

	private static final long serialVersionUID = -45732110L;

	@Id
	@Column(name = "id_ordenTrabajo")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDENES_TRABAJO_SEQ")
	@SequenceGenerator(name = "ORDENES_TRABAJO_SEQ", sequenceName = "ORDENES_TRABAJO_SEQ", allocationSize = 1)
	private long id;

	@OneToMany(mappedBy = "ordenTrabajo")
	private Set<ProductoEnOrdenTrabajo> productosOrdenTrabajo = new HashSet<ProductoEnOrdenTrabajo>();

	@OneToMany(mappedBy = "ordenTrabajo")
	private Set<Incidencia> incidencias = new HashSet<Incidencia>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Enumerated(EnumType.STRING)
	private EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.RECOGIDA;

	@ManyToOne
	@JoinColumn(name = "id_almacenero_recoger", referencedColumnName = "id_almacenero")
	private Almacenero almaceneroRecoger;

	@ManyToOne
	@JoinColumn(name = "id_almacenero_empaquetar", referencedColumnName = "id_almacenero")
	private Almacenero almaceneroEmpaquetar;

	@OneToMany(mappedBy = "ordenTrabajo")
	private Set<Paquete> paquetes = new HashSet<Paquete>();

	// ==============================================
	// Constructores
	// ==============================================

	protected OrdenTrabajo() {

	}

	public OrdenTrabajo(Almacenero almacenero) {
		Asociacion.AlmaceneroRecogerOrdenTrabajo.link(this, almacenero);
	}

	// ==============================================
	// Id de la Orden de Trabajo
	// ==============================================

	public long getId() {
		return id;
	}

	// ==============================================
	// Productos de la Orden de Trabajo
	// ==============================================

	public Set<ProductoEnOrdenTrabajo> getProductosOrdenTrabajo() {
		return new HashSet<ProductoEnOrdenTrabajo>(productosOrdenTrabajo);
	}

	Set<ProductoEnOrdenTrabajo> _getProductosOrdenTrabajo() {
		return productosOrdenTrabajo;
	}

	// ==============================================
	// Incidencias de la Orden de Trabajo
	// ==============================================

	public Set<Incidencia> getIncidencias() {
		return new HashSet<Incidencia>(incidencias);
	}

	Set<Incidencia> _getIncidencias() {
		return incidencias;
	}

	// ==============================================
	// Fecha en la que se creo la Orden de Trabajo
	// ==============================================

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	// ==============================================
	// Estado de la Orden de Trabajo
	// ==============================================

	public EstadoOrdenTrabajo getEstadoOrdenTrabajo() {
		return estado;
	}

	public void setEstadoOrdenTrabajo(EstadoOrdenTrabajo estado) {
		this.estado = estado;
	}

	// ==============================================
	// Almacenero que recoge sus productos
	// ==============================================

	public Almacenero getAlmaceneroRecoger() {
		return almaceneroRecoger;
	}

	void _setAlmaceneroRecoger(Almacenero almaceneroRecoger) {
		this.almaceneroRecoger = almaceneroRecoger;
	}

	// ==============================================
	// Almacenero que empaqueta sus productos
	// ==============================================

	public Almacenero getAlmaceneroEmpaquetar() {
		return almaceneroEmpaquetar;
	}

	public void setAlmaceneroEmpaquetar(Almacenero almacenero) {
		Asociacion.AlmaceneroEmpaquetarOrdenTrabajo.link(this, almacenero);
	}

	void _setAlmaceneroEmpaquetar(Almacenero almaceneroEmpaquetar) {
		this.almaceneroEmpaquetar = almaceneroEmpaquetar;
	}

	// ==============================================
	// Lista de paquetes de la Orden de Trabajo
	// ==============================================

	public Set<Paquete> getPaquetes() {
		return new HashSet<Paquete>(paquetes);
	}
	
	// ==============================================================
	// Comprobar estado de la Orden de Trabajo (Recogida productos)
	// ==============================================================

	public boolean seTerminoRecoger() {
		if (estado == EstadoOrdenTrabajo.TERMINADA) {
			return false;
		}

		if (incidencias.size() > 0) {
			return false;
		}

		for (ProductoEnOrdenTrabajo producto : productosOrdenTrabajo) {
			// ¿Falta algún producto por recoger?
			if (producto.getUnidadesRecoger() > producto.getUnidadesRecogidas()) {
				return true;
			}
		}

		return false;
	}

	// ================================================================
	// Comprobar estado de la Orden de Trabajo (Empaquetado productos)
	// ================================================================

	public boolean seTerminoEmpaquetar() {
		if (estado == EstadoOrdenTrabajo.TERMINADA) {
			return false;
		}

		if (incidencias.size() > 0) {
			return false;
		}

		for (ProductoEnOrdenTrabajo producto : productosOrdenTrabajo) {
			// ¿Falta algún producto por recoger?
			if (producto.getUnidadesRecoger() > producto.getUnidadesRecogidas()) {
				return true;
			}
		}

		return false;
	}

	// ==============================
	// Equals y HashCode
	// ==============================

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

		OrdenTrabajo other = (OrdenTrabajo) obj;
		if (id != other.id)
			return false;

		return true;
	}
}