package business.impl.recogida.versionInestable;

import org.hibernate.cfg.NotYetImplementedException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Pedido;
import model.types.EstadoPedido;
import persistence.util.Jpa;

public class InsertOrdenTrabajo implements Command {
	
	private Pedido pedido;
	
	public InsertOrdenTrabajo(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public Object execute() throws BusinessException {
		
		pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);

		Pedido pedido = Jpa.getManager().merge(this.pedido);

//		OrdenTrabajo ordenTrabajo = new OrdenTrabajo(); TODO
//		ordenTrabajo.setEstadoOrdenTrabajo(EstadoOrdenTrabajo.RECOGIDA);
//		Date date = new Date();
//		ordenTrabajo.setFecha(date);

//		ProductoEnPedido productoEnPedido; 
		
//		for (ProductoEnPedido producto : pedido.getListaProductosPedidos()) {
//			productoEnPedido = Jpa.getManager().merge(producto);
//			Jpa.getManager().persist(new ProductoEnOrdenTrabajo(ordenTrabajo, productoEnPedido, productoEnPedido.getCantidad()));
//		}
//		
//		Jpa.getManager().persist(ordenTrabajo);
//
//		return ordenTrabajo;
		throw new NotYetImplementedException("Hay que asignar a un almacenero a la orden de trabajo D:");
	}

}