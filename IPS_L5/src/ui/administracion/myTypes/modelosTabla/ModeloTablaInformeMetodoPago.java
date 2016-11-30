package ui.administracion.myTypes.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ui.administracion.myTypes.DatosInformeAlmacenero;
import ui.administracion.myTypes.DatosInformeMetodoPago;

public class ModeloTablaInformeMetodoPago extends AbstractModeloTablaNoEditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4544687221625398441L;
	
	
	public List<DatosInformeMetodoPago> informe = new ArrayList<DatosInformeMetodoPago>();
	
	
	
	
	
	public ModeloTablaInformeMetodoPago() {
		super();

		asignarNombresColumnas();
		asignarTiposColumnas();
	}

	private void asignarNombresColumnas() {
		nombreColumnas.clear();

		nombreColumnas.add("Metodo de pago");
	}

	private void asignarTiposColumnas() {
		tipoColumnas.clear();

		tipoColumnas.add(String.class);
	}
	
	
	
	
	
	@Override
	public int getRowCount() {
		return informe.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		DatosInformeMetodoPago datosPago = informe.get(fila);
		
		if(columna == 0){
			return datosPago.getMetodo();
		}else if (columna > 0){
			return datosPago.getInfoFecha(columna -1).get("valor")+"";
		}
		else{
			return null;
		}
		
	}

	@Override
	public void reiniciar() {
		// ----------------------
				// Reiniciar datos
				// ----------------------

				informe.clear();

				fireTableDataChanged(); // Cambiaron los datos

				// -----------------------
				// Reiniciar columnas
				// -----------------------

				tipoColumnas.clear();

				asignarNombresColumnas();
				asignarTiposColumnas();

				fireTableStructureChanged(); // Cambiaron las columnas
	}
	
	
	public void addDatosPedido(DatosInformeMetodoPago datosPago) {
		informe.add(datosPago);

		fireTableDataChanged();
	}
	
	
	public void addFechasTabla(List<Map<String, Object>> datospago) {
		for (Map<String, Object> info : datospago) {
			addFecha((Date) info.get("fecha"));
		}

		fireTableStructureChanged();
	}
	
	
	private void addFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		nombreColumnas.add(sdf.format(fecha));
		tipoColumnas.add(String.class);
	}

}
