package ui.almacen.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Pedido;

public class ModeloTablaPedidos extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = 3500989514105896058L;

	private List<Pedido> pedidos;
	
	public ModeloTablaPedidos() {
		super(new String[] { "Cod. Pedido", "Fecha", "Num Productos" },
				new Class[] { Long.class, Date.class, Integer.class });

		pedidos = new ArrayList<Pedido>();
	}
	
	@Override
	public int getRowCount() {
		return pedidos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return pedidos.get(rowIndex).getId();

		case 1:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(pedidos.get(rowIndex).getFecha());

		case 2:
			return pedidos.get(rowIndex).getNumProductosRecoger();

		default:
			return null;
		}
	}
	
	public void addPedido(Pedido pedido) {
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
		return pedidos.get(fila);
	}

	@Override
	public void removeAll() {
		pedidos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}