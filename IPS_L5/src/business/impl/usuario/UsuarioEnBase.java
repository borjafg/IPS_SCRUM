package business.impl.usuario;

import business.exception.BusinessException;
import business.impl.util.Command;
import persistence.ClienteFinder;

public class UsuarioEnBase implements Command{
String login;
	
	public UsuarioEnBase(String nombre) {
		this.login = nombre;
	}
	
	
	@Override
	public Object execute() throws BusinessException {
		
		return ClienteFinder.findByLogIn(login);//Buscamos un cliente con ese nombre
		
		
		
	}
}
