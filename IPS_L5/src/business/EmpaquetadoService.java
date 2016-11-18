package business;

import java.util.List;

import business.exception.BusinessException;
import model.Almacenero;
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

	public List<OrdenTrabajo> cargarOT_empaquetar(Almacenero almacenero) throws BusinessException;

	public List<MyPedido_OT_Retomar> getPedidosOT(OrdenTrabajo ot) throws BusinessException;

	public void asignarAlmaceneroOT(Almacenero almacenero, OrdenTrabajo ordenTrabajo) throws BusinessException;

	// --------------------------------------------
	// Carga de una orden de trabajo
	// --------------------------------------------

	public Paquete cargarPaquete(OrdenTrabajo ordenTrabajo) throws BusinessException;

	public List<ProductoEnOrdenTrabajo> cargarProductosOT(OrdenTrabajo ordenTrabajo, Pedido pedido)
			throws BusinessException;

	// --------------------------------------------
	// Proceso de empaquetado
	// --------------------------------------------

	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete, int unidades)
			throws BusinessException;

	public boolean sePuedeCerrarPaquete(Paquete paqueteActual) throws BusinessException;

	public Paquete cerrarPaquete(Paquete paquete, OrdenTrabajo ordenTrabajo) throws BusinessException;

	public Cliente getClientePedido(Pedido pedido) throws BusinessException;

	public void terminarOrdenTrabajo(OrdenTrabajo ordenTrabajo) throws BusinessException;

}