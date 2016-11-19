package test.script;



import business.exception.BusinessException;
import business.impl.util.CommandExecutor;
import persistence.util.Jpa;

public class GeneradorTest {

	public static void main(String[] args) {

		// Creamos diferentes Clientes Minoristas
		CommandExecutor exe = new CommandExecutor(); 
			
		try {
			System.out.println("Se inicia el test 1");
			exe.execute(new TestScript2());
			Jpa.getManager().close();
		
		
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
