package test.script;

import infrastructure.Log;
import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import model.PosicionProducto;
import model.Producto;
import model.types.EstanteriaProducto;
import persistence.util.Jpa;

public class TestScript2 implements Command {

	@Override
	public Object execute() throws BusinessException {

		Categoria categoriaPadre;
		Producto producto;
		PosicionProducto posicion;
		Categoria subSubCategoria;
		Categoria subCategoria;
		for (int i = 1; i <= 25; i++) {
			categoriaPadre = new Categoria();
			categoriaPadre.setNombre("Categoria " + i);
			Jpa.getManager().persist(categoriaPadre);

			if (i == 2) {
				for (int j = 1; j <= 10; j++) {
					subCategoria = new Categoria(categoriaPadre);
					subCategoria.setNombre("SubCategoria " + i + "-" + j);
					Jpa.getManager().persist(subCategoria);
					if (j == 2) {

						for (int k = 1; k <= 10; k++) {
							subSubCategoria = new Categoria(subCategoria);
							subSubCategoria.setNombre("subSubCategoria " + i + "-" + j+"-"+k);
							Jpa.getManager().persist(subSubCategoria);
							if (k == 2) {// creo 200 nieto
								for (int z = 1; z <= 200; z++) {
									posicion = new PosicionProducto();
									posicion.setAltura(i);
									posicion.setPasillo(k);
									posicion.setPosicionX(j);
									posicion.setEstanteriaPoducto(EstanteriaProducto.IZQUIERDA);
									Jpa.getManager().persist(posicion);
									producto = new Producto(posicion, subSubCategoria);
									producto.setNombre("Producto " + i + "-" + j + "-" + k+"-"+z);
									producto.setDescripcion("Prod" + i + j + k);
									producto.setPrecio(z);
									producto.setIva(10.0);
									producto.setPeso(j + k);
									producto.setVolumen(i + j);
									Jpa.getManager().persist(producto);
								}

							} else {// creo producto categoria nieto

								posicion = new PosicionProducto();
								posicion.setAltura(k);
								posicion.setPasillo(j);
								posicion.setPosicionX(i);
								posicion.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
								Jpa.getManager().persist(posicion);
								producto = new Producto(posicion, subSubCategoria);
								producto.setNombre("Producto " + i + "-" + j + "-" + k);
								producto.setDescripcion("Prod" + i + j + k);
								producto.setPrecio(i + k);
								producto.setIva(12.0);
								producto.setPeso(i + j);
								producto.setVolumen(j + k);
								Jpa.getManager().persist(producto);
							}

						}

					} else {// creo producto categoria hijo
						posicion = new PosicionProducto();
						posicion.setAltura(i);
						posicion.setPasillo(j);
						posicion.setPosicionX(j);
						posicion.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
						Jpa.getManager().persist(posicion);
						producto = new Producto(posicion, subCategoria);
						producto.setNombre("Producto " + i + "-" + j);
						producto.setDescripcion("Prod" + i + "-" + j);
						producto.setPrecio(i + j);
						producto.setIva(11.0);
						producto.setPeso(j);
						producto.setVolumen(j);
						Jpa.getManager().persist(producto);

					}

				}

			} else {// creo producto categoria padre
				posicion = new PosicionProducto();
				posicion.setAltura(i);
				posicion.setPasillo(i);
				posicion.setPosicionX(i);
				posicion.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
				Jpa.getManager().persist(posicion);
				producto = new Producto(posicion, categoriaPadre);
				producto.setNombre("Producto " + i);
				producto.setDescripcion("Prod" + i);
				producto.setPrecio(i);
				producto.setIva(15.0);
				producto.setPeso(i);
				producto.setVolumen(i);
				Jpa.getManager().persist(producto);
			}
			Log.debug("iteracion " + i + " de categorias padre");
		}

		Jpa.getManager().flush();
		Jpa.getManager().clear();
		Log.debug("Se ha realizado un flush");
		
		return null;
	}

}
