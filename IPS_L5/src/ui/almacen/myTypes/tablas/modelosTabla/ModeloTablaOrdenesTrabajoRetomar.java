package ui.almacen.myTypes.tablas.modelosTabla;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.OrdenTrabajo;
import model.types.EstadoOrdenTrabajo;

public class ModeloTablaOrdenesTrabajoRetomar extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = -1845248439429319884L;

	private List<OrdenTrabajo> ordenesTrabajo;

	public ModeloTablaOrdenesTrabajoRetomar() {
		super(new String[] { "Cod. OT", "Fecha", "Tipo de OT" },
				new Class[] { Long.class, String.class, String.class });

		ordenesTrabajo = new ArrayList<OrdenTrabajo>();
	}

	@Override
	public int getRowCount() {
		return ordenesTrabajo.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OrdenTrabajo ot = ordenesTrabajo.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return ot.getId();

		case 1:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return sdf.format(ot.getFechaCreacion());

		case 2:
			return evaluarEstado(ot);

		default:
			return null;
		}
	}

	public String evaluarEstado(OrdenTrabajo ot) {
		EstadoOrdenTrabajo estado = ot.getEstadoOrdenTrabajo();

		if (estado.equals(EstadoOrdenTrabajo.EMPAQUETADO)) {
			return "Empaquetar";
		}

		else if (estado.equals(EstadoOrdenTrabajo.RECOGIDA)) {
			return "Recoger";
		}

		else {
			return "Desconocido";
		}

	}

	public void addOrdenTrabajoEmpaquetar(OrdenTrabajo ordenTrabajo) {
		ordenesTrabajo.add(ordenTrabajo);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public OrdenTrabajo getOrdenTrabajo(int fila) {
		return ordenesTrabajo.get(fila);
	}
	
	public void setOrdenesTrabajo(List<OrdenTrabajo> ordenesTrabajo2) {
		this.ordenesTrabajo = ordenesTrabajo2;

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	@Override
	public void removeAll() {
		ordenesTrabajo.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

}