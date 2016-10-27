package ui.almacen.almacenero.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.ProductoEnOrdenTrabajo;
import ui.almacen.almacenero.util.MyPosicionProducto;

public class ModeloTablaProductos extends AbstractModeloTablaNoEditable {
	private static final long serialVersionUID = 1L;

	private List<ProductoEnOrdenTrabajo> productos;

	public ModeloTablaProductos() {
		super(new String[] { "nombre", "unidades",  "posición" }, new Class[] { String.class, Integer.class, MyPosicionProducto.class });

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
			return productos.get(rowIndex).getproductoPedido().getCantidad();
		case 2:
			return new MyPosicionProducto(productos.get(rowIndex));
			
			

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
}