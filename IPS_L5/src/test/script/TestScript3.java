package test.script;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import business.exception.BusinessException;
import business.impl.util.Command;
import infrastructure.Log;
import model.Cliente;
import model.types.Tarjeta;
import model.types.TipoCliente;
import model.types.TipoTarjeta;
import persistence.util.Jpa;

public class TestScript3 implements Command {

	@Override
	public Object execute() throws BusinessException {
		
		
		Cliente cliente;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Tarjeta tarjeta;
		try {
		for (int i = 1; i < 8; i++) {
				
				tarjeta = new Tarjeta(32L +i, i+25*2, sdf.parse("28/6/2077"),TipoTarjeta.DÉBITO);
			
			cliente = new Cliente();
			cliente.setDireccionCompleta("Direccion " + i);
			cliente.setNombre("Particular" + i);
			cliente.setLogin("Particular" + i);
			cliente.setTipoCliente(TipoCliente.PARTICULAR);
			cliente.setTarjeta(tarjeta);
			Jpa.getManager().persist(cliente);// vamos generando cada uno de los
												// clientes minoristas
		}

		} catch (ParseException e) {
			Log.error("Fallo en la creación de clientes particulares", e);
		}
		Log.debug("Se han creado clientes particulares a cholón");
		
		
		return null;
	}

}
