package test.script;

import business.exception.BusinessException;
import business.impl.util.CommandExecutor;

public class GeneradorTest {

	public static void main(String[] args) {

		// Creamos diferentes Clientes Minoristas
		CommandExecutor exe = new CommandExecutor();

		try {
			System.out.println("Se inicia el test categorias y productos");
			exe.execute(new TestScript2());
			System.out.println("--------------------");
			System.out.println("Se finaliza el test categorias y productos");

			System.out.println("--------------------");

			System.out.println("Se inicia el test almaceneros, clientes minoristas y productos especiales");
			exe.execute(new TestScript1());
			System.out.println("--------------------");
			System.out.println("Se finaliza el test almaceneros, clientes minoristas y productos especiales");

			return;
		} catch (BusinessException e) {
			System.err.println(e.getMessage());
		}

	}

}
