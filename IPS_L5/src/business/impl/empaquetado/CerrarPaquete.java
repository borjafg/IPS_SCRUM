package business.impl.empaquetado;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Paquete;
import model.types.EstadoPaquete;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;

public class CerrarPaquete implements Command {

	private Paquete paquete;

	public CerrarPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Paquete paq = PaqueteFinder.find(paquete);

			if (paq.getEstado().equals(EstadoPaquete.CERRADO)) {
				throw new BusinessException("No se puede cerrar un paquete que ya está cerrado");
			}
			
			else if (paq.getProductosPaquete().size() == 0){
				throw new BusinessException("No se puede cerrar un paquete vacío");
			}
			
			else {
				paq.setEstado(EstadoPaquete.CERRADO);
				paq.setDestinatario(paq.getPedido().getCliente().getNombre());
				paq.setDireccionCompleta(paq.getPedido().getDireccionCompleta());
			}

			return null;
		}

		catch (MyPersistenceException e) {
			throw new BusinessException(e);
		}
	}

}