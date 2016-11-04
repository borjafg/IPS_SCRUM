package ui.almacen.myTypes.tablas.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.OrdenTrabajo;

public class ModeloTablaOrdenesTrabajo extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -1845248439429319884L;

	private List<OrdenTrabajo> ordenesTrabajo;

	public ModeloTablaOrdenesTrabajo() {
		super(new String[] { "Cod. OT", "Fecha", "Num Pedidos", "Num Productos" },
				new Class[] { Long.class, String.class, Integer.class, Integer.class });

		ordenesTrabajo = new ArrayList<OrdenTrabajo>();
	}

	@Override
	public int getRowCount() {
		return ordenesTrabajo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ordenesTrabajo.get(rowIndex).getId();

		case 1:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(ordenesTrabajo.get(rowIndex).getFecha());

		case 2:
//			return ordenesTrabajo.get(rowIndex).getNumPedidosConProductosRecoger();
			return -1010000100;

		case 3:
//			return ordenesTrabajo.get(rowIndex).getNumProductosFaltanRecoger();
			return -1010000100;

		default:
			return null;
		}
	}

	@Override
	public void removeAll() {
		ordenesTrabajo.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}