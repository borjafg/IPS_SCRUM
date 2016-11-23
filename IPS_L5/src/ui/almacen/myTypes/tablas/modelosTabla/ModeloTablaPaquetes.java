package ui.almacen.myTypes.tablas.modelosTabla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Paquete;

public class ModeloTablaPaquetes extends AbstractModeloTablaNoEditable {

	private static final long serialVersionUID = 8556680416924357392L;

	private List<Paquete> paquetes;

	public ModeloTablaPaquetes() {
		super(new String[] { "codigo del paquete" }, new Class[] { Long.class });

		paquetes = new ArrayList<Paquete>();
	}

	@Override
	public int getRowCount() {
		return paquetes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return paquetes.get(rowIndex).getId();

		default:
			return null;
		}
	}

	@Override
	public void removeAll() {
		paquetes.clear();

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	// =================================================
	// Recuperar y asignar paquetes al modelo
	// =================================================

	public Paquete getPaquete(int fila) {
		return paquetes.get(fila);
	}

	public Paquete findPaqueteById(Long id) {
		for (Paquete paquete : paquetes) {
			if ((new Long(paquete.getId())).equals(id)) {
				return paquete;
			}
		}

		return null;
	}

	public void addPaquete(Paquete paquete) {
		paquetes.add(paquete);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void setPaquetes(List<Paquete> paquetes) {
		this.paquetes = paquetes;

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	// ==============================
	// Borrar paquetes y ordenar
	// ==============================

	public void removePaquete(Paquete paq) {
		paquetes.remove(paq);

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

	public void ordenarPorId() {
		Collections.sort(paquetes, new Comparator<Paquete>() {

			@Override
			public int compare(Paquete paquete1, Paquete paquete2) {
				return (new Long(paquete1.getId())).compareTo(paquete2.getId());
			}
		});

		// Hay que actualizar la tabla
		this.fireTableDataChanged();
	}

}