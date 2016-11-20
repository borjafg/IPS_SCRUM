package business.impl.recogida.generacionOrdenesTrabajo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import business.impl.recogida.generacionOrdenesTrabajo.myTypes.ProductoRecoger;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPedido;
import model.types.EstadoOrdenTrabajo;
import model.types.EstadoPedido;
import persistence.util.Jpa;

class CrearOrdenesTrabajo_FragmentacionPedidos {

	private Pedido pedido;
	private Almacenero almcacenero;

	// ----------------------------------------------
	// Límite de peso y volumen por orden de trabajo
	// ----------------------------------------------

	private double pesoLimite;
	private double volumenLimite;

	// ----------------------------------------------
	// Peso y volumen actual de la orden de trabajo
	// ----------------------------------------------

	private double pesoActual;
	private double volumenActual;

	/**
	 * Constructor de la clase {@link CrearOrdenesTrabajo_FragmentacionPedidos}.
	 * 
	 * @param pedido
	 *            pedido que se fragmentará para crear OT
	 * 
	 * @param almacenero
	 *            almacenero que recogerá los productos de la OT
	 * 
	 * @param peso
	 *            peso límite de cada OT
	 * 
	 * @param volumen
	 *            volumen límite de cada OT
	 * 
	 */
	public CrearOrdenesTrabajo_FragmentacionPedidos(Pedido pedido, Almacenero almacenero, double pesoLimite,
			double volumenLimite) {

		this.pedido = pedido;
		this.almcacenero = almacenero;

		this.pesoLimite = pesoLimite;
		this.volumenLimite = volumenLimite;

		this.pesoActual = 0;
		this.volumenActual = 0;
	}

	/**
	 * Crea una orden de trabajo uniendo varios pedidos, si caben enteros en la
	 * OT.
	 * 
	 * @return null (le indica a las capas superiores que se han creado
	 *         múltiples OT)
	 * 
	 * @throws PersistenceException
	 * 
	 */
	public OrdenTrabajo crear() throws PersistenceException {
		// -----------------------------------------------------------
		// (1) Preparar la lista de productos de la orden de trabajo
		// -----------------------------------------------------------

		Set<ProductoEnPedido> prodPedidos = pedido.getListaProductosPedidos();

		List<ProductoRecoger> productosQuedan = new ArrayList<ProductoRecoger>();

		for (ProductoEnPedido pep : prodPedidos) {
			productosQuedan.add(new ProductoRecoger(pep));
		}

		Collections.sort(productosQuedan);

		// -----------------------------------------------------------
		// (2) Crear las órdenes de trabajo necesarias
		// -----------------------------------------------------------

		List<OrdenTrabajo> ordenesTrabajoCreadas = new ArrayList<OrdenTrabajo>();
		List<ProductoRecoger> eliminarLista = new ArrayList<ProductoRecoger>();

		OrdenTrabajo ot;
		ProductoEnOrdenTrabajo pot;

		int unidades;
		int ref;

		// ===========================================================
		// Mientras no se hayan asignado todos los productos a una OT
		// ===========================================================

		while (!productosQuedan.isEmpty()) {
			ref = 0;

			ot = new OrdenTrabajo(almcacenero);

			ot.setFechaCreacion(new Date());
			ot.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.RECOGIDA);

			Jpa.getManager().persist(ot);

			// ===================================
			// Evaluar los productos que quedan
			// ===================================

			for (ProductoRecoger pr : productosQuedan) {
				unidades = calcularUnidadesMeterCarrito(pr);

				if (unidades > 0) {
					pot = new ProductoEnOrdenTrabajo(ot, pr.getProductoPedido());

					pot.setRef_OrdenTrabajo("POT-" + ref);
					pot.setUnidadesproducto(unidades);

					Jpa.getManager().persist(pot);

					pr.quitar(unidades);
					actualizarCarrito(pr.getProductoPedido(), unidades);
					ref++;

					// ================================
					// Si no queda nada del producto
					// ================================

					if (pr.getUnidades() == 0) {
						eliminarLista.add(pr);
					}
				}
			}

			// ==============================================
			// Eliminar productos de los que no queda nada
			// ==============================================

			for (ProductoRecoger pr : eliminarLista) {
				productosQuedan.remove(pr);
			}

			// ===============================
			// Reiniciar para la siguiente OT
			// ===============================

			eliminarLista.clear();

			this.pesoActual = 0;
			this.volumenActual = 0;
			
			ordenesTrabajoCreadas.add(ot);
		}

		pedido.setEstado(EstadoPedido.COMPLETAMENTE_ASOCIADO_OT);
		
		// --------------------------------------------------------------------
		// (3) Devolver la primera orden de trabajo creada
		// --------------------------------------------------------------------

		return ordenesTrabajoCreadas.get(0);
	}

	/**
	 * Calcula las unidades que se pueden meter en la orden de trabajo
	 * 
	 * @param pr
	 *            producto del que hay que calcular cuantas unidades asociar a
	 *            la OT
	 * 
	 * @return unidades que se asociarán a la orden de trabajo
	 * 
	 */
	private int calcularUnidadesMeterCarrito(ProductoRecoger pr) {
		int unidadesFaltan = pr.getUnidades();
		int unidadesRecoger = 0;

		double pesoProducto;
		double volumenProducto;

		for (int i = 1; i <= unidadesFaltan; i++) {
			pesoProducto = pr.getProductoPedido().getProducto().getPeso() * i;
			volumenProducto = pr.getProductoPedido().getProducto().getVolumen() * i;

			if ((pesoActual + pesoProducto) <= pesoLimite && (volumenActual + volumenProducto) <= volumenLimite) {
				unidadesRecoger++;
			}

			else {
				break; // No caben más unidades
			}
		}

		return unidadesRecoger;
	}

	/**
	 * Actualiza el peso y volumen que quedará en el carrito después de añadir
	 * un producto.
	 * 
	 * @param pep
	 *            producto de un pedido que se añade a la OT
	 * @param unidades
	 *            unidades del producto que se han añadido a la OT
	 * 
	 */
	private void actualizarCarrito(ProductoEnPedido pep, int unidades) {
		pesoActual = pesoActual + (unidades * pep.getProducto().getPeso());
		volumenActual = volumenActual + (unidades * pep.getProducto().getVolumen());
	}

}