package business.impl.recogida;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import persistence.PedidoFinder;
import persistence.util.Jpa;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPedido;
import business.exception.BusinessException;
import business.impl.recogida.util.ProductoRecoger;
import business.impl.util.Command;

public class GeneradorOrdenTrabajo implements Command {

	private List<Pedido> pedidosEvaluados = new ArrayList<Pedido>();
	private Pedido pedido;
	private int tamañoCarrito = 20;
	private int numPedidosComprobados = 0;

	public GeneradorOrdenTrabajo(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public Object execute() throws BusinessException {
		List<ProductoRecoger> productosRecoger = new ArrayList<ProductoRecoger>();
		Pedido p = Jpa.getManager().merge(pedido); // pedidos enlazados a la
													// base de datos

		Pedido sigPedido = null;
		ProductoRecoger productoRecoger = null;

		while (cabenProductosOtroPedido() && numPedidosComprobados <= 3) {
			if (numPedidosComprobados >= 1) {
				sigPedido = obtenerSiguientePedido();

				if (sigPedido != null) {
					p = Jpa.getManager().merge(sigPedido);
				}
				
				else {
					break; // No quedan más pedidos que evaluar
				}
			}

			for (ProductoEnPedido aux : p.getListaProductosPedidos()) {
				productoRecoger = ComprobarProducto(Jpa.getManager().merge(aux));

				if (productoRecoger.getUnidades() > 0) {
					productosRecoger.add(productoRecoger);
					actualizarEspacioCarrito(productoRecoger.getUnidades());
				}
			}

			numPedidosComprobados++;
			pedidosEvaluados.add(p);
		} // Fin while

		// Crear orden de trabajo
		// Para todos los pedidos a añadir --> añadir orden de trabajo
		// Actualizar el estado de los pedidos que se evaluaron

		List<ProductoEnOrdenTrabajo> ordenTrabajo = new ArrayList<ProductoEnOrdenTrabajo>();

		return ordenTrabajo;

	}

	private ProductoRecoger ComprobarProducto(ProductoEnPedido prodPedido) {
		int unidadesPedidas = prodPedido.getCantidad();
		int unidadesEnOT = calcularUnidadesOT(prodPedido
				.getProductosEnOrdenTrabajo());

		int unidadesFaltan = unidadesPedidas - unidadesEnOT;
		int unidadesRecoger = 0;

		for (int i = 1; i <= unidadesFaltan; i++) {
			if (tamañoCarrito - i >= 0) {
				unidadesRecoger++;
			}
		}

		return new ProductoRecoger(prodPedido, unidadesRecoger);
	}

	private int calcularUnidadesOT(
			Set<ProductoEnOrdenTrabajo> productosEnOrdenTrabajo) {
		int unidadesOT = 0;

		for (ProductoEnOrdenTrabajo prodOT : productosEnOrdenTrabajo) {
			unidadesOT = unidadesOT + prodOT.getUnidadesRecoger();
		}

		return unidadesOT;
	}

	private void actualizarEspacioCarrito(int unidadesNuevoProd) {
		tamañoCarrito = tamañoCarrito - unidadesNuevoProd;
	}

	private boolean cabenProductosOtroPedido() {
		if (tamañoCarrito > 0) {
			return true;
		}

		return false;
	}

	/**
	 * Busca un pedido que se pueda añadir a la orden de trabajo
	 * 
	 * @return pedido que todavía no se ha evaluado y que tiene productos que se
	 *         pueden añadir a la orden de trabajo
	 *
	 */
	private Pedido obtenerSiguientePedido() {
		List<Pedido> pedidosPosiblesRecoger = PedidoFinder.findPosibleRecoger();

		boolean evaluado = false;

		for (Pedido pedido : pedidosPosiblesRecoger) {
			for (Pedido pedEvaluado : pedidosEvaluados) {
				if (pedido.equals(pedEvaluado)) {
					evaluado = true;
				}
			}

			if (!evaluado) { // Si no está en la lista de productos evaluados
				return pedido;
			}

			evaluado = false;
		}

		return null; // No quedan pedidos que se puedan añadir
	}
}