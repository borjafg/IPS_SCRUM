package test.script;

import infrastructure.Log;
import business.exception.BusinessException;
import business.impl.util.CommandExecutor;

public class GeneradorTest {

	public static void main(String[] args) {

		// Creamos diferentes Clientes Minoristas
		CommandExecutor exe = new CommandExecutor();

		try {
			Log.debug("Se inicia el test categorias y productos");
			exe.execute(new TestScript2());
			Log.debug("--------------------");
			Log.debug("Se finaliza el test categorias y productos");

			Log.debug("--------------------");

			Log.debug("Se inicia el test almaceneros, clientes minoristas y productos especiales");
			exe.execute(new TestScript1());
			Log.debug("--------------------");
			Log.debug("Se finaliza el test almaceneros, clientes minoristas y productos especiales");
			
			
			Log.debug("Se inicia el test clientes particulares");
			exe.execute(new TestScript3());
			Log.debug("--------------------");
			Log.debug("Se finaliza el test clientes particulares");
			
			return;
		} catch (BusinessException e) {
			Log.error("Fallo a la hora de ejecutar una prueba", e);
		}

	}

}
