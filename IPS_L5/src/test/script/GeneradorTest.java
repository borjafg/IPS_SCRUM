package test.script;



import business.exception.BusinessException;
import business.impl.util.CommandExecutor;
import model.Almacenero;
import model.Categoria;
import model.Cliente;
import model.PosicionProducto;
import model.Producto;
import model.types.EstanteriaProducto;
import model.types.TipoCliente;
import persistence.util.Jpa;

public class GeneradorTest {

	public static void main(String[] args) {

		// Creamos diferentes Clientes Minoristas
		CommandExecutor exe = new CommandExecutor(); 
			
		try {
			exe.execute(new TestScript1());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}
