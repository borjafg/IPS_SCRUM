package ui.administracion.myTypes;

import java.util.List;
import java.util.Map;

import model.types.MetodosPago;

public class DatosInformeMetodoPago {

	private MetodosPago metodoPago;
	private List<Map<String, Object>> listaFechas;

	public DatosInformeMetodoPago(MetodosPago metodoPago, List<Map<String, Object>> listaFechas) {
		this.listaFechas = listaFechas;
		this.metodoPago = metodoPago;
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

	public MetodosPago getMetodo() {
		return metodoPago;
	}

	/**
	 * Devuelve la informacion sobre todas las fechas del informe asociadas al
	 * pedido.
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
