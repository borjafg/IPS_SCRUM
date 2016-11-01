package test.business.impl;

import java.util.List;

import business.exception.BusinessException;
import business.impl.util.Command;
import model.PosicionProducto;
import persistence.util.Jpa;

public class ListarPosicionesSinProducto implements Command {

	@Override
	public Object execute() throws BusinessException {
		List<PosicionProducto> listaPosicionProducto = null;
		
		listaPosicionProducto = Jpa.getManager().createQuery("select c from PosicionProducto c",PosicionProducto.class).getResultList();
		
		return listaPosicionProducto;
	}

}
