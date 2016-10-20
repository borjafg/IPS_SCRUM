package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Cliente;
import model.Incidencia;
import model.OrdenTrabajo;
import model.Paquete;
import model.Pedido;
import model.PosicionProducto;
import model.Producto;
import model.ProductoEnOrdenTrabajo;
import model.ProductoEnPaquete;
import model.ProductoEnPedido;
import model.types.CategoriasProducto;
import model.types.EstanteriaProducto;
import persistence.PedidoFinder;
import persistence.ProductoFinder;
import persistence.util.Jpa;

public class ClasePruebas {

	public static void main(String[] args) throws InterruptedException {
		EntityManager em = Jpa.createEntityManager();

		EntityTransaction trx = em.getTransaction();
		trx.begin();

		try {

			EntityManager en = Jpa.getManager();

			// probarAccesoMapeador(en);
			// probarCreacionObjetosBaseDatos(en);
			añadirProducto(en);
			System.out.println("---- Sin errores ----");

			trx.commit();

		} catch (Exception excep) {
			excep.printStackTrace(System.out);
			trx.rollback();
		}
	}

	private static void añadirProducto(EntityManager en) {
		// Crear un producto

		PosicionProducto posProd = new PosicionProducto();

		posProd.setAltura(1);
		posProd.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
		posProd.setPasillo(1);
		posProd.setPosicionX(1);

		en.persist(posProd);

		Producto prod = new Producto(posProd);

		prod.setCategoria(CategoriasProducto.Ninguna);
		prod.setNombre("producto1");
		prod.setPrecio(11.0);

		en.persist(prod);

		// Crear un segundo producto

		PosicionProducto posProd2 = new PosicionProducto();

		posProd2.setAltura(2);
		posProd2.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
		posProd2.setPasillo(2);
		posProd2.setPosicionX(2);

		en.persist(posProd2);

		Producto prod2 = new Producto(posProd2);

		prod2.setCategoria(CategoriasProducto.Ninguna);
		prod2.setNombre("producto2");
		prod2.setPrecio(20.0);

		en.persist(prod2);

		// Crear un tercer producto

		PosicionProducto posProd3 = new PosicionProducto();

		posProd3.setAltura(3);
		posProd3.setEstanteriaPoducto(EstanteriaProducto.IZQUIERDA);
		posProd3.setPasillo(3);
		posProd3.setPosicionX(3);

		en.persist(posProd3);

		Producto prod3 = new Producto(posProd3);

		prod3.setCategoria(CategoriasProducto.Ninguna);
		prod3.setNombre("producto3");
		prod3.setPrecio(30.0);

		en.persist(prod3);
	}

	private static void probarAccesoMapeador(EntityManager en) {
		System.out.println("===========================");
		System.out
				.println("Probando acceso a las tablas de la base de datos\n");

		en.createQuery("select c from Cliente c", Cliente.class)
				.getResultList();
		System.out.println("---- Cliente correcto ----");

		en.createQuery("select i from Incidencia i", Incidencia.class)
				.getResultList();
		System.out.println("---- Incidencia correcto ----");

		en.createQuery("select o from OrdenTrabajo o", OrdenTrabajo.class)
				.getResultList();
		System.out.println("---- OrdenTrabajo correcto ----");

		en.createQuery("select p from Paquete p", Paquete.class)
				.getResultList();
		System.out.println("---- Paquete correcto ----");

		PedidoFinder.findAll();
		System.out.println("---- Pedido correcto ----");

		en.createQuery("select p from PosicionProducto p",
				PosicionProducto.class).getResultList();
		System.out.println("---- PosicionProducto correcto ----");

		ProductoFinder.findAll();
		System.out.println("---- Producto correcto ----");

		en.createQuery("select p from ProductoEnOrdenTrabajo p",
				ProductoEnOrdenTrabajo.class).getResultList();
		System.out.println("---- ProductoEnOrdenTrabajo correcto ----");

		en.createQuery("select p from ProductoEnPaquete p",
				ProductoEnPaquete.class).getResultList();
		System.out.println("---- ProductoEnPaquete correcto ----");

		en.createQuery("select p from ProductoEnPedido p",
				ProductoEnPedido.class).getResultList();
		System.out.println("---- ProductoEnPedido correcto ----");

		System.out.println();
		System.out.println("===========================");
		System.out.println();
	}

	private static void probarCreacionObjetosBaseDatos(EntityManager en) {

		OrdenTrabajo ordenTrabajo = en
				.createQuery("select p from OrdenTrabajo p where p.id = :name",
						OrdenTrabajo.class).setParameter("name", (long) 1)
				.getSingleResult();
		Pedido pedido = PedidoFinder.findById((long) 1);

		ProductoEnPedido pep = null;

		for (ProductoEnPedido pep3 : pedido.getListaProductosPedidos()) {
			pep = pep3;
		}

		ProductoEnOrdenTrabajo prop = new ProductoEnOrdenTrabajo(ordenTrabajo,
				pep, pep.getCantidad());

		en.persist(prop);
	}
}