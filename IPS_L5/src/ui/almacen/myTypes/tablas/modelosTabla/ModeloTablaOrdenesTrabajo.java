package ui.almacen.myTypes.tablas.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.OrdenTrabajo;
import ui.almacen.myTypes.model.MyOrdenTrabajo_Retomar;

public class ModeloTablaOrdenesTrabajo extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -1845248439429319884L;

	private List<MyOrdenTrabajo_Retomar> ordenesTrabajo;

	public ModeloTablaOrdenesTrabajo() {
		super(new String[] { "Cod. OT", "Fecha", "Num Pedidos", "Num Productos" },
				new Class[] { Long.class, String.class, Integer.class, Integer.class });

		ordenesTrabajo = new ArrayList<MyOrdenTrabajo_Retomar>();
	}

	@Override
	public int getRowCount() {
		return ordenesTrabajo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MyOrdenTrabajo_Retomar otr = ordenesTrabajo.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return otr.getOrdenTrabajo().getId();

		case 1:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(otr.getOrdenTrabajo().getFecha());

		case 2:
			return otr.getNumPedidosFaltanRecoger();

		case 3:
			return otr.getNumProductosFaltanRecoger();

		default:
			return null;
		}
	}

	public void addOrdenTrabajoEmpaquetar(MyOrdenTrabajo_Retomar ordenTrabajo) {
		ordenesTrabajo.add(ordenTrabajo);
	}
	
	public OrdenTrabajo getOrdenTrabajo(int fila) {
		return ordenesTrabajo.get(fila).getOrdenTrabajo();
	}

	@Override
	public void removeAll() {
		ordenesTrabajo.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}