package model;

public class Asociacion {

	/**
	 * Asocia un pedido con el cliente que lo pidió, y viceversa.
	 *
	 */
	public static class Pedir {
		public static void link(Cliente cliente, Pedido pedido) {
			pedido._setCliente(cliente);
			cliente._getPedidos().add(pedido);
		}
	}

	/**
	 * Asocia un producto con el pedido al que pertenece
	 * 
	 */
	public static class AsignarProducto_Pedido {
		public static void link(Pedido pedido, ProductoEnPedido productoPedido, Producto producto) {
			productoPedido._setPedido(pedido);
			productoPedido._setProducto(producto);

			pedido._getListaProductosPedidos().add(productoPedido);
			producto._getListaProductosPedidos().add(productoPedido);
		}
	}

	/**
	 * Asocia un producto de un pedido a una orden de trabajo
	 * 
	 */
	public static class AsignarProducto_OrdenTrabajo {
		public static void link(OrdenTrabajo ordenTrabajo, ProductoEnOrdenTrabajo productoOrdenTrabajo,
				ProductoEnPedido productoPedido) {
			productoOrdenTrabajo._setOrdenTrabajo(ordenTrabajo);
			productoOrdenTrabajo._setProducto(productoPedido);

			ordenTrabajo._getProductosOrdenTrabajo().add(productoOrdenTrabajo);
			productoPedido._getProductosEnOrdenTrabajo().add(productoOrdenTrabajo);
		}
	}

	/**
	 * Asocia una incidencia a una orden de trabajo
	 * 
	 */
	public static class RegistrarIncidencia {
		public static void link(OrdenTrabajo ordenTrabajo, Incidencia incidencia) {
			incidencia._setOrdenTrabajo(ordenTrabajo);
			ordenTrabajo._getIncidencias().add(incidencia);
		}
	}

	/**
	 * Asocia un producto de un pedido a un paquete
	 *
	 */
	public static class Empaquetar {
		public static void link(Paquete paquete, ProductoEnPedido productoPedido, ProductoEnPaquete productoPaquete) {
			productoPaquete._setPaquete(paquete);
			productoPaquete._setProductoPedido(productoPedido);

			paquete._getProductosPaquete().add(productoPaquete);
			productoPedido._getPaquetes().add(productoPaquete);
		}
	}
	
	/**
	 * Asocia la posición de un producto a un producto
	 *
	 */
	public static class Situar {
		public static void link(Producto producto, PosicionProducto posicion) {
			producto._setPosicion(posicion);
			posicion._setProducto(producto);
		}
	}
}