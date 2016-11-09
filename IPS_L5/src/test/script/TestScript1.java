package test.script;

import business.impl.util.Command;
import model.Almacenero;
import model.Categoria;
import model.Cliente;
import model.PosicionProducto;
import model.Producto;
import model.types.EstanteriaProducto;
import model.types.TipoCliente;
import persistence.util.Jpa;

public class TestScript1 implements Command {
	
	@Override
	public Object execute(){
		Cliente cliente;
		for (int i = 1; i < 8; i++) {
			cliente = new Cliente();
			cliente.setDireccionCompleta("Direccion " + i);
			cliente.setNombre("Cliente" + i);
			cliente.setTipoCliente(TipoCliente.MINORISTA);
			Jpa.getManager().persist(cliente);// vamos generando cada uno de los
												// clientes minoristas
		}

		Categoria categoriaPadre;
		Producto producto;
		PosicionProducto posicion;
		for (int i = 1; i <= 25; i++) {
			categoriaPadre = new Categoria();
			categoriaPadre.setNombre("Categoria " + i);
			Jpa.getManager().persist(categoriaPadre);
			Categoria subCategoria;
			for (int j = 1; j <= 10; j++) {
				subCategoria = new Categoria(categoriaPadre);
				subCategoria.setNombre("SubCategoria " + i + "-" + j);
				Jpa.getManager().persist(subCategoria);
				Categoria subSubCategoria;
				for (int k = 1; k <= 10; k++) {
					subSubCategoria = new Categoria(subCategoria);
					subSubCategoria.setNombre("subSubCategoria " + i + "-" + j + "-" + k);
					Jpa.getManager().persist(subSubCategoria);
					if (k == 2) {
						for (int z = 1; z <= 200; z++) {
							posicion = new PosicionProducto();
							posicion.setAltura(i);
							posicion.setPasillo(k);
							posicion.setPosicionX(j);
							posicion.setEstanteriaPoducto(EstanteriaProducto.IZQUIERDA);
							Jpa.getManager().persist(posicion);
							producto = new Producto(posicion, subSubCategoria);
							producto.setNombre("Producto " + i + "-" + j + "-" + k);
							producto.setDescripcion("Prod" + i + j + k);
							producto.setPrecio(z);
							Jpa.getManager().persist(producto);
						}
					} else {
						posicion = new PosicionProducto();
						posicion.setAltura(k);
						posicion.setPasillo(j);
						posicion.setPosicionX(i);
						posicion.setEstanteriaPoducto(EstanteriaProducto.DERECHA);
						Jpa.getManager().persist(posicion);
						producto = new Producto(posicion, subSubCategoria);
						producto.setNombre("Producto " + i + "-" + j + "-" + k);
						producto.setDescripcion("Prod" + i + j + k);
						producto.setPrecio(i + k);
						Jpa.getManager().persist(producto);
					}
				}
			}

		}//finaliza creacion de productos, posiciones y categorias anidadas
		
		
		
		//Creamos los almaceneros en la base de datos
		String []nombre = {"Pepe","Manolo","Alfonso","Paula"};
		
		
		
		Almacenero almacenero;
		for(String alm: nombre){
			almacenero = new Almacenero();
			almacenero.setLogin(alm);
			Jpa.getManager().persist(almacenero);
		}
		
		
		Cliente clientenormal;
		clientenormal = new Cliente();
		clientenormal.setNombre("Antonio José");
		clientenormal.setDireccionCompleta("Debajo de un puente");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		Jpa.getManager().persist(clientenormal);
		
		clientenormal = new Cliente();
		clientenormal.setNombre("Artyon");
		clientenormal.setDireccionCompleta("En el metro de Moscú");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		Jpa.getManager().persist(clientenormal);
		
		clientenormal = new Cliente();
		clientenormal.setNombre("Jodorowsky");
		clientenormal.setDireccionCompleta("En sus mundos psicomágicos");
		clientenormal.setTipoCliente(TipoCliente.MINORISTA);
		Jpa.getManager().persist(clientenormal);
		
		Categoria categoriaNormal;
		categoriaNormal = new Categoria();
		categoriaNormal.setNombre("Consumibles");
		Jpa.getManager().persist(categoriaNormal);
		
		///
		
		Categoria subCategoria;
		//toxicos
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
		Jpa.getManager().persist(prod);
		
		///
		//sanos
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
		Jpa.getManager().persist(prod);
		
		
		
		
		
		
		
		
		return null;
	}
	
}
