package test.business.impl;

import java.util.List;

import org.hibernate.cfg.CreateKeySecondPass;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import persistence.CategoriaFinder;
import persistence.util.Jpa;

public class ListarCategorias implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<Categoria> listaCategorias = null;
		
		listaCategorias = Jpa.getManager().createQuery("select c from Categoria c where size(c.subcategorias) = 0",Categoria.class).getResultList();
		//select b from Book where size(b.authors) >= 2
		return listaCategorias;
	}

}
