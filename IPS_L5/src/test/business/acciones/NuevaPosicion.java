package test.business.acciones;

import javax.persistence.EntityManager;

import model.PosicionProducto;
import model.types.EstanteriaProducto;
import test.business.TestAction;

public class NuevaPosicion implements TestAction{
	PosicionProducto posicion;
	
	public NuevaPosicion(int altura, int pasillo, int posicionX, EstanteriaProducto estanteria) {
		posicion = new PosicionProducto();//faltan parametros
	}
	
	
	
	
	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try{
			ent.persist(posicion);
			sb.append("Se ha creado la posicion correctamente \n\n");
		}catch(Exception ex){
			sb.append("\n Ha ocurrido un error \n");
		}
		return sb.toString();
	}

}
