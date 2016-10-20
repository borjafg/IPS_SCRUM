package ui.almacen.almacenero.modelosTabla;

import javax.swing.table.*;

/**
 * Para usar esta clase hay redefinir algunos m�todos m�s, as� como a�adir una
 * List<T> siendo T el tipo de objeto que tendr� el modelo. </br>
 * </br>
 * Este tipo de modelo de tablas no permitir� que se editen las celdas de la
 * tabla, y generar� los valores de cada fila a partir de los objetos del
 * modelo. </br>
 * </br>
 * La ventaja de esta versi�n es que se pueden recuperar los objetos del modelo
 * y acceder a sus atributos (aunque no fueran utilizados para generar las
 * filas). Ejemplo:</br>
 * </br>
 * - Clase Persona {nombre, edad, dni}</br>
 * - En la tabla s�lo se muestra el nombre y la edad</br>
 * </br>
 * Aunque en la tabla s�lo se muestren dos atributos, es posible acceder al dni
 * de esa persona, o incluso recuperar el objeto persona sin necesidad de volver
 * a generarlo (haciendo new Persona(...)).
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractModeloTablaNoEditable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected final Class[] tipoColumnas;
	protected final String[] nombreColumnas;

	public AbstractModeloTablaNoEditable(String[] nombreColumnas, Class[] tipoColumnas) {
		this.nombreColumnas = nombreColumnas;
		this.tipoColumnas = tipoColumnas;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return nombreColumnas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tipoColumnas[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}
}