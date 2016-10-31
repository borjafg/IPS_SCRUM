package test.business.impl;

import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import persistence.util.Jpa;

public class ListarCategoriasSinProductos implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<Categoria> listaCategorias = null;
		
		listaCategorias = Jpa.getManager().createQuery("select c from Categoria c where size(c.productos) = 0",Categoria.class).getResultList();
		
		return listaCategorias;
	}
	
	
	

}
