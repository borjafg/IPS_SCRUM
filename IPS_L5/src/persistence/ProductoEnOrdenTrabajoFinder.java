package persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import model.ProductoEnOrdenTrabajo;
import persistence.util.Jpa;

public class ProductoEnOrdenTrabajoFinder {

	public static List<ProductoEnOrdenTrabajo> findByIdOrdenTrabajo(long id) {
		
		TypedQuery<ProductoEnOrdenTrabajo> consulta = Jpa.getManager()
				.createNamedQuery("ProductoEnOrdenTrabajo.findByOrdenTrabajo", ProductoEnOrdenTrabajo.class);
		
		return consulta.setParameter("id", id).getResultList();
	}
}