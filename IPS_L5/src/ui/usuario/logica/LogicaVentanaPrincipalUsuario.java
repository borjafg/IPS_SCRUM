package ui.usuario.logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Categoria;
import model.Cliente;
import model.Producto;
import model.types.MetodosPago;
import model.types.TipoCliente;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class LogicaVentanaPrincipalUsuario {

	// Aqui voy añadiendo los productos y sus unidades
	private List<ModeloProductosPedidos> listaCesta = new ArrayList<ModeloProductosPedidos>();
	
	
	
	private Cliente clienteReg;

//	public DefaultListModel<Producto> getModeloListaProductos() {//le paso la categoria de cada producto
//		DefaultListModel<Producto> modeloListaProducto = new DefaultListModel<Producto>();
//
//		try {
//			List<Producto> listaProductos = ServiceFactory.getUserService().getListaProducto();
//
//			for (Producto producto : listaProductos) {
//				modeloListaProducto.addElement(producto);
//			}
//		} catch (BusinessException e) {
//			System.err.println(e.getMessage());
//		}
//
//		return modeloListaProducto;
//	}
	
	
	public List<Producto> getListaProductos(Categoria categoria){
		List<Producto> listaProductos = null;
		
		try {
			listaProductos = ServiceFactory.getUserService().getListaProducto(categoria);
		} catch (BusinessException e) {
			
			System.err.println(e.getMessage());
		}
		return listaProductos;
	}
	
	
	public DefaultListModel<Categoria> getModeloCategoriasPadre(){
		DefaultListModel<Categoria> modeloListaCategoriasPadre = new DefaultListModel<Categoria>();
		
		try{
			List<Categoria> listaCategoriasPadre = ServiceFactory.getUserService().getListaCategoriasPadre();
			for(Categoria categoriaP: listaCategoriasPadre){
				modeloListaCategoriasPadre.addElement(categoriaP);
			}
		}catch(BusinessException e){
			System.err.println(e.getMessage());
		}
		return modeloListaCategoriasPadre;
	}
	
	
	public List<Categoria> getListaCategoriasHijas(Categoria catPadre){
		List<Categoria> listaCategoriasHijo = null;
		try{
			listaCategoriasHijo = ServiceFactory.getUserService().getListaCategoriasHijo(catPadre);
		}catch(BusinessException e){
			System.err.println(e.getMessage());
		}

		return listaCategoriasHijo;
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
	
	
	public boolean comprobarQueNoHayProductosEnCategoria(Categoria cat){
		boolean hay = true;
		try {
			hay = ServiceFactory.getUserService().isProductoEnCategoria(cat);
		} catch (BusinessException e) {
			System.err.println(e.getMessage());
		}
		return hay;
	}
	
	
	
	
	

	/**
	 * Metodo que añade los productos que pide el usuario al conjunto de pedidos
	 * 
	 * 
	 * @param productoPedido
	 */
	public DefaultListModel<ModeloProductosPedidos> sumarProductoACesta(ModeloProductosPedidos productoPedido,
			DefaultListModel<ModeloProductosPedidos> modeloListaCesta) {

		if (listaCesta.isEmpty()) { // cuando esta vacia
			listaCesta.add(productoPedido);
			modeloListaCesta.addElement(productoPedido);
		} else {
			// true si contiene el elemento
			if (contieneProducto(productoPedido)) {
				getProductoPedido(productoPedido.getProducto().getId()).sumarUnidades(productoPedido.getUnidades());

				return cargarModeloListaCesta();
			} else {
				listaCesta.add(productoPedido);
				modeloListaCesta.addElement(productoPedido);
			}
		}

		return modeloListaCesta;
	}

	public boolean verificarResta(int unidades, ModeloProductosPedidos productoCesta) {
		return (productoCesta.getUnidades() - unidades >= 0) ? true : false;
	}

	public DefaultListModel<ModeloProductosPedidos> restarProductoCesta(int unidades,
			ModeloProductosPedidos productoCesta) {
		getProductoPedido(productoCesta.getProducto().getId()).restarUnidades(unidades);

		return cargarModeloListaCesta();
	}

	public DefaultListModel<ModeloProductosPedidos> EliminarProducto(int index) {
		listaCesta.remove(index);

		// si seleccionado, es que ya lo contiene
		return cargarModeloListaCesta();
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
			precio = precio + a.getPrecioTotalProducto();
		}

		return precio;
	}
	
	
	public void generarusuarioRegistrado() throws BusinessException{
		ServiceFactory.getUserService().cargarBaseUsuarioRegistrado(clienteReg,listaCesta);
	}

	public void generarTodo(String direccion, String nombre,MetodosPago metodoPago) throws BusinessException {
		ServiceFactory.getUserService().cargarBaseDeDatos(direccion, nombre, listaCesta,metodoPago);
	}

	public DefaultListModel<ModeloProductosPedidos> resetear() {
		listaCesta = new ArrayList<ModeloProductosPedidos>();

		return new DefaultListModel<ModeloProductosPedidos>();
	}
	
	
	public boolean isUsuarioEnBase(String nombre) throws BusinessException{
		return  ServiceFactory.getUserService().isUsuarioEnBase(nombre);
		
	}


	public TipoCliente iniciarSesion(String nombre) throws BusinessException {
		this.clienteReg = ServiceFactory.getUserService().getUsuarioEnBase(nombre);
		return clienteReg.getTipoCliente();
	}
	
	public void cerrarSesion(){
		this.clienteReg = null;
	}

}