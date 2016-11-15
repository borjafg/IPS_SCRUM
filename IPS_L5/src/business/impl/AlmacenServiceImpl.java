package business.impl;

import java.util.List;
import java.util.Set;

import business.RecogidaService;
import business.exception.BusinessException;
import business.impl.recogida.HuboIncidencias;
import business.impl.recogida.MarcarOT_empaquetado;
import business.impl.recogida.ObtenerListaPedidosSinOrdenTrabajo;
import business.impl.recogida.ObtenerProductosOT;
import business.impl.recogida.RecogerUnidadesProducto;
import business.impl.recogida.RegistrarIncidencia;
import business.impl.recogida.generacionOrdenesTrabajo.GenerarOrdenTrabajo;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

@SuppressWarnings("unchecked")
public class AlmacenServiceImpl implements RecogidaService {

	private CommandExecutor executor = new CommandExecutor();

	// ----------------------------------------
	// Creación de una orden de trabajo
	// ----------------------------------------

	@Override
	public List<MyPedido_OT_Retomar> obtenerListaPedidosSinOrdenTrabajo() throws BusinessException {
		return (List<MyPedido_OT_Retomar>) executor.execute(new ObtenerListaPedidosSinOrdenTrabajo());
	}

	@Override
	public OrdenTrabajo generarOrdenTrabajo(Pedido pedido, Almacenero almacenero) throws BusinessException {
		return (OrdenTrabajo) executor.execute(new GenerarOrdenTrabajo(pedido, almacenero));
	}

	// ----------------------------------------
	// Procesamiento de una orden de trabajo
	// ----------------------------------------

	@Override
	public Set<ProductoEnOrdenTrabajo> obtenerProductosOT(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (Set<ProductoEnOrdenTrabajo>) executor.execute(new ObtenerProductosOT(ordenTrabajo));
	}

	@Override
	public void recogerUnidadesProducto(ProductoEnOrdenTrabajo prod, int unidadesRecoger) throws BusinessException {
		executor.execute(new RecogerUnidadesProducto(prod, unidadesRecoger));
	}
	
	@Override
	public void marcarOT_empaquetado(OrdenTrabajo ordenTrabajo, Almacenero almacenero) throws BusinessException {
		executor.execute(new MarcarOT_empaquetado(ordenTrabajo, almacenero));
	}

	// -------------------------------
	// Gestión de incidencias
	// -------------------------------

	@Override
	public void registrarIncidencia(OrdenTrabajo ordenTrabajo, String incidencia) throws BusinessException {
		executor.execute(new RegistrarIncidencia(ordenTrabajo, incidencia));
	}

	@Override
	public boolean huboIncidencias(OrdenTrabajo ordenTrabajo) throws BusinessException {
		return (boolean) executor.execute(new HuboIncidencias(ordenTrabajo));
	}

}