package ui.almacen.recogida.logica;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.OrdenTrabajo;
import model.Pedido;
import business.exception.BusinessException;
import business.impl.AlmacenServiceImpl;
import infrastructure.ServiceFactory;

/**
 * Clase que obtiene todos los pedidos disponible, los muestra y genera órdenes de trabajo a partir de los pedidos elegidos
 * @author Nacho
 *
 */

public class TerminalPedido {
	
	private List<Pedido> pedidos;
	private OrdenTrabajo ot;
	
	public TerminalPedido() throws BusinessException {
		new AlmacenServiceImpl();
		cargarPedidos();
		ordenarPedidos();
	}

	/**
	 * Devuelve una lista con todos los pedidos cargados
	 * @return	lista con todos los pedidos que se han cargado
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	/**
	 * Genera la orden de trabaja a partir de un pedido dado
	 * @param pedido	el pedido que se usará para generar la orden de trabajo
	 * @throws BusinessException 
	 */
	public void generarOrdenTrabajo(Pedido pedido) throws BusinessException { 
		this.ot =  insertarOrdenTrabajo(pedido);
	}
	
	/**
	 * Devuelve la orden de trabajo generada, si ha sido generada
	 * @return	última orden de trabajo si ha sido generada, null en otro caso
	 */
	public OrdenTrabajo getOrdenTrabajo() {
		return ot;
	}


	private void cargarPedidos() throws BusinessException {
		 this.pedidos = obtenerPedidos();
	}
	
	private List<Pedido> obtenerPedidos() throws BusinessException { 
		return ServiceFactory.getRecogidaService().getAllPedidos();
	}
	

	private void ordenarPedidos() {		
		Collections.sort(this.pedidos, new Comparator<Pedido>() {
			@Override
			public int compare(Pedido arg0, Pedido arg1) {
				return arg0.getFecha().compareTo(arg1.getFecha());
			}
	    });
		
	}
	
	
	private OrdenTrabajo insertarOrdenTrabajo(Pedido pedido) throws BusinessException {
		return ServiceFactory.getRecogidaService().insertOrdenTrabajo(pedido);
	}

}
