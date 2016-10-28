package business.impl.recogida;

import business.impl.util.Command;
import model.ProductoEnOrdenTrabajo;
import persistence.util.Jpa;

public class ActualizarProductoEnOrden implements Command {

	private ProductoEnOrdenTrabajo producto;

	public ActualizarProductoEnOrden(ProductoEnOrdenTrabajo producto) {
		this.producto = producto;
	}

	@Override
	public Object execute() {
		ProductoEnOrdenTrabajo prot = Jpa.getManager().merge(producto);
		
		prot.recoger(prot.getUnidadesRecoger());
		
		return null;
	}

}