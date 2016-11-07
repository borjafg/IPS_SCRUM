package ui.almacen.myTypes.tablas.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

public class ModeloTablaSeleccionPedidos extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = 3500989514105896058L;

	private List<MyPedido_OT_Retomar> pedidos;
	
	public ModeloTablaSeleccionPedidos() {
		super(new String[] { "Cod. Pedido", "Fecha", "Num Productos" },
				new Class[] { Long.class, String.class, Integer.class });

		pedidos = new ArrayList<MyPedido_OT_Retomar>();
	}
	
	@Override
	public int getRowCount() {
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return pedidos.get(rowIndex).getPedido().getId();

		case 1:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(pedidos.get(rowIndex).getPedido().getFecha());

		case 2:
			return pedidos.get(rowIndex).getNumProductosFaltan();

		default:
			return null;
		}
	}
	
	public void addPedido(MyPedido_OT_Retomar pedido) {
		pedidos.add(pedido);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void removePedido(int fila) {
		pedidos.remove(fila);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public Pedido getPedido(int fila) {
		return pedidos.get(fila).getPedido();
	}

	@Override
	public void removeAll() {
		pedidos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}