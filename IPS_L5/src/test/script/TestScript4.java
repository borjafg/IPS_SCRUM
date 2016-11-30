package test.script;

import business.exception.BusinessException;
import business.impl.util.Command;
import infrastructure.Log;
import model.Transportista;
import persistence.util.Jpa;

public class TestScript4 implements Command  {

	@Override
	public Object execute() throws BusinessException {
		Transportista trans = new Transportista("Javier");
		Jpa.getManager().persist(trans);
		trans = new Transportista("Manolito");
		Jpa.getManager().persist(trans);
		trans = new Transportista("Pepito");
		Jpa.getManager().persist(trans);
		trans = new Transportista("Kiko");
		Jpa.getManager().persist(trans);
		trans = new Transportista("Saúl");
		Jpa.getManager().persist(trans);
		trans = new Transportista("Jorge");
		Jpa.getManager().persist(trans);
		
		Log.debug("Se han creado transportistas");
		
		return null;
	}

}
