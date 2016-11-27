package business;

import java.util.List;

import business.exception.BusinessException;
import model.Paquete;
import model.Transportista;

public interface EnvioService {

	public List<Transportista> obtenerListaTransportistas() throws BusinessException;

	public List<Paquete> obtenerListaPaquetesSinEnvio() throws BusinessException;

	public void cerrarEnvio(List<Paquete> paquetesEnvio, Transportista transportista) throws BusinessException;

}