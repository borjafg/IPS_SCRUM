package test.script;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import business.impl.util.Command;
import model.Almacenero;
import model.Categoria;
import model.Cliente;
import model.PosicionProducto;
import model.Producto;
import model.types.EstanteriaProducto;
import model.types.Tarjeta;
import model.types.TipoCliente;
import model.types.TipoTarjeta;
import persistence.util.Jpa;

public class TestScript1 implements Command {

	@Override
	public Object execute() {
		Cliente cliente;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Tarjeta tarjeta;
		try {
		for (int i = 1; i < 8; i++) {
				
				tarjeta = new Tarjeta(32L +i, i+25*2, sdf.parse("28/6/2077"),TipoTarjeta.CRÉDITO);
			
			cliente = new Cliente();
			cliente.setDireccionCompleta("Direccion " + i);
			cliente.setNombre("Cliente" + i);
			cliente.setTipoCliente(TipoCliente.MINORISTA);
			cliente.setTarjeta(tarjeta);
			Jpa.getManager().persist(cliente);// vamos generando cada uno de los
												// clientes minoristas
		}

		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Se han creado clientes minoristas a cholón");
		// Creamos los almaceneros en la base de datos
		String[] nombre = { "Pepe", "Manolo", "Alfonso", "Paula" };

		Almacenero almacenero;
		for (String alm : nombre) {
			almacenero = new Almacenero();
			almacenero.setLogin(alm);
			Jpa.getManager().persist(almacenero);
		}

		System.out.println("Creados almaceneros a cholón");
		
		try {
		
		Cliente clientenormal;
		
		tarjeta = new Tarjeta(1L, 1254,sdf.parse("12/4/2084"),TipoTarjeta.CRÉDITO);		
		clientenormal = new Cliente();
		clientenormal.setNombre("Antonio José");
		clientenormal.setDireccionCompleta("Debajo de un puente");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		clientenormal.setTarjeta(tarjeta);
		Jpa.getManager().persist(clientenormal);

		tarjeta = new Tarjeta(33L,2033 ,sdf.parse("25/12/2033"),TipoTarjeta.DÉBITO);
		clientenormal = new Cliente();
		clientenormal.setNombre("Artyon");
		clientenormal.setDireccionCompleta("En el metro de Moscú");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		clientenormal.setTarjeta(tarjeta);
		Jpa.getManager().persist(clientenormal);

		
		tarjeta = new Tarjeta(5L, 10101,sdf.parse("27/9/3025"),TipoTarjeta.DÉBITO);
		clientenormal = new Cliente();
		clientenormal.setNombre("Jodorowsky");
		clientenormal.setDireccionCompleta("En sus mundos psicomágicos");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		clientenormal.setTarjeta(tarjeta);
		Jpa.getManager().persist(clientenormal);

		System.out.println("Creados clientes Minoristas");
		
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		
		Categoria categoriaNormal;
		categoriaNormal = new Categoria();
		categoriaNormal.setNombre("Consumibles");
		Jpa.getManager().persist(categoriaNormal);

		
		///

		Categoria subCategoria;
		// toxicos
		subCategoria = new Categoria(categoriaNormal);
		subCategoria.setNombre("Consumibles tóxicos");
		Jpa.getManager().persist(subCategoria);

		PosicionProducto posicionP = new PosicionProducto();
		posicionP.setAltura(12);
		posicionP.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
		posicionP.setPasillo(4);
		posicionP.setPosicionX(5);
		Jpa.getManager().persist(posicionP);

		Producto prod = new Producto(posicionP, subCategoria);
		prod.setDescripcion("SetaVen");
		prod.setNombre("Seta tóxica");
		prod.setPrecio(12);
		prod.setIva(12.0);
		prod.setPeso(1.0);
		prod.setVolumen(1.0);
		Jpa.getManager().persist(prod);

		///
		// sanos
		subCategoria = new Categoria(categoriaNormal);
		subCategoria.setNombre("Consumibles sanos");
		Jpa.getManager().persist(subCategoria);

		posicionP = new PosicionProducto();
		posicionP.setAltura(45);
		posicionP.setEstanteriaPoducto(EstanteriaProducto.IZQUIERDA);
		posicionP.setPasillo(9);
		posicionP.setPosicionX(1);
		Jpa.getManager().persist(posicionP);

		prod = new Producto(posicionP, subCategoria);
		prod.setDescripcion("ManSana");
		prod.setNombre("Manzana sana");
		prod.setPrecio(1);
		prod.setIva(10.0);
		prod.setPeso(2.0);
		prod.setVolumen(2.0);
		Jpa.getManager().persist(prod);

		System.out.println("Creadas categorías especiales");
		Jpa.getManager().flush();
		Jpa.getManager().clear();
		return null;
	}

}
