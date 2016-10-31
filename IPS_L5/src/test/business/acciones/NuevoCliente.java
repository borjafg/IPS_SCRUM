package test.business.acciones;

import javax.persistence.EntityManager;

import model.Cliente;
import model.types.MetodosPago;
import model.types.TipoCliente;
import test.business.TestAction;

public class NuevoCliente implements TestAction {
	
	Cliente clientenuevo;
	
	public NuevoCliente(String Nombre,String direccion, MetodosPago metodoPago, TipoCliente tipo) {
		clientenuevo = new Cliente();//constructor tiene paramteros??
	}
	
	
	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try{
			ent.persist(clientenuevo);
			sb.append("Se ha creado el cliente correctamente \n\n");
		}catch(Exception ex){
			sb.append("\n Ha ocurrido un error \n");
		}
		return sb.toString();
	}

}
