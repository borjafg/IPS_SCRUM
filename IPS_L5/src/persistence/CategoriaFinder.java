package persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import model.Categoria;
import model.OrdenTrabajo;
import persistence.exception.MyPersistenceException;
import persistence.util.Jpa;

public class CategoriaFinder {

	public static List<Categoria> finAll(){
		return Jpa.getManager().createNamedQuery("Categoria.findAll", Categoria.class).getResultList();
	}
	
	public static List<Categoria> findCategoriasPadre(){
		return Jpa.getManager().createNamedQuery("Categoria.findCategoriasPadre", Categoria.class).getResultList();
		
	}
	
	
	
	/**
	 * Si la Categoria pasada no estaba sincronizada con la base de
	 * datos, este método devolverá un nuevo objeto Categoria que sí estará
	 * sincronizado con la base de datos
	 * 
	 * @param categoria
	 *            categoria que hay que buscar
	 * 
	 * @return copia de la categoria (sincronizada con la base de datos),
	 *         o el mismo objeto si ya estaba sincronizado
	 * 
	 * @throws MyPersistenceException
	 * 
	 */
	public static Categoria find(Categoria categoria) throws MyPersistenceException{
		try {
 
			return Jpa.getManager().find(Categoria.class, categoria.getId());
		}

		catch (NoResultException e) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("No se ha encontrado la categoria con id = ");
			sb.append(categoria.getId());
			
			throw new MyPersistenceException(sb.toString());
		}

		catch (PersistenceException e) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Ha ocurrido un problema al buscar la categoria con id = ");
			sb.append(categoria.getId());
			
			throw new MyPersistenceException(sb.toString());
		}
	}
		
}