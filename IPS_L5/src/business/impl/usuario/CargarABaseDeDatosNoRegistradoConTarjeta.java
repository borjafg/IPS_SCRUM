package business.impl.usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.types.TipoTarjeta;
import persistence.ProductoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class CargarABaseDeDatosNoRegistradoConTarjeta implements Command {

	private String direccion;
	private String nombre;
	private TipoEnvio tipoEnvio;
	private Long numeroTarjeta;
	private int codigoSec;
	private TipoTarjeta tipoTarjeta;
	private Date fecha;
	private List<ModeloProductosPedidos> listaCesta;

	public CargarABaseDeDatosNoRegistradoConTarjeta(String direccion, String nombre,
			List<ModeloProductosPedidos> listaCesta, TipoEnvio tipoEnvio, Long numeroTarjeta, int codigoSec,
			TipoTarjeta tipoTarjeta, String fecha) {
		this.direccion = direccion;
		this.nombre = nombre;
		this.tipoEnvio = tipoEnvio;
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSec = codigoSec;
		this.tipoTarjeta = tipoTarjeta;
		this.listaCesta = listaCesta;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.fecha = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		cliente.setTarjeta(new Tarjeta(numeroTarjeta, codigoSec, fecha, tipoTarjeta));

		Jpa.getManager().persist(cliente);

		// ===================================
		// creo el pedido
		// ===================================

		Pedido pedido = new Pedido(cliente);

		pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);

		pedido.setMetodoPago(MetodosPago.TARJETA); // <--- Cambiar

		// pedido.setPagado(PedidoPagado.SI); // <---- Cambiar por NO, cuando el
		// método de pago sea por transacción
		pedido.setPagado(PedidoPagado.SI);
		pedido.setDireccionCompleta(direccion);
		pedido.setFecha(new Date());
		pedido.setTipoEnvio(tipoEnvio);

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

}
