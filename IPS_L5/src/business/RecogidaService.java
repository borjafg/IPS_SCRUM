package business;

import java.util.List;
import java.util.Set;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido;

public interface RecogidaService {

	public List<MyPedido> obtenerListaPedidosSinOrdenTrabajo() throws BusinessException;

	public OrdenTrabajo generarOrdenTrabajo(Pedido pedido, Almacenero almacenero) throws BusinessException;

	public boolean huboIncidencias(OrdenTrabajo ordenTrabajo) throws BusinessException;

	public Set<ProductoEnOrdenTrabajo> obtenerProductosOT(OrdenTrabajo ordenTrabajo) throws BusinessException;

}