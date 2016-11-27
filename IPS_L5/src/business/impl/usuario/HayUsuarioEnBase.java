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
		
		Cliente cli = ClienteFinder.findByLogIn(nombre);//<--nombreDeUsuario, hay que cambiar el find y la consulta
		
		if (cli== null){
			return false;
		}else{
			return true;
		}
		
	}

}
