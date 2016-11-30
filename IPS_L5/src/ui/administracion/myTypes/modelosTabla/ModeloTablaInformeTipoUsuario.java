package ui.administracion.myTypes.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ui.administracion.myTypes.DatosInformeMetodoPago;
import ui.administracion.myTypes.DatosInformeTipoCliente;

public class ModeloTablaInformeTipoUsuario extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -7735289780289550830L;

	public List<DatosInformeTipoCliente> informe = new ArrayList<DatosInformeTipoCliente>();

	public ModeloTablaInformeTipoUsuario() {
		super();

		asignarNombresColumnas();
		asignarTiposColumnas();
	}

	private void asignarNombresColumnas() {
		nombreColumnas.clear();

		nombreColumnas.add("Tipo Cliente");
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
		DatosInformeTipoCliente datosCliente = informe.get(fila);
		
		if(columna == 0){
			return datosCliente.getMetodo();
		}else if (columna > 0){
			return datosCliente.getInfoFecha(columna -1).get("valor")+"";
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
	
	public void addDatosPedido(DatosInformeTipoCliente datosCliente) {
		informe.add(datosCliente);

		fireTableDataChanged();
	}

	public void addFechasTabla(List<Map<String, Object>> datosCliente) {
		for (Map<String, Object> info : datosCliente) {
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
