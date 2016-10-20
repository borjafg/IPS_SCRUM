package persistence;

import java.util.List;

import model.Producto;
import persistence.util.Jpa;

public class ProductoFinder {

	public static Producto findById(Long id) {
		return Jpa.getManager().find(Producto.class, id);
	}

	public static List<Producto> findAll() {
		return Jpa.getManager().createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}
}