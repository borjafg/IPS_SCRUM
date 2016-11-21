package ui.almacen.myTypes.tablas.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.Transportista;

public class ModeloTablaSeleccionTransportista extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -9048552285714696520L;

	private List<Transportista> transportistas;

	public ModeloTablaSeleccionTransportista() {
		super(new String[] { "nombre" }, new Class[] { String.class });

		transportistas = new ArrayList<Transportista>();
	}

	@Override
	public int getRowCount() {
		return transportistas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		switch (columnIndex) {
		case 0:
			return transportistas.get(rowIndex).getNombre();

		default:
			return null;
		}

	}

	public Transportista getTransportista(int fila) {
		return transportistas.get(fila);
	}

	public void setTransportistas(List<Transportista> transportistas) {
		this.transportistas = transportistas;

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	@Override
	public void removeAll() {
		transportistas.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

}