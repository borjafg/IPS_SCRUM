package ui.almacen.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.ProductoEnOrdenTrabajo;
import ui.almacen.recogida.util.MyPosicionProducto;

public class ModeloTablaProductosRecoger extends AbstractModeloTablaNoEditable {
	
	private static final long serialVersionUID = 1L;

	private List<ProductoEnOrdenTrabajo> productos;

	public ModeloTablaProductosRecoger() {
		super(new String[] { "referencia", "descripcion", "unidades",  "posición" }, new Class[] { String.class, Long.class, String.class, Integer.class, MyPosicionProducto.class });

		productos = new ArrayList<ProductoEnOrdenTrabajo>();
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: // Referencia dentro de la orden de trabajo
			return productos.get(rowIndex).getRef_OrdenTrabajo();

		case 1: // Descripcion del producto
			return productos.get(rowIndex).getproductoPedido().getProducto().getDescripcion();
		
		case 2: // Unidades que qudan por recoger
			return productos.get(rowIndex).getUnidadesRecoger() - productos.get(rowIndex).getUnidadesRecogidas();
			
		case 3: // Posición del producto
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
	
	@Override
	public void removeAll() {
		productos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}