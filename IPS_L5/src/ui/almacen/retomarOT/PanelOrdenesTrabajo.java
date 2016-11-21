package ui.almacen.retomarOT;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.OrdenTrabajo;
import model.types.EstadoOrdenTrabajo;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaOrdenesTrabajoRetomar;

public class PanelOrdenesTrabajo extends JPanel {

	private static final long serialVersionUID = -2747534485918494L;

	protected VentanaPrincipalAlmacenero ventanaPrincipal;

	protected ModeloTablaOrdenesTrabajoRetomar modeloTablaOrdenesTrabajo;

	// ===========================================
	// Componentes de este panel
	// ===========================================

	// ==== Panel centro ====

	private JPanel panelCentro;

	private JLabel labelOrdenesTrabajo;
	private JScrollPane scrollPaneOrdenesTrabajo;
	private JTable tablaOrdenesTrabajo;

	// ==== Panel sur ====

	private JPanel panelSur;
	private JButton botonAtras;

	public PanelOrdenesTrabajo() {
		super();

		setLayout(new BorderLayout(0, 0));

		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(300, 400));
	}

	// =====================================
	// Panel centro
	// =====================================

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setBorder(new EmptyBorder(0, 3, 0, 3));

			GridBagLayout gbl_panelCentro = new GridBagLayout();

			gbl_panelCentro.columnWidths = new int[] { 65, 240, 65, 0 };
			gbl_panelCentro.rowHeights = new int[] { 25, 270, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };

			panelCentro.setLayout(gbl_panelCentro);

			GridBagConstraints gbc_labelOrdenesTrabajo = new GridBagConstraints();

			gbc_labelOrdenesTrabajo.fill = GridBagConstraints.BOTH;
			gbc_labelOrdenesTrabajo.gridx = 1;
			gbc_labelOrdenesTrabajo.gridy = 0;

			panelCentro.add(getLabelOrdenesTrabajo(), gbc_labelOrdenesTrabajo);

			GridBagConstraints gbc_scrollPaneOrdenesTrabajo = new GridBagConstraints();

			gbc_scrollPaneOrdenesTrabajo.gridwidth = 3;
			gbc_scrollPaneOrdenesTrabajo.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneOrdenesTrabajo.gridx = 0;
			gbc_scrollPaneOrdenesTrabajo.gridy = 1;

			panelCentro.add(getScrollPaneOrdenesTrabajo(), gbc_scrollPaneOrdenesTrabajo);
		}

		return panelCentro;
	}

	private JLabel getLabelOrdenesTrabajo() {
		if (labelOrdenesTrabajo == null) {
			labelOrdenesTrabajo = new JLabel("\u00D3rdenes de Trabajo");

			labelOrdenesTrabajo.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelOrdenesTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelOrdenesTrabajo;
	}

	private JScrollPane getScrollPaneOrdenesTrabajo() {
		if (scrollPaneOrdenesTrabajo == null) {
			scrollPaneOrdenesTrabajo = new JScrollPane(getTablaOrdenesTrabajo());

			scrollPaneOrdenesTrabajo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneOrdenesTrabajo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneOrdenesTrabajo;
	}

	protected JTable getTablaOrdenesTrabajo() {
		if (tablaOrdenesTrabajo == null) {
			modeloTablaOrdenesTrabajo = new ModeloTablaOrdenesTrabajoRetomar();
			tablaOrdenesTrabajo = new JTable(modeloTablaOrdenesTrabajo);

			tablaOrdenesTrabajo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try {
						int fila = getTablaOrdenesTrabajo().getSelectedRow();

						if (fila != -1) {
							retomarOT(modeloTablaOrdenesTrabajo.getOrdenTrabajo(fila));
						}

					}

					catch (BusinessException excep) {
						ventanaPrincipal.gestionarErrorConexion(excep);
					}
				}
			});
		}

		return tablaOrdenesTrabajo;
	}

	private void retomarOT(OrdenTrabajo ordenTrabajo) throws BusinessException {
		ventanaPrincipal.setOrdenTrabajo(ordenTrabajo);

		if (ordenTrabajo.getEstadoOrdenTrabajo().equals(EstadoOrdenTrabajo.RECOGIDA)) { // RETOMAR
			ventanaPrincipal.mostrarPanelRecogidaProductos();
		}

		else { // EMPAQUETAR
			ServiceFactory.getEmpaquetadoService().asignarAlmaceneroOT(ventanaPrincipal.getAlmacenero(), ordenTrabajo);
			ventanaPrincipal.mostrarPanelEmpaquetadoProductos();
		}

		reiniciarPanel();
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(4, 4, 4, 4));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 40, 10, 0, 0 };
			gbl_panelSur.rowHeights = new int[] { 34, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.insets = new Insets(0, 0, 0, 5);
			gbc_botonAtras.gridx = 0;
			gbc_botonAtras.gridy = 0;

			panelSur.add(getBotonAtras(), gbc_botonAtras);
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciarPanel();
					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.BOLD, 12));
		}

		return botonAtras;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	/**
	 * Carga una lista de ordenes de trabajo que hay que retomar (ya sea para
	 * empaquetado o recogida).
	 * 
	 * @return -> true si hay ordenes de trabajo disponibles<br>
	 *         -> false si no hay ordenes de trabajo que recoger
	 * 
	 * @throws BusinessException
	 * 
	 */
	public boolean inicializarDatos() throws BusinessException {

		List<OrdenTrabajo> ordenesTrabajo = ServiceFactory.getAlmaceneroService()
				.ObtenerOrdenesTrabajoRetomar(ventanaPrincipal.getAlmacenero());

		// --------------------------------------
		// Si se encontraron ordenes de trabajo
		// --------------------------------------

		if (ordenesTrabajo.size() > 0) {
			modeloTablaOrdenesTrabajo.setOrdenesTrabajo(ordenesTrabajo);

			return true;
		}

		// --------------------------------------------
		// Si no se encontró ninguna orden de trabajo
		// --------------------------------------------

		else {
			return false;
		}
	}

	private void reiniciarPanel() {
		modeloTablaOrdenesTrabajo.removeAll();
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}