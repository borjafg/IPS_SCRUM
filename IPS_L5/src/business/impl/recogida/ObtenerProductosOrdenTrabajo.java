package business.impl.recogida;

import java.util.ArrayList;
import java.util.Set;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.ProductoEnOrdenTrabajo;
import persistence.OrdenTrabajoFinder;
import persistence.util.Jpa;

public class ObtenerProductosOrdenTrabajo implements Command {

	private OrdenTrabajo ordenTrabajo;

	public ObtenerProductosOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		ArrayList<ProductoEnOrdenTrabajo> productos = null;

		OrdenTrabajo ordenTrabajo = OrdenTrabajoFinder.findbyId(this.ordenTrabajo.getId());

		Set<ProductoEnOrdenTrabajo> productoOrdenTrabajoSet = ordenTrabajo.getProductosOrdenTrabajo();

		productos = new ArrayList<ProductoEnOrdenTrabajo>();

		
		ProductoEnOrdenTrabajo pot;
		for (ProductoEnOrdenTrabajo productoOrdenTrabajo : productoOrdenTrabajoSet)
		{
			pot = Jpa.getManager().merge(productoOrdenTrabajo);
			productos.add(pot);
		}

		return productos;
	}

}