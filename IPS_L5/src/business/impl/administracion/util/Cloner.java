package business.impl.administracion.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cloner {

	/**
	 * Devuelve una copia de la plantilla de fechas que se le pasa como
	 * parámetro.<br>
	 * <br>
	 * Cada Map de la lista tiene dos elementos:<br>
	 * -> 'fecha' (java.util.Date)<br>
	 * -> 'valor' (int)
	 * 
	 * @param listaClonar
	 *            plantilla que hay que clonar
	 * 
	 * @return lista clonada
	 * 
	 */

	public static List<Map<String, Object>> clonarPlantilla(List<Map<String, Object>> listaClonar) {
		List<Map<String, Object>> listaResultado = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();

		for (Map<String, Object> item : listaClonar) {
			map = new HashMap<String, Object>();

			map.put("fecha", DateUtil.clonarFecha((Date) item.get("fecha")));
			map.put("valor", item.get("valor"));

			listaResultado.add(map);
		}

		return listaResultado;
	}

}