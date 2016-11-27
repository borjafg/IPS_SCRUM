package ui.administracion.myTypes.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ui.administracion.myTypes.DatosInformeAlmacenero;

public class ModeloTablaInformeAlmacenero extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -511765397209348465L;

	private final List<DatosInformeAlmacenero> informe = new ArrayList<DatosInformeAlmacenero>();

	// ==================================================
	// Inicializar el modelo
	// ==================================================

	public ModeloTablaInformeAlmacenero() {
		super();

		asignarNombresColumnas();
		asignarTiposColumnas();
	}

	private void asignarNombresColumnas() {
		nombreColumnas.clear();

		nombreColumnas.add("Almacenero");
	}

	private void asignarTiposColumnas() {
		tipoColumnas.clear();

		tipoColumnas.add(String.class);
	}

	// ===========================================================
	// Metodos que permiten conocer la información del panel
	// ===========================================================

	@Override
	public int getRowCount() {
		return informe.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		DatosInformeAlmacenero datosAlmacenero = informe.get(fila);

		if (columna == 0) {
			return datosAlmacenero.getAlmacenero().getLogin();
		}

		else if (columna > 0) {
			return datosAlmacenero.getInfoFecha(columna - 1).get("valor") + "";
		}

		else {
			return null;
		}
	}

	// ===========================================================
	// Metodos que permiten cambiar los datos del panel
	// ===========================================================

	/**
	 * Permite añadir datos sobre un almacenero al informe. Antes de empezar a
	 * añadir datos al modelo hay que haber actualizado las columnas de la
	 * tabla, mediante el metodo <code><i>addFecha(Date fecha)</i></code>.
	 * 
	 * @param datosAlmacenero
	 *            información sobre un almacenero
	 * 
	 */
	public void addDatosAlmacenero(DatosInformeAlmacenero datosAlmacenero) {
		informe.add(datosAlmacenero);

		fireTableDataChanged();
	}

	// ===========================================================
	// Metodos que permiten cambiar la estructura del panel
	// ===========================================================

	public void addFechasTabla(List<Map<String, Object>> datosAlmacenero) {
		for (Map<String, Object> info : datosAlmacenero) {
			addFecha((Date) info.get("fecha"));
		}

		fireTableStructureChanged();
	}

	/**
	 * Añade una columna a la tabla. Después de añadir todas las columnas hay
	 * que llamar al método <code><i>fireTableStructureChanged()</i></code>.
	 * 
	 * @param fecha
	 *            fecha de la que hay que mostrar información en la tabla.
	 * 
	 */
	private void addFecha(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		nombreColumnas.add(sdf.format(fecha));
		tipoColumnas.add(String.class);
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

}