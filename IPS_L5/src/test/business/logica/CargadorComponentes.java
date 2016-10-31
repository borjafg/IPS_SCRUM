package test.business.logica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import business.UserService;
import business.exception.BusinessException;
import model.Categoria;
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
	
	
	public List<Categoria> cargarCategoriasSinProductos(){
		List<Categoria> listado = null;

		try {
			listado = testService.getListaCategorias();

		} catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;
	}
	
	
	public List<PosicionProducto> cargarPosicionesSinProducto(){
		List<PosicionProducto> listado = null;

		try {
			listado = testService.getListPosicionesSinProducto();

		} catch (BusinessException e) {

			System.err.println(e.getMessage());
		}

		return listado;

	}

}
