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
import model.types.TipoEnvio;
import persistence.ClienteFinder;
import persistence.ProductoFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;

public class CargarBaseUsuarioMinorista implements Command {
	
	
	private List<ModeloProductosPedidos> listaCesta;
	private Cliente cliente;
	private TipoEnvio tipoEnvio;
	
	public CargarBaseUsuarioMinorista(Cliente cliente, List<ModeloProductosPedidos> listaCesta,TipoEnvio tipoEnvio) {
		this.cliente = cliente;
		this.listaCesta = listaCesta;
		this.tipoEnvio = tipoEnvio;
	}
	
	@Override
	public Object execute() throws BusinessException {
		try {
			Cliente cliente = ClienteFinder.find(this.cliente);//cliente actualizado con la base de datos
			
			Pedido pedido = new Pedido(cliente);
			pedido.setDireccionCompleta(cliente.getDireccionCompleta());
			pedido.setFecha(new Date());
			pedido.setTipoEnvio(tipoEnvio);
			pedido.setMetodoPago(MetodosPago.FACTURA);
			pedido.setEstado(EstadoPedido.POSIBLE_ASOCIAR_OT);
			pedido.setPagado(PedidoPagado.SI);
			
			Jpa.getManager().persist(pedido);
			
			
			ProductoEnPedido prodPedido = null;
			Producto prod;
			
			for(ModeloProductosPedidos modelo : listaCesta){
				try {
					prod = ProductoFinder.findById(modelo.getProducto());
					prodPedido = new ProductoEnPedido(pedido, prod);
					prodPedido.setCantidad(modelo.getUnidades());
					
					Jpa.getManager().persist(prodPedido);
					//Jpa.getManager().merge(prodPedido);
				} catch (MyPersistenceException e) {

					e.printStackTrace();
				}
			}
			
			
		} catch (MyPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
