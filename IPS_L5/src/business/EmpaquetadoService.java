package business;

import java.util.List;

import business.exception.BusinessException;
import model.Cliente;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;

public interface EmpaquetadoService {
	public List<ProductoEnOrdenTrabajo> getListaProductosOrdenTrabajo(long id) throws BusinessException;

	public void asignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paqueteActual) throws BusinessException;

	public Cliente getClientePedido(Pedido pedido) throws BusinessException;
}