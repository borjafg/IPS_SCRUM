package business.impl.usuario;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.Cliente;
import persistence.ClienteFinder;

public class HayUsuarioEnBase implements Command {

	
	String nombre;
	
	public HayUsuarioEnBase(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public Object execute() throws BusinessException {
		
		Cliente cli = ClienteFinder.findByNombre(nombre);//Buscamos un cliente con ese nombre
		
		if (cli== null){
			return false;
		}else{
			return true;
		}
		
	}

}
