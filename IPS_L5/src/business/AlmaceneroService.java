package business;

import java.util.List;

import business.exception.BusinessException;
import model.Almacenero;
import model.OrdenTrabajo;

public interface AlmaceneroService {

	public Almacenero login(String nombreUsuario) throws BusinessException;

	public List<OrdenTrabajo> ObtenerOrdenesTrabajoRetomar(Almacenero alm) throws BusinessException;
}