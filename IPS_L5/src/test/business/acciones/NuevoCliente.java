package test.business.acciones;

import javax.persistence.EntityManager;

import model.Cliente;
import model.types.MetodosPago;
import model.types.TipoCliente;
import test.business.TestAction;

public class NuevoCliente implements TestAction {
	
	Cliente clientenuevo;
	
	public NuevoCliente(String nombre,String direccion, TipoCliente tipo) {
		clientenuevo = new Cliente();
		clientenuevo.setDireccionCompleta(direccion);
		clientenuevo.setNombre(nombre);
		clientenuevo.setTipoCliente(tipo);
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
