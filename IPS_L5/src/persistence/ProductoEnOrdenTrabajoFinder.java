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
	
	public static ProductoEnOrdenTrabajo findByIds(long id_ordenTrabajo, long id_pedido, long id_producto) {
		TypedQuery<ProductoEnOrdenTrabajo> consulta = Jpa.getManager()
				.createQuery("select p from ProductoEnOrdenTrabajo p where p.ordenTrabajo.id = ?1 and p.productoPedido.pedido.id = ?2 and p.productoPedido.producto.id = ?3", ProductoEnOrdenTrabajo.class);
		
		return consulta.setParameter(1, id_ordenTrabajo).setParameter(2, id_pedido).setParameter(3, id_producto).getSingleResult();
	}
}