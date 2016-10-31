package test.business;

import java.util.List;

import business.exception.BusinessException;
import business.impl.util.CommandExecutor;
import model.Categoria;
import model.PosicionProducto;
import test.business.impl.ListarCategorias;
import test.business.impl.ListarCategoriasSinProductos;
import test.business.impl.ListarPosicionesSinProducto;

@SuppressWarnings("unchecked")
public class TestServiceImpl implements TestService {

	private CommandExecutor executor = new CommandExecutor();
	
	
	@Override
	public List<Categoria> getListaCategorias() throws BusinessException {
		return (List<Categoria>)executor.execute(new ListarCategorias());
	}

	@Override
	public List<Categoria> getListCategoriasSinProductos() throws BusinessException {
		return (List<Categoria>)executor.execute(new ListarCategoriasSinProductos());
	}

	@Override
	public List<PosicionProducto> getListPosicionesSinProducto() throws BusinessException {
		return (List<PosicionProducto>)executor.execute(new ListarPosicionesSinProducto());
	}

}
