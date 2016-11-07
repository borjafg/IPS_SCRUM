package business.impl.usuario;

import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import persistence.CategoriaFinder;

public class ListarCategoriasPadre implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<Categoria> listaCategoriasPadre = null;
		
		listaCategoriasPadre = CategoriaFinder.findCategoriasPadre();
		
		return listaCategoriasPadre;
	}

}
