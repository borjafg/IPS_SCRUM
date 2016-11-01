package business.impl.usuario;

import java.util.Date;
import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.ProductoEnPedido;
import model.types.EstadoPedido;
import model.types.MetodosPago;
import model.types.PedidoPagado;
import persistence.util.Jpa;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class CargadorABaseDeDatos implements Command {

	private String direccion;
	private String nombre;
	private List<ModeloProductosPedidos> listaCesta;

	public CargadorABaseDeDatos(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta) {

		this.direccion = direccion;
		this.nombre = nombre;
		this.listaCesta = listaCesta;
	}

	@Override
	public Object execute() throws BusinessException {
		// ===================================
		// añado el cliente
		// ===================================

		Cliente cliente = new Cliente();

		cliente.setNombre(nombre);
		cliente.setDireccionCompleta(direccion);

		Jpa.getManager().persist(cliente);

		// ===================================
		// creo el pedido
		// ===================================

		Pedido pedido = new Pedido(cliente);

		pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);
		pedido.setPagado(PedidoPagado.SI); // <---- Cambiar por NO
		pedido.setDireccionCompleta(direccion);
		pedido.setFecha(new Date());
		pedido.setMetodoPago(MetodosPago.METALICO); // <--- Cambiar

		Jpa.getManager().persist(pedido);

		// ===================================
		// Asocio los productos del pedido
		// ===================================

		ProductoEnPedido prodPedido = null;
		Producto prod;

		for (ModeloProductosPedidos mod : listaCesta) {
			prod = Jpa.getManager().merge(mod.getProducto());
			
			prodPedido = new ProductoEnPedido(pedido, prod);
			prodPedido.setCantidad(mod.getUnidades());
			prodPedido.setCantidadAsociadaOT(0);
			
			Jpa.getManager().persist(prodPedido);
		}

		return null;
	}

	// public void execute(){
	// EntityManagerFactory factory =
	// Persistence.createEntityManagerFactory("base_datos");
	// EntityManager manager = factory.createEntityManager();
	// manager.getTransaction().begin();
	//
	// //añado el cliente
	// Cliente cliente = new Cliente();
	// cliente.setMetodoPago(pago);
	// cliente.setNombre(nombre);
	// cliente.setDireccionCompleta(direccion);
	// manager.persist(cliente);
	//
	//
	// Pedido pedido = new Pedido(cliente);
	// pedido.setDireccionCompleta(direccion);
	// pedido.setFecha(new Date());
	// manager.persist(pedido);
	//
	//
	// ProductoEnPedido prodPedido =null;
	//
	// for(ModeloProductosPedidos mod : listaCesta){
	//
	// prodPedido = new ProductoEnPedido(pedido, mod.getProducto());
	// prodPedido.setCantidad(mod.getUnidades());
	// manager.persist(prodPedido);
	// }
	//
	// manager.getTransaction().commit();
	// manager.close();
	// factory.close();
	//
	//
	// }

}