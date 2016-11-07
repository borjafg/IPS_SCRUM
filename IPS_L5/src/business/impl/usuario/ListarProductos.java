package business.impl.usuario;


import java.util.ArrayList;
import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Categoria;
import model.Producto;
import persistence.CategoriaFinder;
import persistence.ProductoFinder;
import persistence.exception.MyPersistenceException;

public class ListarProductos implements Command {

	
	Categoria categoria;
		
	public ListarProductos(Categoria categoria) {
		this.categoria = categoria;
	}



	@Override
	public Object execute() throws BusinessException {
		List<Producto> listaProducto = new ArrayList<Producto>();
		
		
		try {
			Categoria cat = CategoriaFinder.find(categoria);//categoria actualizada con la base de datos
			for(Producto prod:ProductoFinder.findAll()	) {
				if(prod.getCategoria().equals(cat)){
					listaProducto.add(prod);
				}
			}
			
			
			
		} catch (MyPersistenceException e) {
			System.err.println(e.getMessage());
		}
		
		
					
		
		return listaProducto ;
		
	}
	
	

}
