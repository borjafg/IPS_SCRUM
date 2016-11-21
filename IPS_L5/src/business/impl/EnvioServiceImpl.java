package business.impl;

import java.util.List;

import business.EnvioService;
import business.exception.BusinessException;
import business.impl.envios.ObtenerListaPaquetesSinEnvio;
import business.impl.envios.ObtenerListaTransportistas;
import business.impl.util.CommandExecutor;
import model.Paquete;
import model.Transportista;

@SuppressWarnings("unchecked")
public class EnvioServiceImpl implements EnvioService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public List<Transportista> obtenerListaTransportistas() throws BusinessException {
		return (List<Transportista>) executor.execute(new ObtenerListaTransportistas());
	}

	public List<Paquete> obtenerListaPaquetesSinEnvio() throws BusinessException {
		return (List<Paquete>) executor.execute(new ObtenerListaPaquetesSinEnvio());
	}

}