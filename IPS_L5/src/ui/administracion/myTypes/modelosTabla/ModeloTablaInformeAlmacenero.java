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
	// Metodos que permiten conocer la informaci�n del panel
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
	 * Permite a�adir datos sobre un almacenero al informe. Antes de empezar a
	 * a�adir datos al modelo hay que haber actualizado las columnas de la
	 * tabla, mediante el metodo <code><i>addFecha(Date fecha)</i></code>.
	 * 
	 * @param datosAlmacenero
	 *            informaci�n sobre un almacenero
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
	 * A�ade una columna a la tabla. Despu�s de a�adir todas las columnas hay
	 * que llamar al m�todo <code><i>fireTableStructureChanged()</i></code>.
	 * 
	 * @param fecha
	 *            fecha de la que hay que mostrar informaci�n en la tabla.
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