package test.business.logica;

import java.util.List;

import business.exception.BusinessException;
import model.Categoria;
import model.Pedido;
import model.PosicionProducto;
import test.business.TestService;
import test.business.TestServiceImpl;

public class CargadorComponentes {
	TestService testService = new TestServiceImpl();

	public List<Categoria> cargarCategorias() {
		List<Categoria> listado = null;

		try {
			listado = testService.getListaCategorias();

		} catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;
	}

	public List<Categoria> cargarCategoriasSinProductos() {
		List<Categoria> listado = null;

		try {
			listado = testService.getListCategoriasSinProductos();

		} catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;
	}

	public List<PosicionProducto> cargarPosicionesSinProducto() {
		List<PosicionProducto> listado = null;

		try {
			listado = testService.getListPosicionesSinProducto();

		} catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;
	}
	
	public List<Pedido> cargarPedidosNoPagados(){
		List<Pedido> listado = null;
		try{
			listado = testService.getListPedidosNoPagados();
		}catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;
	}
	

}