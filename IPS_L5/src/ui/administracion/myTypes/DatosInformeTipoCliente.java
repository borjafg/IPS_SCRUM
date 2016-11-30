package ui.administracion.myTypes;

import java.util.List;
import java.util.Map;

import model.types.MetodosPago;
import model.types.TipoCliente;

public class DatosInformeTipoCliente {

	private TipoCliente tipoCliente;
	private List<Map<String,Object>> listaFechas;
	
	
	public DatosInformeTipoCliente(TipoCliente tipoCliente,List<Map<String,Object>> listaFechas ) {
		this.tipoCliente=tipoCliente;
		this.listaFechas = listaFechas;
	}
	
	
	
	
	public Map<String, Object> getInfoFecha(int pos) {
		return listaFechas.get(pos);
	}
	
	
	public TipoCliente getMetodo(){
		return tipoCliente;
	}
	
	public List<Map<String, Object>> getFechas() {
		return listaFechas;
	}
	
}
