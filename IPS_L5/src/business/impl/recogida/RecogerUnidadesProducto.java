package business.impl.recogida;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.ProductoEnOrdenTrabajo;
import model.types.EstadoOrdenTrabajo;
import persistence.ProductoEnOrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;

public class RecogerUnidadesProducto implements Command {

	private ProductoEnOrdenTrabajo producto;
	private int unidades;

	public RecogerUnidadesProducto(ProductoEnOrdenTrabajo producto, int unidadesRecoger) {
		this.producto = producto;
		this.unidades = unidadesRecoger;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			ProductoEnOrdenTrabajo prod = ProductoEnOrdenTrabajoFinder.find(producto);

			prod.recoger(unidades);

			comprobarEstadoOrdenTrabajo(prod.getOrdenTrabajo());

		} catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException(e);
		}

		return null;
	}

	private void comprobarEstadoOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		boolean recogidaCompleta = true;

		for (ProductoEnOrdenTrabajo prod : ordenTrabajo.getProductosOrdenTrabajo()) {

			if (prod.getUnidadesProducto() > prod.getUnidadesRecogidas()) {
				recogidaCompleta = false; // Faltan productos por recoger
				break;
			}
		}

		if (recogidaCompleta) {
			ordenTrabajo.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.EMPAQUETADO);
		}
	}

}