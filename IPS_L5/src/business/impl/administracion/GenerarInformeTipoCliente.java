package business.impl.administracion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.administracion.util.Cloner;
import business.impl.administracion.util.DateUtil;
import business.impl.util.Command;
import model.types.TipoCliente;
import persistence.PedidoFinder;
import persistence.exception.MyPersistenceException;
import ui.administracion.myTypes.DatosInformeTipoCliente;

public class GenerarInformeTipoCliente implements Command {

	private List<DatosInformeTipoCliente> informe;
	private List<Map<String, Object>> plantillaFechas;

	@Override
	public Object execute() throws BusinessException {
		try {

			inicializarInforme(PedidoFinder.findPedido_MasAntiguo(), new Date());

			List<Map<String, Object>> plantillaCompleta;

			List<TipoCliente> clientes = Arrays.asList(TipoCliente.MINORISTA, TipoCliente.PARTICULAR);

			for (TipoCliente tc : clientes) {
				List<Object[]> info = PedidoFinder.findNumPedidoDia_TipoCliente(tc);

				plantillaCompleta = rellenarPlantilla(info);

				informe.add(new DatosInformeTipoCliente(tc, plantillaCompleta));
			}

			return informe;
		} catch (MyPersistenceException | PersistenceException pe) {
			throw new BusinessException("Ha ocurrido un error al buscar pedidos", pe);
		}
	}

	private void inicializarInforme(Date fechaIni, Date fechaFin) throws BusinessException {
		if (fechaIni == null) {
			throw new BusinessException("No hay ningun pedido comprado");
		}

		// -------------------------------------------------------------------
		// Las fechas deben tener solo la informacion del día, mes, año
		// -------------------------------------------------------------------

		Date fechaInicial = DateUtil.truncarHastaDia(fechaIni);
		Date fechaFinal = DateUtil.truncarHastaDia(fechaFin);

		// --------------------------------------------------------------------
		// Inicializar informe y plantilla de fechas
		// --------------------------------------------------------------------

		informe = new ArrayList<DatosInformeTipoCliente>();
		plantillaFechas = new ArrayList<Map<String, Object>>();

		// --------------------------------------------------------------------
		// Cargar plantilla de fechas
		// --------------------------------------------------------------------

		Map<String, Object> infoFecha;
		Date fechaAux = DateUtil.clonarFecha(fechaInicial);

		while (!fechaAux.after(fechaFinal)) {
			infoFecha = new HashMap<String, Object>();

			infoFecha.put("fecha", fechaAux);
			infoFecha.put("valor", 0);

			plantillaFechas.add(infoFecha);

			fechaAux = DateUtil.sumarDia(fechaAux);
		}
	}

	public List<Map<String, Object>> rellenarPlantilla(List<Object[]> infoPedido) {
		// ====================================================
		// Copiar la plantilla de fechas
		// ====================================================

		List<Map<String, Object>> plantillaCompleta = Cloner.clonarPlantilla(plantillaFechas);

		// Si el almacenero no recogio ninguna orden de trabajo

		if (infoPedido.isEmpty()) {
			return plantillaCompleta;
		}

		// ====================================================
		// Rellenar la plantilla de fechas
		// ====================================================

		int indice = 0;

		Object[] values = infoPedido.get(indice);
		Date fecha = DateUtil.getDate((int) values[0], (int) values[1], (int) values[2]);

		for (Map<String, Object> fechaPlantilla : plantillaCompleta) {

			// -----------------------------------------------
			// Si la fecha coincide con la de la plantilla
			// -----------------------------------------------

			if (DateUtil.mismoDia((Date) fechaPlantilla.get("fecha"), fecha)) {
				fechaPlantilla.put("valor", values[3]);

				// -----------------------------------------------
				// Pasar a evaluar la siguiente fecha
				// -----------------------------------------------

				indice++;

				if (indice < infoPedido.size()) {
					values = infoPedido.get(indice);

					fecha = DateUtil.getDate((int) values[0], (int) values[1], (int) values[2]);
				}

				else { // No quedan fechas que evaluar
					break;
				}
			}
		}

		// ====================================================
		// Devolver la plantilla rellenada
		// ====================================================

		return plantillaCompleta;
	}

}// fin clase
