package ui.almacen.recogida.logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.OrdenTrabajo;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.recogida.util.MyPosicionProducto;

/**
 * Clase que obtiene todos los productos de una orden y los ordena de forma que se minimice el recorrido del almacén.
 * 
 * @author Nacho 
 *
 */

public class TerminalOT {
	
	private OrdenTrabajo ordenTrabajo;
	private List<ProductoEnOrdenTrabajo> productos; 
	
	public TerminalOT(OrdenTrabajo ot) throws BusinessException {
		this.ordenTrabajo = ot;
		this.productos = new ArrayList<ProductoEnOrdenTrabajo>();
		
		obtenerOrden();
		ordenarProductos();
	}
	
	/**
	 * Devuelve todos los productos asociados a la orden de trabajo
	 * @return	lista de productos asociados a la orden de trabajo
	 */
	public List<ProductoEnOrdenTrabajo> getProductos() {
		return productos;
	}
	
	
	/**
	 * Devuelve la orden de trabajo actual
	 * @return	orden de trabajo actual
	 */
	public OrdenTrabajo getOrdenTrabajo() {
		return ordenTrabajo;
	} 
	
	
	private void ordenarProductos() throws BusinessException {
		
		List<MyPosicionProducto> listaPosiciones = new ArrayList<MyPosicionProducto>();
		
		for (int i = 0; i < this.productos.size(); i++) {
			listaPosiciones.add(new MyPosicionProducto(this.productos.get(i)));
		}
		

		 Collections.sort(listaPosiciones, new Comparator<MyPosicionProducto>() {
             @Override
             public int compare(MyPosicionProducto pos0, MyPosicionProducto pos1) {
                return pos0.compareTo(pos1);
             }
         });
		
		List<ProductoEnOrdenTrabajo> listaProductos = new ArrayList<ProductoEnOrdenTrabajo>();
		
		for (int i = 0; i < listaPosiciones.size(); i++) {
			listaProductos.add(listaPosiciones.get(i).getProducto());
		}
		
		this.productos =  listaProductos;
	}
	

	private void obtenerOrden() throws BusinessException { 
		this.productos = ServiceFactory.getRecogidaService().obtenerProductosPorOrdenTrabajo(this.ordenTrabajo);
	}

}
