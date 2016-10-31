package test.business.acciones;

import javax.persistence.EntityManager;

import model.Categoria;
import test.business.TestAction;

public class NuevaCategoria implements TestAction{

	private Categoria categoria;
	
	
	public NuevaCategoria(String nombre, Categoria padre) {
		if(padre == null){
			categoria = new Categoria();//faltarian parametros??
		}else{
			categoria = new Categoria(padre);
		}
		categoria.setNombre(nombre);
		
	}
	
	
	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try{
			ent.persist(categoria);
			sb.append("Se ha creado la categoria correctamente \n\n");
		}catch(Exception ex){
			sb.append("\n Ha ocurrido un error \n");
		}
		return sb.toString();
	}

}
