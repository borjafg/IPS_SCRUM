package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Paquete;
import model.ProductoEnOrdenTrabajo;
import persistence.util.Jpa;

public class AsignarProductoPaquete implements Command {

	private ProductoEnOrdenTrabajo producto;
	private Paquete paquete;

	public AsignarProductoPaquete(ProductoEnOrdenTrabajo producto, Paquete paquete) {
		this.producto = producto;
		this.paquete = paquete;
	}

	@Override
	public Object execute() throws BusinessException {
		// Sincronizar el paquete y el producto con la base de datos
		
		ProductoEnOrdenTrabajo prot = Jpa.getManager().merge(producto);

		// Si se acaba de crear el paquete
		
		if(paquete.getDestinatario() == null || paquete.getDireccionCompleta() == null) {
			paquete.setDestinatario(prot.getproductoPedido().getPedido().getCliente().getNombre());
			paquete.setDireccionCompleta(prot.getproductoPedido().getPedido().getDireccionCompleta());
			
//			Jpa.getManager().persist(paquete);
//			
//			// Cuando el paquete ya tiene los datos apropiados
//			
//			Jpa.getManager().persist(new ProductoEnPaquete(prot.getproductoPedido(), paquete));
//			return null;
		}
//		
//		// Si ya se había creado el paquete
//		
//		Paquete paq = PaqueteFinder.findById(paquete.getId());
//		
//		Jpa.getManager().persist(new ProductoEnPaquete(prot.getproductoPedido(), paq));

		return null;
	}

}