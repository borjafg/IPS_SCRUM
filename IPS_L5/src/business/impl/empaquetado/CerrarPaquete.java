package business.impl.empaquetado;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.OrdenTrabajo;
import model.Paquete;
import model.types.EstadoPaquete;
import persistence.OrdenTrabajoFinder;
import persistence.PaqueteFinder;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class CerrarPaquete implements Command {

	private Paquete paquete;
	private OrdenTrabajo ordenTrabajo;

	public CerrarPaquete(Paquete paquete, OrdenTrabajo ordenTrabajo) {
		this.paquete = paquete;
		this.ordenTrabajo = ordenTrabajo;
	}

	@Override
	public Object execute() throws BusinessException {

		try {
			Paquete paq = PaqueteFinder.find(paquete);

			if (paq.getEstado().equals(EstadoPaquete.CERRADO)) {
				throw new BusinessException("No se puede cerrar un paquete que ya está cerrado");
			}

			else if (paq.getProductosPaquete().size() == 0) {
				throw new BusinessException("No se puede cerrar un paquete vacío");
			}

			else {
				// ===================================
				// Cerrar el paquete actual
				// ===================================

				paq.setEstado(EstadoPaquete.CERRADO);
				paq.setDestinatario(paq.getPedido().getCliente().getNombre());
				paq.setDireccionCompleta(paq.getPedido().getDireccionCompleta());

				// ===================================
				// Abrir un nuevo paquete
				// ===================================

				OrdenTrabajo ot = OrdenTrabajoFinder.find(ordenTrabajo);

				if (OrdenTrabajoFinder.findNumProductosFaltaEmpaquetar(ot) > 0) {
					Paquete paquete = new Paquete(ot);

					int numCaja = PaqueteFinder.findUltimoNumCaja(ot);
					numCaja++;

					paquete.setNumCaja(numCaja);

					Jpa.getManager().persist(paquete);

					return paquete;
				}
				
				else {
					return null;
				}
			}
		}

		catch (MyPersistenceException | PersistenceException e) {
			throw new BusinessException("Ha ocurrido un error al intentar cerrar el paquete", e);
		}
	}

}