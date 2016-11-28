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
import model.types.Tarjeta;
import model.types.TipoCliente;
import model.types.TipoEnvio;
import persistence.ProductoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class CargadorABaseDeDatosNoRegistrado implements Command {

	private String direccion;
	private String nombre;
	private List<ModeloProductosPedidos> listaCesta;
	private MetodosPago metodoPago;
	private TipoEnvio tipoEnvio;
	private Tarjeta tarjeta = new Tarjeta();

	public CargadorABaseDeDatosNoRegistrado(String direccion, String nombre, List<ModeloProductosPedidos> listaCesta,
			MetodosPago metodoPago,TipoEnvio tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
		this.direccion = direccion;
		this.nombre = nombre;
		this.listaCesta = listaCesta;
		this.metodoPago = metodoPago;
	}

	@Override
	public Object execute() throws BusinessException {
		// ===================================
		// añado el cliente
		// ===================================

		Cliente cliente = new Cliente();

		cliente.setNombre(nombre);
		cliente.setDireccionCompleta(direccion);

		// Cambiar cuando se modifique el log in de la aplicación
		cliente.setTipoCliente(TipoCliente.PARTICULAR);
		cliente.setTarjeta(tarjeta);

		Jpa.getManager().persist(cliente);

		// ===================================
		// creo el pedido
		// ===================================

		Pedido pedido = new Pedido(cliente);

		pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);

		pedido.setMetodoPago(metodoPago); 
		pedido.setTipoEnvio(tipoEnvio);

		if (metodoPago.equals(MetodosPago.TRANSFERENCIA)) {
			pedido.setPagado(PedidoPagado.NO);
		} else {
			pedido.setPagado(PedidoPagado.SI);
		}

		// pedido.setPagado(PedidoPagado.SI); // <---- Cambiar por NO, cuando el
		// método de pago sea por transacción

		pedido.setDireccionCompleta(direccion);
		pedido.setFecha(new Date());

		Jpa.getManager().persist(pedido);

		// ===================================
		// Asocio los productos del pedido
		// ===================================

		ProductoEnPedido prodPedido = null;
		Producto prod;

		for (ModeloProductosPedidos mod : listaCesta) {

			try {
				prod = ProductoFinder.findById(mod.getProducto());
				prodPedido = new ProductoEnPedido(pedido, prod);
				prodPedido.setCantidad(mod.getUnidades());
				
				Jpa.getManager().persist(prodPedido);
			} catch (MyPersistenceException e) {

				e.printStackTrace();
			}
			// prod = Jpa.getManager().merge(mod.getProducto());

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