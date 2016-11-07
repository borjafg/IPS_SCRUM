package business;

import java.util.List;
import java.util.Set;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

public interface RecogidaService {

	// ----------------------------------------
	// Creación de una orden de trabajo
	// ----------------------------------------

	public List<MyPedido_OT_Retomar> obtenerListaPedidosSinOrdenTrabajo() throws BusinessException;

	public OrdenTrabajo generarOrdenTrabajo(Pedido pedido, Almacenero almacenero) throws BusinessException;

	// ----------------------------------------
	// Procesamiento de una orden de trabajo
	// ----------------------------------------

	public Set<ProductoEnOrdenTrabajo> obtenerProductosOT(OrdenTrabajo ordenTrabajo) throws BusinessException;

	public void recogerUnidadesProducto(ProductoEnOrdenTrabajo prod, int unidadesRecoger) throws BusinessException;

	// -------------------------------
	// Gestión de incidencias
	// -------------------------------

	public void registrarIncidencia(OrdenTrabajo ordenTrabajo, String incidencia) throws BusinessException;

	public boolean huboIncidencias(OrdenTrabajo ordenTrabajo) throws BusinessException;

}