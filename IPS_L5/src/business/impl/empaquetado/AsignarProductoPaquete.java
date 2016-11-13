package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Paquete;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPaquete;
import persistence.PaqueteFinder;
import persistence.ProductoEnOrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class AsignarProductoPaquete implements Command {

	private ProductoEnOrdenTrabajo producto;
	private Paquete paquete;
	private int unidades;

	public AsignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete, int unidades) {
		this.producto = producto;
		this.paquete = paquete;
		this.unidades = unidades;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			ProductoEnOrdenTrabajo pot = ProductoEnOrdenTrabajoFinder.find(producto);
			Paquete paq = PaqueteFinder.find(paquete);

			pot.empaquetar(unidades);

			// ---------------------------------------------------
			// Si el primer producto que se añade al paquete
			// ---------------------------------------------------

			if (paq.getPedido() == null) {
				colocarProductoPaquete(pot, paq);
				paq.setPedido(pot.getproductoPedido().getPedido());
			}

			// ---------------------------------------------------------
			// Si ya se habia añadido algún producto al paquete
			// ---------------------------------------------------------

			// (1) Si los productos del paquete son del mismo pedido
			// del producto que intento añadir

			else if (paq.getPedido().equals(pot.getproductoPedido().getPedido())) {
				for (ProductoEnPaquete prodPaq : paq.getProductosPaquete()) {

					// Si ya habia unidades de ese producto en el paquete

					if (prodPaq.getProductoOrdenTrabajo().equals(pot)) {
						prodPaq.setUnidadesProducto(prodPaq.getUnidadesProducto() + unidades);
						return null;
					}
				}

				// Si no habia unidades de ese producto en el paquete
				colocarProductoPaquete(pot, paq);
			}

			// (2) Si no se puede añadir al paquete

			else {
				throw new BusinessException("En un paquete sólo puede haber productos de un mismo pedido");
			}

			return null;
		}

		catch (IllegalArgumentException e) {
			throw new BusinessException("La cantidad indicada supera a la que requiere la OT");
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al asociar un producto a un paquete", e);
		}
	}

	/**
	 * Añade un producto a un paquete (usar sólo si no se habían metido antes
	 * unidades de ese producto en el paquete).
	 * 
	 * @param pot
	 *            producto a añadir al paquete
	 * @param paq
	 *            paquete en el que hay que guardar el producto
	 * 
	 */
	private void colocarProductoPaquete(ProductoEnOrdenTrabajo pot, Paquete paq) {
		ProductoEnPaquete prodPaquete = new ProductoEnPaquete(pot, paq);

		prodPaquete.setUnidadesProducto(unidades);

		Jpa.getManager().persist(prodPaquete);
	}

}