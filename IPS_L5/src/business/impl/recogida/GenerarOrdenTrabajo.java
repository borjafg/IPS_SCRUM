package business.impl.recogida;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.exception.BusinessException;
import business.impl.recogida.myTypes.ProductoRecoger;
import business.impl.util.Command;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPedido;
import model.types.EstadoPedido;
import persistence.PedidoFinder;
import persistence.util.Jpa;

public class GenerarOrdenTrabajo implements Command {

	private Pedido pedido; // Sobre este pedido se genera la OT
	private Almacenero almacenero; // Es quien solicita la OT

	// =============================================
	// Necesario para generar la orden de trabajo
	// =============================================

	private List<Pedido> pedidosEvaluados = new ArrayList<Pedido>();
	private int espacioCarrito = 20;

	private int ultimoNumRef = 0;

	// ==================================
	// Si hay que juntar más pedidos
	// ==================================

	private List<Pedido> posiblesPedidos = null;
	private int ultimoPedidoEvaluado = 0;

	/**
	 * Constructor de la clase GenerarOrdenTrabajo.
	 * 
	 * @param pedido
	 *            pedido que se usará para generar la orden de trabajo
	 *
	 */
	public GenerarOrdenTrabajo(Pedido pedido, Almacenero almacenero) {
		this.pedido = pedido;
		this.almacenero = almacenero;
	}

	@Override
	public Object execute() throws BusinessException {
		// ------------------------------------------
		// ---- Sincronizar con la base de datos ----
		// ------------------------------------------

		this.almacenero = Jpa.getManager().merge(almacenero);

		Pedido p = Jpa.getManager().find(Pedido.class, pedido.getId());

		OrdenTrabajo ordenTrabajo = new OrdenTrabajo(almacenero);
		ordenTrabajo.setFecha(new Date());

		Jpa.getManager().persist(ordenTrabajo);

		// ------------------------------------------

		Pedido sigPedido = null;
		ProductoRecoger productoRecoger = null;

		while (cabenProductosOtroPedido() && pedidosEvaluados.size() <= 5) {

			// Si ya se comprobó al menos un pedido y hay sitio en el carrito

			if (pedidosEvaluados.size() >= 1) {
				sigPedido = obtenerSiguientePedido(p);

				if (sigPedido != null) {
					p = sigPedido;
				}

				else {
					break; // No quedan más pedidos que evaluar
				}
			}

			// Para cada producto en el pedido

			for (ProductoEnPedido aux : p.getListaProductosPedidos()) {
				productoRecoger = comprobarProducto(Jpa.getManager().merge(aux));

				if (productoRecoger.getUnidades() > 0) {
					añadirProductoOT(ordenTrabajo, productoRecoger);
				}
			}

			actualizarEstadoPedido(p);

			pedidosEvaluados.add(p);
		}

		return ordenTrabajo;
	}

	// ===================================================
	// Controlar el espacio del carrito
	// ===================================================

	private void actualizarEspacioCarrito(int unidadesNuevoProd) {
		espacioCarrito = espacioCarrito - unidadesNuevoProd;
	}

	private boolean cabenProductosOtroPedido() {
		if (espacioCarrito > 0) {
			return true;
		}

		return false;
	}

	// =============================================================
	// Si el pedido es pequeño buscar otro para llenar el carrito
	// =============================================================

	/**
	 * Busca un pedido que se pueda añadir a la orden de trabajo
	 * 
	 * @param
	 * 
	 * @return pedido que todavía no se ha evaluado y que tiene productos que se
	 *         pueden añadir a la orden de trabajo
	 *
	 */
	private Pedido obtenerSiguientePedido(Pedido p) {

		// La primera vez hay que buscar más pedidos
		if (posiblesPedidos == null) {
			posiblesPedidos = PedidoFinder.findPosibleRecoger_NoPedido(p);
		}

		for (int i = ultimoPedidoEvaluado; i < posiblesPedidos.size(); i++) {

			// Si no está en la lista de productos evaluados
			if (!pedidosEvaluados.contains(posiblesPedidos.get(i))) {
				ultimoPedidoEvaluado = i;

				return posiblesPedidos.get(i);
			}
		}

		return null; // No quedan pedidos que se puedan añadir
	}

	// ==================================================================
	// Evaluar un producto para saber cuántas unidades caben el carrito
	// ==================================================================

	private ProductoRecoger comprobarProducto(ProductoEnPedido prodPedido) {
		int unidadesFaltan = prodPedido.getCantidad() - prodPedido.getCantidadAsociadaOT();
		int unidadesRecoger = 0;

		for (int i = 1; i <= unidadesFaltan; i++) {
			if (espacioCarrito - i >= 0) {
				unidadesRecoger++;
			}
		}

		return new ProductoRecoger(prodPedido, unidadesRecoger);
	}

	// ==========================================================
	// Añadir productos a la orden de trabajo
	// ==========================================================

	private void añadirProductoOT(OrdenTrabajo ordenTrabajo, ProductoRecoger productoRecoger) {

		// -------------------------
		// ---- Añadir producto ----
		// -------------------------

		ProductoEnOrdenTrabajo prodOT = new ProductoEnOrdenTrabajo(ordenTrabajo, productoRecoger.getProducto(),
				productoRecoger.getUnidades());

		prodOT.setRef_OrdenTrabajo("POT-" + ultimoNumRef);
		ultimoNumRef++;

		Jpa.getManager().persist(prodOT);

		// ------------------------------
		// ---- Actualizar el pedido ----
		// ------------------------------

		productoRecoger.getProducto().setCantidadAsociadaOT(
				productoRecoger.getProducto().getCantidadAsociadaOT() + productoRecoger.getUnidades());

		// -------------------------------------------
		// ---- Actualizar el espacio del carrito ----
		// -------------------------------------------

		actualizarEspacioCarrito(productoRecoger.getUnidades());
	}

	// =============================================================
	// Si el pedido ya tiene todos su productos asociados a una OT
	// =============================================================

	private void actualizarEstadoPedido(Pedido pedido) {
		boolean completamenteAsociadoOT = true;

		for (ProductoEnPedido pp : pedido.getListaProductosPedidos()) {

			if (pp.getCantidad() > pp.getCantidadAsociadaOT()) {
				completamenteAsociadoOT = false;

				break;
			}
		}

		if (completamenteAsociadoOT) {
			pedido.setEstado(EstadoPedido.COMPLETAMENTE_ASOCIADO_OT);
		}
	}
}