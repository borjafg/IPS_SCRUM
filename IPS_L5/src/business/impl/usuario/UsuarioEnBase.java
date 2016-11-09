package business.impl.usuario;

import business.exception.BusinessException;
import business.impl.util.Command;
import persistence.ClienteFinder;

public class UsuarioEnBase implements Command{
String nombre;
	
	public UsuarioEnBase(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public Object execute() throws BusinessException {
		
		return ClienteFinder.findByNombre(nombre);//Buscamos un cliente con ese nombre
		
		
		
	}
}
