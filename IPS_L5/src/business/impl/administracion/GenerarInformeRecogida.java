package business.impl.administracion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import business.exception.BusinessException;
import business.impl.administracion.util.Cloner;
import business.impl.administracion.util.DateUtil;
import business.impl.util.Command;
import model.Almacenero;
import persistence.AlmaceneroFinder;
import persistence.OrdenTrabajoFinder;
import persistence.exception.MyPersistenceException;
import ui.administracion.myTypes.DatosInformeAlmacenero;

public class GenerarInformeRecogida implements Command {

	private List<DatosInformeAlmacenero> informe;
	private List<Map<String, Object>> plantillaFechas;

	@Override
	public Object execute() throws BusinessException {
		try {
			inicializarInforme(OrdenTrabajoFinder.findOrdenTrabajoTerminadaRecoger_MasAntigua(), new Date());

			List<Map<String, Object>> plantillaCompleta;

			List<Almacenero> almaceneros = AlmaceneroFinder.findAll();

			for (Almacenero al : almaceneros) {
				List<Object[]> info = OrdenTrabajoFinder.findNumOrdenTrabajoRecogidasDia(al);

				plantillaCompleta = rellenarPlantilla(info);

				informe.add(new DatosInformeAlmacenero(al, plantillaCompleta));
			}

			return informe;
		}

		catch (MyPersistenceException | PersistenceException pe) {
			throw new BusinessException(
					"Ha ocurrido un error al buscar la información de las ordenes de trabajo retomadas", pe);
		}
	}

	/**
	 * Inicializa el informe y carga la lista de fechas que aparecerá en el
	 * informe.
	 * 
	 * @param fechaIni
	 *            primera fecha del informe
	 * 
	 * @param fechaFin
	 *            ultima fecha del informe
	 * 
	 * @throws BusinessException
	 *             no hay información en la base de datos para generar el
	 *             informe
	 * 
	 */
	private void inicializarInforme(Date fechaIni, Date fechaFin) throws BusinessException {
		if (fechaIni == null) {
			throw new BusinessException("No hay ninguna orden de trabajo que se haya terminado de recoger");
		}

		// -------------------------------------------------------------------
		// Las fechas deben tener solo la informacion del día, mes, año
		// -------------------------------------------------------------------

		Date fechaInicial = DateUtil.truncarHastaDia(fechaIni);
		Date fechaFinal = DateUtil.truncarHastaDia(fechaFin);

		// --------------------------------------------------------------------
		// Inicializar informe y plantilla de fechas
		// --------------------------------------------------------------------

		informe = new ArrayList<DatosInformeAlmacenero>();
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

	public List<Map<String, Object>> rellenarPlantilla(List<Object[]> infoAlmacenero) {
		// ====================================================
		// Copiar la plantilla de fechas
		// ====================================================

		List<Map<String, Object>> plantillaCompleta = Cloner.clonarPlantilla(plantillaFechas);

		// Si el almacenero no recogio ninguna orden de trabajo

		if (infoAlmacenero.isEmpty()) {
			return plantillaCompleta;
		}

		// ====================================================
		// Rellenar la plantilla de fechas
		// ====================================================

		int indice = 0;

		Object[] values = infoAlmacenero.get(indice);
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

				if (indice < infoAlmacenero.size()) {
					values = infoAlmacenero.get(indice);

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

}