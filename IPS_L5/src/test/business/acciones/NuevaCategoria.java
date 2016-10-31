package test.business.acciones;

import javax.persistence.EntityManager;

import model.Categoria;
import test.business.TestAction;

public class NuevaCategoria implements TestAction {

	private String nombreCategoria;
	private Categoria padre;

	public NuevaCategoria(String nombre, Categoria padre) {
		this.nombreCategoria = nombre;
		this.padre = padre;
	}

	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try {
			Categoria nuevaCat;
			
			if (padre == null) {
				nuevaCat = new Categoria();
			}
			
			else {
				Categoria catPadre = ent.merge(padre);
				nuevaCat = new Categoria(catPadre);
			}
			
			nuevaCat.setNombre(nombreCategoria);
			
			ent.persist(nuevaCat);

			sb.append("Se ha creado la categoria correctamente \n\n");
		} catch (Exception ex) {
			sb.append("\n Ha ocurrido un error \n");
		}
		
		return sb.toString();
	}

}