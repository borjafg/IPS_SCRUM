package business.impl.usuario;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import persistence.CategoriaFinder;
import persistence.exception.MyPersistenceException;

public class HayProductosEnCategoria implements Command {

	Categoria categoria;
	
	public HayProductosEnCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Categoria cat = CategoriaFinder.find(categoria);
			if(cat.getProductos().isEmpty()){
				return false;
			}else{
				return true;
			}
		} catch (MyPersistenceException e) {
			System.err.println(e.getMessage());
		}
		return false;
		//CategoriaFinder.find(getListCategorias().getSelectedValue()).getProductos().isEmpty()
	}

}
