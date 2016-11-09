package ui.almacen.myTypes.tablas.modelosTabla;

import java.util.ArrayList;
import java.util.List;

import model.Pedido;
import model.ProductoEnOrdenTrabajo;

public class ModeloTablaProductosEmpaquetar extends AbstractModeloTablaNoEditable {
	private static final long serialVersionUID = 1L;

	private List<ProductoEnOrdenTrabajo> productos;

	public ModeloTablaProductosEmpaquetar() {
		super(new String[] { "cod. pedido", "ref.", "descripcion", "unidades" },
				new Class[] { Long.class, String.class, String.class, Integer.class });

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
			return productos.get(rowIndex).getproductoPedido().getPedido().getId();

		case 1:
			return productos.get(rowIndex).getRef_OrdenTrabajo();

		case 2:
			return productos.get(rowIndex).getproductoPedido().getProducto().getDescripcion();

		case 3:
			return productos.get(rowIndex).getUnidadesProducto() - productos.get(rowIndex).getUnidadesEmpaquetadas();

		default:
			return null;
		}
	}

	public void setProductos(List<ProductoEnOrdenTrabajo> productos) {
		this.productos = productos;

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public ProductoEnOrdenTrabajo getProducto(String referencia) {
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getRef_OrdenTrabajo().equals(referencia)) {
				return productos.get(i);
			}
		}

		return null;
	}

	public void addProducto(ProductoEnOrdenTrabajo producto) {
		productos.add(producto);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void removeProducto(String referencia) {
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getRef_OrdenTrabajo().equals(referencia)) {
				productos.remove(i);
			}
		}

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	@Override
	public void removeAll() {
		productos.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void removeProductosPedido(Pedido pedido) {

		List<ProductoEnOrdenTrabajo> elementosBorrar = new ArrayList<ProductoEnOrdenTrabajo>();

		for (ProductoEnOrdenTrabajo pot : productos) {
			if (!pot.getproductoPedido().getPedido().equals(pedido)) {
				elementosBorrar.add(pot);
			}
		}
		
		for(ProductoEnOrdenTrabajo pot : elementosBorrar) {
			productos.remove(pot);
		}

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

}