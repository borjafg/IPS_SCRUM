package business.impl.usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import infrastructure.Log;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.ProductoEnPedido;
import model.types.EstadoPedido;
import model.types.MetodosPago;
import model.types.PedidoPagado;
import model.types.TipoEnvio;
import model.types.TipoTarjeta;
import persistence.ClienteFinder;
import persistence.ProductoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class CargarABaseDeDatosParticular implements Command {

	private Cliente cliente;
	private String direccion;
	private String nombre;
	private TipoEnvio tipoEnvio;
	private Long numeroTarjeta;
	private int codigoSec;
	private TipoTarjeta tipoTarjeta;
	private Date fecha;
	private List<ModeloProductosPedidos> listaCesta;
	private MetodosPago metodoPago;

	public CargarABaseDeDatosParticular(Cliente cliente, String direccion, String nombre,
			List<ModeloProductosPedidos> listaCesta, MetodosPago metodoPago, TipoEnvio tipoEnvio, Long numeroTarjeta,
			int codigoSec, TipoTarjeta tipoTarjeta, String fecha) {

		this.cliente = cliente;
		this.direccion = direccion;
		this.nombre = nombre;
		this.tipoEnvio = tipoEnvio;
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSec = codigoSec;
		this.tipoTarjeta = tipoTarjeta;
		this.listaCesta = listaCesta;
		this.metodoPago = metodoPago;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			this.fecha = sdf.parse(fecha);
		}

		catch (ParseException e) {
			Log.error("No se ha podido procesar la fecha de caducidad de una tarjeta de crédito del cliente con id = "
					+ cliente.getId(), e);
		}
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Cliente cli = ClienteFinder.find(cliente);

			cli.setNombre(nombre);
			cli.setDireccionCompleta(direccion);
			cli.getTarjeta().setCodigoSeguridad(codigoSec);
			cli.getTarjeta().setFechaCaducidad(fecha);
			cli.getTarjeta().setNumeroTarjeta(numeroTarjeta);
			cli.getTarjeta().setTipoTarjeta(tipoTarjeta);

			// cliente enlazado con la base de datos, no hace falta usar merge

			// Jpa.getManager().merge(cli); //persistimos los cambios en el
			// cliente

			Pedido pedido = new Pedido(cli);

			pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);
			pedido.setMetodoPago(metodoPago);

			if (metodoPago.equals(MetodosPago.TRANSFERENCIA)) {
				pedido.setPagado(PedidoPagado.NO);
			}

			else {
				pedido.setPagado(PedidoPagado.SI);
			}

			pedido.setDireccionCompleta(direccion);
			pedido.setFecha(new Date());
			pedido.setTipoEnvio(tipoEnvio);
			pedido.setDestinatario(nombre);

			Jpa.getManager().persist(pedido);

			ProductoEnPedido prodPedido = null;
			Producto prod;

			for (ModeloProductosPedidos modelo : listaCesta) {
				try {
					prod = ProductoFinder.findById(modelo.getProducto());

					prodPedido = new ProductoEnPedido(pedido, prod);
					prodPedido.setCantidad(modelo.getUnidades());

					Jpa.getManager().persist(prodPedido);
				}

				catch (MyPersistenceException e) {
					Log.error("Ha ocurrido un error al persistir el pedido de un particular", e);
				}
			}

		}

		catch (MyPersistenceException e) {
			Log.error("Ha ocurrido un error al actualizar los datos de un particular", e);
		}

		return null;
	}

}
