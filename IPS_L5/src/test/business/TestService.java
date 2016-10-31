package test.business;

import java.util.List;

import business.exception.BusinessException;
import model.Categoria;
import model.PosicionProducto;

public interface TestService {
	public List<Categoria> getListaCategorias() throws BusinessException;

	public List<Categoria> getListCategoriasSinProductos() throws BusinessException;

	public List<PosicionProducto> getListPosicionesSinProducto() throws BusinessException;
}
