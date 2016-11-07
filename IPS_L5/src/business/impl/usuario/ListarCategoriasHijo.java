package business.impl.usuario;

import java.util.ArrayList;
import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import persistence.CategoriaFinder;
import persistence.exception.MyPersistenceException;

public class ListarCategoriasHijo implements Command {

	Categoria categoriaPadre;
	
	
	
	
	public ListarCategoriasHijo(Categoria categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}




	@Override
	public Object execute() throws BusinessException {
		List<Categoria> listaCategorias = CategoriaFinder.finAll();
		List<Categoria>listaCategoriasHijo = new ArrayList<Categoria>();
		try {
			Categoria categoriaPadre = CategoriaFinder.find(this.categoriaPadre);		
			for(Categoria cat: listaCategorias){
				if(cat.getCategoriaPadre() != null){
				if(cat.getCategoriaPadre().equals(categoriaPadre)){
					listaCategoriasHijo.add(cat);
				}}
			}		
		} catch (MyPersistenceException e) {
			e.printStackTrace();
		}//categoria padre sincronizada
		
		return listaCategoriasHijo;
	}

}
