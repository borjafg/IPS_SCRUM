package business;

import java.util.List;

import business.exception.BusinessException;
import model.Cliente;
import model.OrdenTrabajo;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

public interface EmpaquetadoService {

	// --------------------------------------------
	// Retomar una orden de trabajo
	// --------------------------------------------

	public List<ProductoEnOrdenTrabajo> getListaProductosOrdenTrabajo(long id) throws BusinessException;

	public List<MyPedido_OT_Retomar> getPedidosOT(OrdenTrabajo ot) throws BusinessException;

	// --------------------------------------------
	// Carga de una orden de trabajo
	// --------------------------------------------

	public Paquete cargarPaquete(OrdenTrabajo ordenTrabajo) throws BusinessException;

	public List<ProductoEnOrdenTrabajo> cargarProductosOT(OrdenTrabajo ordenTrabajo, Pedido pedido)
			throws BusinessException;

	// --------------------------------------------
	// Proceso de empaquetado
	// --------------------------------------------

	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete) throws BusinessException;

	public Cliente getClientePedido(Pedido pedido) throws BusinessException;
}