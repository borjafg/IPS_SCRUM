package business;

import java.util.List;

import business.exception.BusinessException;
import model.Transportista;

public interface EnvioService {

	public List<Transportista> obtenerListaTransportistas() throws BusinessException;

}