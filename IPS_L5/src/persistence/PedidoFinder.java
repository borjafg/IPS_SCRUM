package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import model.OrdenTrabajo;
import model.Pedido;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class PedidoFinder {

	/**
	 * Si el pedido pasado no estaba sincronizado con la base de datos, este
	 * método devolverá un nuevo objeto Pedido que sí estará sincronizado con la
	 * base de datos
	 * 
	 * @param pedido
	 *            pedido que hay que buscar
	 * 
	 * @return copia del pedido (sincronizado con la base de datos), o el mismo
	 *         objeto si ya estaba sincronizado
	 * 
	 * @throws BusinessException
	 * 
	 */
	public static Pedido find(Pedido pedido) throws MyPersistenceException {
		try {
			return Jpa.getManager().find(Pedido.class, pedido.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("No se ha encontrado el pedido con id = ");
			sb.append(pedido.getId());

			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema al buscar el pedido con id = ");
			sb.append(pedido.getId());

			throw new MyPersistenceException(sb.toString());
		}
	}

	public static List<Pedido> findAll() throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Pedido.findAll", Pedido.class).getResultList();
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al buscar todos los pedidos", e);
		}
	}

	// ===============================================
	// Creación de órdenes de trabajo
	// ===============================================

	public static boolean cabeEntero(Pedido pedido, double pesoMaximo, double volumenMaximo)
			throws MyPersistenceException {

		try {
			List<Object[]> resultado = Jpa.getManager().createNamedQuery("Pedido.findPeso_Volumen", Object[].class)
					.setParameter("pedido", pedido).getResultList();

			if (resultado.isEmpty()) {
				throw new MyPersistenceException("No se ha encontrado el pedido en la base de datos");
			}

			// Primer parametro ----> Peso total del pedido
			// Segundo parametro ---> Volumen total del pedido

			return (((double) resultado.get(0)[0]) <= pesoMaximo) && (((double) resultado.get(0)[1]) <= volumenMaximo);
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al comprobar si un pedido cabe entero en una OT", e);
		}
	}

	public static List<Pedido> findPosibleRecoger() throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Pedido.findPosibleRecoger", Pedido.class).getResultList();
		}

		catch (PersistenceException e) {
			throw new MyPersistenceException("Ha ocurrido un error al buscar los pedidos que se pueden recoger", e);
		}
	}

	public static List<Object[]> findPosibleRecoger_NoPedido(Pedido p) throws MyPersistenceException {
		try{
		return Jpa.getManager().createNamedQuery("Pedido.findPosibleRecoger_NoPedido", Object[].class)
				.setParameter("pedido", p).getResultList();
		}
		
		catch (PersistenceException pe) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Ha ocurrido un error al buscar los pedidos disponiblesy sus pesos y volumenes");
			
			throw new MyPersistenceException(sb.toString(), pe);
		}
	}

	// ===============================================
	// Empaquetado de productos
	// ===============================================

	public static List<Pedido> findPosibleEmpaquetarOT(OrdenTrabajo ordenTrabajo) throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Pedido.findPosibleEmpaquetar", Pedido.class)
					.setParameter("ordenTrabajo", ordenTrabajo).getResultList();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema pedidos disponibles para la orden de trabajo = ");
			sb.append(ordenTrabajo.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

	public static long findNumProductosPosibleEmpaquetar(OrdenTrabajo ordenTrabajo, Pedido pedido)
			throws MyPersistenceException {
		try {
			return Jpa.getManager().createNamedQuery("Pedido.findNumProductosPosibleEmpaquetar", Long.class)
					.setParameter("ordenTrabajo", ordenTrabajo).setParameter("pedido", pedido).getSingleResult();
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();

			sb.append("Ha ocurrido un problema pedidos disponibles para la orden de trabajo = ");
			sb.append(ordenTrabajo.getId());

			throw new MyPersistenceException(sb.toString(), e);
		}
	}

}