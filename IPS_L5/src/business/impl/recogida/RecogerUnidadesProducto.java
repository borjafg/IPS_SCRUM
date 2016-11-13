package business.impl.recogida;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.ProductoEnOrdenTrabajo;
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

		} catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al intentar recoger un producto", e);
		}

		return null;
	}

}