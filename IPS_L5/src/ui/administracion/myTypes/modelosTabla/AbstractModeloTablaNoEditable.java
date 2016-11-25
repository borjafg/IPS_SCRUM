package ui.administracion.myTypes.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.*;

/**
 * Para usar esta clase hay redefinir algunos métodos más, así como añadir una
 * List<T> siendo T el tipo de objeto que tendrá el modelo. </br>
 * </br>
 * Este tipo de modelo de tablas no permitirá que se editen las celdas de la
 * tabla, y generará los valores de cada fila a partir de los objetos del
 * modelo. </br>
 * </br>
 * La ventaja de esta versión es que se pueden recuperar los objetos del modelo
 * y acceder a sus atributos (aunque no fueran utilizados para generar las
 * filas). Ejemplo:</br>
 * </br>
 * - Clase Persona {nombre, edad, dni}</br>
 * - En la tabla sólo se muestra el nombre y la edad</br>
 * </br>
 * Aunque en la tabla sólo se muestren dos atributos, es posible acceder al dni
 * de esa persona, o incluso recuperar el objeto persona sin necesidad de volver
 * a generarlo (haciendo new Persona(...)).
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractModeloTablaNoEditable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	protected final List<Class> tipoColumnas;
	protected final List<String> nombreColumnas;

	public AbstractModeloTablaNoEditable() {
		tipoColumnas = new ArrayList<Class>();
		nombreColumnas = new ArrayList<String>();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int column) {
		return nombreColumnas.get(column);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tipoColumnas.get(columnIndex);
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.size();
	}

	public abstract void reiniciar();
}