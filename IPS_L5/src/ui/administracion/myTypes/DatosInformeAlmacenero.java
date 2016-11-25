package ui.administracion.myTypes;

import java.util.ArrayList;
import java.util.Date;
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
	
	public static List<Map<String, Object>> generarPlantilla(Date fechaIni, Date fechaFin){
		List<Map<String, Object>> listaDeFechas = new ArrayList<Map<String, Object>>();
		
		Date fechaAux = (Date) fechaIni.clone();
		
		//while(fechaAux.get)
		
		
		return listaDeFechas;
		
	}
	
	
}
