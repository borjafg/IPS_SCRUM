package ui.administracion.myTypes;

import java.util.List;
import java.util.Map;

import model.Almacenero;

public class DatosInformeAlmacenero {

	private Almacenero almacenero;
	private List<Map<String, Object>> listaFechas;

	public DatosInformeAlmacenero(Almacenero almacenero, List<Map<String, Object>> listaFechas) {
		this.almacenero = almacenero;
		this.listaFechas = listaFechas;
	}

	/**
	 * Obtiene informacion sobre una fecha concreta.
	 * 
	 * @param pos
	 *            fecha de la que se quiere obtener informacion
	 * 
	 * @return mapa con dos claves:<br>
	 *         - 'fecha' --> Date que indica de qué fecha se muestra info<br>
	 *         - 'valor' --> Integer con la info correspondiente
	 * 
	 */
	public Map<String, Object> getInfoFecha(int pos) {
		return listaFechas.get(pos);
	}

	public Almacenero getAlmacenero() {
		return almacenero;
	}

	/**
	 * Devuelve la informacion sobre todas las fechas del informe asociadas al
	 * almacenero.
	 * 
	 * @return lista de mapas con dos claves:<br>
	 *         - 'fecha' --> Date que indica de qué fecha se muestra info<br>
	 *         - 'valor' --> Integer con la info correspondiente
	 * 
	 */
	public List<Map<String, Object>> getFechas() {
		return listaFechas;
	}

}