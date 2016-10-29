package ui.almacen.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.ProductoEnOrdenTrabajo;

public class ModeloTablaProductosEmpaquetar extends AbstractModeloTablaNoEditable {
	private static final long serialVersionUID = 1L;

	private List<ProductoEnOrdenTrabajo> productos;

	public ModeloTablaProductosEmpaquetar() {
		super(new String[] { "nombre", "unidades" }, new Class[] { String.class, Integer.class });

		productos = new ArrayList<ProductoEnOrdenTrabajo>();
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return productos.get(rowIndex).getproductoPedido().getProducto().getNombre();

		case 1:
			return productos.get(rowIndex).getUnidadesRecoger();

		default:
			return null;
		}
	}

	public void addProducto(ProductoEnOrdenTrabajo producto) {
		productos.add(producto);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void removeProducto(int fila) {
		productos.remove(fila);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public ProductoEnOrdenTrabajo getProducto(int fila) {
		return productos.get(fila);
	}

	@Override
	public void removeAll() {
		productos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}