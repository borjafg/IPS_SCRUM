package business.impl.recogida.generacionOrdenesTrabajo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPedido;
import model.types.EstadoOrdenTrabajo;
import model.types.EstadoPedido;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

class CrearOrdenTrabajo_UnionPedidos {

	private Pedido pedido;
	private Almacenero almacenero;

	private double pesoLimite;
	private double volumenLimite;

	public CrearOrdenTrabajo_UnionPedidos(Pedido pedido, Almacenero almacenero, double peso, double volumen) {
		this.pedido = pedido;
		this.almacenero = almacenero;

		this.pesoLimite = peso;
		this.volumenLimite = volumen;
	}

	/**
	 * Crea una orden de trabajo uniendo varios pedidos, si caben enteros en la
	 * OT.
	 * 
	 * @return orden de trabajo creada
	 * 
	 * @throws MyPersistenceException
	 * @throws PersistenceException
	 * @throws BusinessException
	 * 
	 */
	public OrdenTrabajo crear() throws MyPersistenceException, PersistenceException, BusinessException {

		List<ProductoEnPedido> productosOT = new ArrayList<ProductoEnPedido>();
		List<Pedido> pedidosOT = new ArrayList<Pedido>();

		// --------------------------------------------------------
		// (Parte 1) Meter el pedido en la orden de trabajo
		// --------------------------------------------------------

		meterPedidoOT(productosOT, pedido);
		pedidosOT.add(pedido);

		// --------------------------------------------------------
		// (Parte 2) Ahora que ya conocemos el espacio que sobra
		// --------------------------------------------------------

		List<Object[]> pedidosSinOT = findPedidosFaltan(pedido);

		Pedido ped;

		double peso;
		double volumen;

		for (Object[] pedInfo : pedidosSinOT) {
			ped = (Pedido) pedInfo[0];
			peso = (double) pedInfo[1];
			volumen = (double) pedInfo[2];

			if ((pesoLimite - peso) > 0 && (volumenLimite - volumen) > 0) {
				meterPedidoOT(productosOT, ped);
				pedidosOT.add(ped);
			}
		}

		// --------------------------------------------------------
		// (Parte 3) Crear la orden de trabajo con esos productos
		// --------------------------------------------------------

		OrdenTrabajo ot = new OrdenTrabajo(almacenero);

		ot.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.RECOGIDA);
		ot.setFechaCreacion(new Date());

		Jpa.getManager().persist(ot);

		// --------------------------------------------------------
		// (Parte 4) Asociar los productos con la orden de trabajo
		// --------------------------------------------------------

		ProductoEnOrdenTrabajo pot;
		int ref = 0;

		for (ProductoEnPedido pep : productosOT) {
			pot = new ProductoEnOrdenTrabajo(ot, pep);

			pot.setUnidadesproducto(pep.getCantidad());
			pot.setRef_OrdenTrabajo("POT-" + ref);

			Jpa.getManager().persist(pot);

			ref++;
		}

		for (Pedido pedidoOT : pedidosOT) {
			pedidoOT.setEstado(EstadoPedido.COMPLETAMENTE_ASOCIADO_OT);
		}

		return ot;
	}

	// ==============================================================
	// ==============================================================
	//
	// Gesti�n del carrito y la Orden de Trabajo
	//
	// ==============================================================
	// ==============================================================

	/**
	 * A�ade a la orden de trabajo los productos del pedido que se le indica y
	 * actualiza el carrito.
	 * 
	 * @param productosOT
	 *            productos que formar�n parte de la OT
	 * @param pedido
	 *            pedido del que se van a a�adir productos
	 * 
	 */
	private void meterPedidoOT(List<ProductoEnPedido> productosOT, Pedido pedido) {
		for (ProductoEnPedido pep : pedido.getListaProductosPedidos()) {
			productosOT.add(pep);
			actualizarCarrito(pep);
		}
	}

	/**
	 * Actualiza el peso y volumen que quedar� en el carrito despu�s de a�adir
	 * un producto.
	 * 
	 * @param pep
	 *            producto de un pedido que se a�ade a la OT
	 * 
	 */
	private void actualizarCarrito(ProductoEnPedido pep) {
		pesoLimite = pesoLimite - (pep.getCantidad() * pep.getProducto().getPeso());
		volumenLimite = volumenLimite - (pep.getCantidad() * pep.getProducto().getVolumen());
	}

	// ==============================================================
	// ==============================================================
	//
	// Obtener Lista de pedidos junto con sus peso y volumen
	//
	// ==============================================================
	// ==============================================================

	/**
	 * Devuelve una lista de pedidos junto con su peso y volumen
	 * 
	 * @param pedido
	 *            del que no se quiere buscar informac
	 * 
	 * @return lista de pedidos sin OT, junto con su peso y su volumen
	 * 
	 * @throws BusinessException
	 *             Ha ocurrido un error al evaluar extraer la informaci�n de la
	 *             base de datos
	 * 
	 */
	private List<Object[]> findPedidosFaltan(Pedido pedido) throws BusinessException {
		try {
			List<Pedido> pedidos = PedidoFinder.findPosibleRecoger_NoPedido(pedido);

			List<Object[]> pedidosInfo = new ArrayList<Object[]>();

			Object[] infoPed;

			for (Pedido ped : pedidos) {
				infoPed = PedidoFinder.findPesoVolumen(ped);

				pedidosInfo.add(new Object[] { ped, infoPed[0], infoPed[1] });
			}

			return pedidosInfo;
		}

		catch (MyPersistenceException mpe) {
			throw new BusinessException("Ha ocurrido un error al evaluar los pedidos sin OT", mpe);
		}
	}

}