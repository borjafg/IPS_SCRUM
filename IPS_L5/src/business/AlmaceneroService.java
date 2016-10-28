package business;

import business.exception.BusinessException;
import model.Almacenero;

public interface AlmaceneroService {
	
	public Almacenero login(String nombreUsuario) throws BusinessException;
}