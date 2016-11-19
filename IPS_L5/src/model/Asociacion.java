package model;

public class Asociacion {

	// ------------------------------------------------
	// Productos y categorias
	// ------------------------------------------------

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

	/**
	 * Asocia un producto con su categoria
	 *
	 */
	public static class Categorizar {
		public static void link(Producto producto, Categoria categoria) {
			producto._setCategoria(categoria);
			categoria._getProductos().add(producto);
		}
	}

	/**
	 * Asocia una categoria con su subcategoria
	 * 
	 */
	public static class NuevaCategoria {
		public static void link(Categoria subcategoria, Categoria categoriaPadre) {
			subcategoria._setCategoriaPadre(categoriaPadre);
			categoriaPadre._getSubCategorias().add(subcategoria);
		}
	}

	// ------------------------------------------------
	// Pedidos y Productos en un pedido
	// ------------------------------------------------

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

	// ------------------------------------------------
	// Ordenes de Trabajo (creación e incidencias)
	// ------------------------------------------------

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

	// ------------------------------------------------
	// Ordenes de Trabajo (almaceneros)
	// ------------------------------------------------

	/**
	 * Asocia un almacenero con una orden de trabajo cuyos productos tiene que
	 * recoger
	 * 
	 */
	public static class AlmaceneroRecogerOrdenTrabajo {
		public static void link(OrdenTrabajo ordenTrabajo, Almacenero almacenero) {
			ordenTrabajo._setAlmaceneroRecoger(almacenero);
			almacenero._getOrdenesTrabajoRecoger().add(ordenTrabajo);
		}
	}

	/**
	 * Asocia un almacenero con una orden de trabajo cuyos productos tiene que
	 * empaquetar
	 * 
	 */
	public static class AlmaceneroEmpaquetarOrdenTrabajo {
		public static void link(OrdenTrabajo ordenTrabajo, Almacenero almacenero) {
			ordenTrabajo._setAlmaceneroEmpaquetar(almacenero);
			almacenero._getOrdenesTrabajoEmpaquetar().add(ordenTrabajo);
		}
	}

	// ------------------------------------------------
	// Ordenes de Trabajo (empaquetado)
	// ------------------------------------------------

	/**
	 * Asocia un producto de un pedido a un paquete
	 *
	 */
	public static class Empaquetar {
		public static void link(Paquete paquete, ProductoEnOrdenTrabajo productoOrdenTrabajo,
				ProductoEnPaquete productoPaquete) {

			productoPaquete._setPaquete(paquete);
			productoPaquete._setProductoOrdenTrabajo(productoOrdenTrabajo);

			paquete._getProductosPaquete().add(productoPaquete);
			productoOrdenTrabajo._getPaquetes().add(productoPaquete);
		}
	}

	/**
	 * Asocia un paquete con una orden de trabajo
	 *
	 */
	public static class NuevoPaquete_OrdenTrabajo {
		public static void link(Paquete paquete, OrdenTrabajo ordenTrabajo) {
			paquete._setOrdenTrabajo(ordenTrabajo);

			ordenTrabajo.getPaquetes().add(paquete);
		}
	}

	/**
	 * Asocia un envio con un transportista
	 * 
	 */
	public static class AsignarEnvio_Transportista {
		public static void link(Envio envio, Transportista transportista) {
			envio.setTransportista(transportista);
			transportista._getEnvios().add(envio);
		}
	}

	/**
	 * Asocia un paquete a un envio
	 * 
	 */
	public static void AsignarPaquete_Envio(Paquete paquete, Envio envio) {
		paquete._setEnvio(envio);
		envio._getPaquetesEnvio().add(paquete);
	}

}