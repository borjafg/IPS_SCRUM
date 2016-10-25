package ui.usuario.logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import model.Producto;
import model.types.MetodosPago;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;
import business.UserService;
import business.exception.BusinessException;
import business.impl.UserServiceImpl;

public class LogicaVentanaPrincipalUsuario {

	private UserService userService;

	private List<ModeloProductosPedidos> listaCesta = new ArrayList<ModeloProductosPedidos>();// Aqui
																								// voy
																								// añadiendo
																								// los
																								// productos
																								// y
																								// sus
																								// unidades

	public DefaultListModel<Producto> getModeloListaProductos() {
		DefaultListModel<Producto> modeloListaProducto = new DefaultListModel<Producto>();
		userService = new UserServiceImpl();

		try {
			List<Producto> listaProductos = userService.getListaProducto();
			for (Producto producto : listaProductos) {
				modeloListaProducto.addElement(producto);
			}
		} catch (BusinessException e) {
			System.err.println(e.getMessage());
		}

		return modeloListaProducto;
	}

	private DefaultListModel<ModeloProductosPedidos> cargarModeloListaCesta() {
		DefaultListModel<ModeloProductosPedidos> modeloListaCesta = new DefaultListModel<ModeloProductosPedidos>();

		for (ModeloProductosPedidos c : listaCesta) {
			if (c.getUnidades() > 0) {
				modeloListaCesta.addElement(c);
			}
		}

		return modeloListaCesta;
	}

	public boolean isnumber(String texto) {
		for (char ch : texto.toCharArray()) {
			if (!Character.isDigit(ch)) {
				return false;
			}
		}

		return true;

	}

	/**
	 * Metodo que añade los productos que pide el usuario al conjunto de pedidos
	 * 
	 * 
	 * @param productoPedido
	 */
	public DefaultListModel<ModeloProductosPedidos> sumarProductoACesta(ModeloProductosPedidos productoPedido,DefaultListModel<ModeloProductosPedidos> modeloListaCesta) {
		if (listaCesta.isEmpty()) {// cuando esta vacia
			listaCesta.add(productoPedido);
			modeloListaCesta.addElement(productoPedido);
		} else {
			if (contieneProducto(productoPedido)) {// true si contiene al
													// elemento
				getProductoPedido(productoPedido.getProducto().getId()).sumarUnidades(productoPedido.getUnidades());
				return cargarModeloListaCesta();
			} else {
				listaCesta.add(productoPedido);
				modeloListaCesta.addElement(productoPedido);
			}
		}
		return modeloListaCesta;
	}

	public boolean verificarResta(int unidades,ModeloProductosPedidos productoCesta) {
		return (productoCesta.getUnidades() - unidades >= 0) ? true : false;
	}

	public DefaultListModel<ModeloProductosPedidos> restarProductoCesta(int unidades, ModeloProductosPedidos productoCesta) {
		getProductoPedido(productoCesta.getProducto().getId()).restarUnidades(
				unidades);

		return cargarModeloListaCesta();
	}
	
	
	public DefaultListModel<ModeloProductosPedidos> EliminarProducto(int index){
		listaCesta.remove(index);
		return cargarModeloListaCesta();
		//si seleccionado, es que ya lo contiene
		
	}

	private boolean contieneProducto(ModeloProductosPedidos productoPedido) {
		for (ModeloProductosPedidos a : listaCesta) {
			if (a.getProducto().getId() == productoPedido.getProducto().getId()) {
				return true;
			}
		}

		return false;
	}

	private ModeloProductosPedidos getProductoPedido(long id) {
		for (ModeloProductosPedidos a : listaCesta) {
			if (a.getProducto().getId() == id) {
				return a;
			}
		}

		return null;
	}

	public double calcularPrecioTotal() {
		double precio = 0.0;

		for (ModeloProductosPedidos a : listaCesta) {
			precio = precio + a.getPrecioProductosTotal();
		}

		return precio;
	}

	public void generarTodo(String direccion, MetodosPago pago, String nombre)
			throws BusinessException {
		userService = new UserServiceImpl();
		userService.cargarBaseDeDatos(direccion, pago, nombre, listaCesta);

	}

	public DefaultListModel<ModeloProductosPedidos> resetear() {
		listaCesta = new ArrayList<ModeloProductosPedidos>();

		return new DefaultListModel<ModeloProductosPedidos>();

	}

}