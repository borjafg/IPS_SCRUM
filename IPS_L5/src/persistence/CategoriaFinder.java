package persistence;

import java.util.List;

import model.Categoria;
import persistence.util.Jpa;

public class CategoriaFinder {

	public static List<Categoria> finAll(){
		return Jpa.getManager().createNamedQuery("Categoria.findAll",Categoria.class).getResultList();
	}
	
	
}
