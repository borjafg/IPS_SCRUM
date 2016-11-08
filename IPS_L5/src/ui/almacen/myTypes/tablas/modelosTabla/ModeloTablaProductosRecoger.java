package ui.almacen.myTypes.tablas.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.ProductoEnOrdenTrabajo;
import ui.almacen.myTypes.model.MyProducto_OrdenadoPosicion;

public class ModeloTablaProductosRecoger extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = 1L;

	private List<MyProducto_OrdenadoPosicion> productos;

	public ModeloTablaProductosRecoger() {
		super(new String[] { "referencia", "descripcion", "unidades", "posición" },
				new Class[] { String.class, String.class, Integer.class, String.class });

		productos = new ArrayList<MyProducto_OrdenadoPosicion>();
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MyProducto_OrdenadoPosicion prod = productos.get(rowIndex);

		switch (columnIndex) {
		case 0: // Referencia dentro de la orden de trabajo
			return prod.getProducto().getRef_OrdenTrabajo();

		case 1: // Descripcion del producto
			return prod.getProducto().getproductoPedido().getProducto().getDescripcion();

		case 2: // Unidades que qudan por recoger
			return prod.getProducto().getUnidadesProducto() - prod.getProducto().getUnidadesRecogidas();

		case 3: // Posición del producto
			return prod.getInfo();

		default:
			return null;
		}
	}

	public void setProductos(List<MyProducto_OrdenadoPosicion> productos) {
		this.productos = productos;

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void addProducto(MyProducto_OrdenadoPosicion producto) {
		productos.add(producto);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	/**
	 * Borra un ProductoEnOrdenTrabajo si el id del Producto asociado a él es
	 * igual al que se le pasa como parámetro.
	 * 
	 * @param id
	 *            id del producto
	 * 
	 */
	public void removeProducto(long id) {
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getProducto().getproductoPedido().getProducto().getId() == id) {
				productos.remove(i);
			}
		}

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public ProductoEnOrdenTrabajo getProducto(long id) {

		for (MyProducto_OrdenadoPosicion producto : productos) {
			if (producto.getProducto().getproductoPedido().getProducto().getId() == id) {
				return producto.getProducto();
			}
		}

		return null; // no hay ninguno con ese id
	}

	@Override
	public void removeAll() {
		productos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}
}