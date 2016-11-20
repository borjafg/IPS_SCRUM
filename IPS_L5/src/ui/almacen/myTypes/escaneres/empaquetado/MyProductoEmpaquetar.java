package ui.almacen.myTypes.escaneres.empaquetado;

import model.ProductoEnOrdenTrabajo;

public class MyProductoEmpaquetar {

	private ProductoEnOrdenTrabajo prodOT;
	
	public MyProductoEmpaquetar(ProductoEnOrdenTrabajo prodOT) {
		this.prodOT = prodOT;
	}
	
	public ProductoEnOrdenTrabajo getProductoOT() {
		return prodOT;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Cod. pedido: ").append(prodOT.getproductoPedido().getPedido().getId()).append(", ");
		sb.append("Referencia: ").append(prodOT.getRef_OrdenTrabajo()).append(", ");
		sb.append("descripcion: ").append(prodOT.getproductoPedido().getProducto().getDescripcion());
		
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prodOT == null) ? 0 : prodOT.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		MyProductoEmpaquetar other = (MyProductoEmpaquetar) obj;
		
		if (prodOT == null) {
			if (other.prodOT != null)
				return false;
		} else if (!prodOT.equals(other.prodOT))
			return false;
		
		return true;
	}
	
}