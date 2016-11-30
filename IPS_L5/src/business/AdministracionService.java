package business;

import java.util.List;

import business.exception.BusinessException;
import model.Pedido;
import ui.administracion.myTypes.DatosInformeAlmacenero;
import ui.administracion.myTypes.DatosInformeEmpaquetado;
import ui.administracion.myTypes.DatosInformeMetodoPago;

public interface AdministracionService {

	public List<DatosInformeAlmacenero> generarInformeRecogida() throws BusinessException;

	public List<Pedido> generarPedidosNoPagados() throws BusinessException;
	
	public void confirmarPedido (Pedido pedido)throws BusinessException;
	
	public List<DatosInformeEmpaquetado> generarInformeEmpaquetado() throws BusinessException;
	
	public List<DatosInformeMetodoPago> generarInformeMetodoPago() throws BusinessException;
	
}