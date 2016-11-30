package persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Almacenero;
import model.OrdenTrabajo;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class OrdenTrabajoFinder {

	/**
	 * Si la Orden de Trabajo pasada no estaba sincronizada con la base de
	 * datos, este método devolverá un nuevo objeto OrdenTrabajo que sí estará
	 * sincronizado con la base de datos
	 * 
	 * @param ordenTrabajo
	 *            orden de trabajo que hay que buscar
	 * 
	 * @return copia de la orden de trabajo (sincronizada con la base de datos),
	 *         o el mismo objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static OrdenTrabajo find(OrdenTrabajo ordenTrabajo) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(OrdenTrabajo.class, ordenTrabajo.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado la orden de trabajo con id = ");
			sb.append(ordenTrabajo.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar la orden de trabajo con id = ");
			sb.append(ordenTrabajo.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static long findNumProductosFaltaEmpaquetar(OrdenTrabajo ot) throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("OrdenTrabajo.findNumProductosFaltaEmpaquetar", Long.class)
					.setParameter("ordenTrabajo", ot).getSingleResult();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el numero de productos de la orden de trabajo con id = ");
			sb.append(ot.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static List<OrdenTrabajo> findOrdenesTrabajoRetomar(Almacenero almacenero) throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("OrdenTrabajo.findOrdenesTrabajoRetomar", OrdenTrabajo.class)
					.setParameter("almaceneroRecog", almacenero).setParameter("almaceneroEmpaq", almacenero)
					.getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar una lista de órdenes de trabajo a retomar");

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static List<Object[]> findNumOrdenTrabajoRecogidasDia(Almacenero almacenero) throws MyPersistenceException {
		try {
			return Jpa.getManager()
					.createNamedQuery("OrdenTrabajo.findNumOrdenTrabajoRecogidasDia_Almacenero", Object[].class)
					.setParameter("almacenero", almacenero).getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar las ordenes de trabajo recogidas por día");

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static Date findOrdenTrabajoTerminadaRecoger_MasAntigua() throws MyPersistenceException {
		try {
			List<Date> fechas = Jpa.getManager()
					.createNamedQuery("OrdenTrabajo.findOrdenTrabajoTerminadaRecoger_MasAntigua", Date.class)
					.setMaxResults(1).getResultList();

			if (fechas.isEmpty()) {
				return null;
			}

			return fechas.get(0);
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar las ordenes de trabajo recogidas por día");

			throw new MyPersistenceException(sb.toString(), e);
		}
	}
	
	
	public static List<Object[]> findNumOrdenTrabajoEmpaquetadasDia(Almacenero almacenero) throws MyPersistenceException {
		try {
			return Jpa.getManager()
					.createNamedQuery("OrdenTrabajo.findNumOrdenTrabajoEmpaquetadaDia_Almacenero", Object[].class)
					.setParameter("almacenero", almacenero).getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar las ordenes de trabajo empaquetadas por día");

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static Date findOrdenTrabajoTerminadaEmpaquetada_MasAntigua() throws MyPersistenceException {
		try {
			List<Date> fechas = Jpa.getManager()
					.createNamedQuery("OrdenTrabajo.findOrdenTrabajoEmpaquetada_MasAntigua", Date.class)
					.setMaxResults(1).getResultList();

			if (fechas.isEmpty()) {
				return null;
			}

			return fechas.get(0);
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar las ordenes de trabajo empaquetadas por día");

			throw new MyPersistenceException(sb.toString(), e);
		}
	}
	
	

}