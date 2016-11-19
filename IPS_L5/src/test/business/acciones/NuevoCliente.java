package test.business.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;

import model.Cliente;
import model.types.Tarjeta;
import model.types.TipoCliente;
import model.types.TipoTarjeta;
import test.business.TestAction;

public class NuevoCliente implements TestAction {

	private Cliente clientenuevo;
	private Tarjeta tarjeta;

	public NuevoCliente(String nombre, String direccion, TipoCliente tipo, long numeroTarjeta, int codigo,
			TipoTarjeta tipoTarjeta, String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			tarjeta = new Tarjeta(numeroTarjeta, codigo, sdf.parse(fecha), tipoTarjeta);

			clientenuevo = new Cliente();
			clientenuevo.setDireccionCompleta(direccion);
			clientenuevo.setNombre(nombre);
			clientenuevo.setTipoCliente(tipo);
			clientenuevo.setTarjeta(tarjeta);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public String doTest(EntityManager ent) {
		StringBuilder sb = new StringBuilder();

		try {
			ent.persist(tarjeta);
			sb.append("Se ha creado la tarjeta del cliente correctamente \n\n");
			ent.persist(clientenuevo);
			sb.append("Se ha creado el cliente correctamente \n\n");
		} catch (Exception ex) {
			sb.append("\n Ha ocurrido un error \n");
		}

		return sb.toString();
	}

}
