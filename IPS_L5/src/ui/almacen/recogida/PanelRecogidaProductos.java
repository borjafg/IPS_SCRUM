package ui.almacen.recogida;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.modelosTabla.ModeloTablaProductosRecoger;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class PanelRecogidaProductos extends JPanel {

	private static final long serialVersionUID = -2481078673400949799L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;
	
	// ===========================================
	// Componentes de este panel
	// ===========================================

	// ==== Panel norte ====

	private JPanel panelNorte;
	private JLabel labelCodOrdenTrabajo;
	private JTextField textFieldCodOrdenTrabajo;

	// ==== Panel centro ====

	private JPanel panelCentro;
	private JTable tablaProductos;
	private ModeloTablaProductosRecoger modeloTablaProductos;

	// ==== Panel sur ====

	private JPanel panelSur;
	private JLabel labelIncidencias;
	private JSpinner spinnerUnidades;
	private JButton botonAtras;
	private JButton botonIncidencias;

	/**
	 * Devuelve un panel que permite empaquetar productos de una determinada
	 * orden de trabajo
	 * 
	 * @param ventanaPrincipal
	 *            referencia al JFrame que contiene el panel
	 * 
	 */
	public PanelRecogidaProductos() {
		super();

		setPreferredSize(new Dimension(374, 530));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	public void inicializarDatos() {
		// Cargar lista de productos en la Orden de Trabajo
	}

	// =====================================
	// Panel norte
	// =====================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBorder(new EmptyBorder(2, 0, 2, 0));

			panelNorte.add(getLabelCodOrdenTrabajo());
			panelNorte.add(getTextFieldCodOrdenTrabajo());
		}

		return panelNorte;
	}

	private JLabel getLabelCodOrdenTrabajo() {
		if (labelCodOrdenTrabajo == null) {
			labelCodOrdenTrabajo = new JLabel("C\u00F3digo de la OT: ");

			labelCodOrdenTrabajo.setHorizontalTextPosition(SwingConstants.CENTER);
			labelCodOrdenTrabajo.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelCodOrdenTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			labelCodOrdenTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return labelCodOrdenTrabajo;
	}

	private JTextField getTextFieldCodOrdenTrabajo() {
		if (textFieldCodOrdenTrabajo == null) {
			textFieldCodOrdenTrabajo = new JTextField();

			textFieldCodOrdenTrabajo.setEditable(false);
			textFieldCodOrdenTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textFieldCodOrdenTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldCodOrdenTrabajo.setColumns(15);
		}

		return textFieldCodOrdenTrabajo;
	}

	// =====================================
	// Panel centro
	// =====================================

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			
			panelCentro.setLayout(new GridLayout(0, 1, 0, 0));
			panelCentro.add(getTablaProductos());
		}

		return panelCentro;
	}

	private JTable getTablaProductos() {
		if (tablaProductos == null) {
			modeloTablaProductos = new ModeloTablaProductosRecoger();
			tablaProductos = new JTable(modeloTablaProductos);
			tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}

		return tablaProductos;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setBorder(new EmptyBorder(0, 8, 0, 7));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 40, 40, 20, 0, 0, 10, 10, 0 };
			gbl_panelSur.rowHeights = new int[] { 12, 34, 10, 34, 9, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_labelIncidencias = new GridBagConstraints();

			gbc_labelIncidencias.fill = GridBagConstraints.BOTH;
			gbc_labelIncidencias.gridwidth = 4;
			gbc_labelIncidencias.insets = new Insets(0, 0, 5, 5);
			gbc_labelIncidencias.gridx = 0;
			gbc_labelIncidencias.gridy = 1;

			panelSur.add(getLabelIncidencias(), gbc_labelIncidencias);

			GridBagConstraints gbc_spinnerUnidades = new GridBagConstraints();

			gbc_spinnerUnidades.gridwidth = 3;
			gbc_spinnerUnidades.fill = GridBagConstraints.BOTH;
			gbc_spinnerUnidades.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerUnidades.gridx = 4;
			gbc_spinnerUnidades.gridy = 1;

			panelSur.add(getSpinnerUnidades(), gbc_spinnerUnidades);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.gridwidth = 2;
			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.insets = new Insets(0, 0, 5, 5);
			gbc_botonAtras.gridx = 0;
			gbc_botonAtras.gridy = 3;

			panelSur.add(getBotonAtras(), gbc_botonAtras);

			GridBagConstraints gbc_botonIncidencias = new GridBagConstraints();

			gbc_botonIncidencias.fill = GridBagConstraints.BOTH;
			gbc_botonIncidencias.gridwidth = 4;
			gbc_botonIncidencias.insets = new Insets(0, 0, 5, 5);
			gbc_botonIncidencias.gridx = 3;
			gbc_botonIncidencias.gridy = 3;

			panelSur.add(getBotonIncidencias(), gbc_botonIncidencias);
		}

		return panelSur;
	}

	private JLabel getLabelIncidencias() {
		if (labelIncidencias == null) {
			labelIncidencias = new JLabel("");

			labelIncidencias.setForeground(new Color(165, 42, 42));
			labelIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelIncidencias.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelIncidencias;
	}

	private JSpinner getSpinnerUnidades() {
		if (spinnerUnidades == null) {
			spinnerUnidades = new JSpinner();
			spinnerUnidades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return spinnerUnidades;

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

			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonAtras;
	}

	private JButton getBotonIncidencias() {
		if (botonIncidencias == null) {
			botonIncidencias = new JButton("Registrar incidencia");

			botonIncidencias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO preguntar como se registran las incidencias

					// Si hubo incidencias
					indicarQueHuboIncidencias();
				}
			});

			botonIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonIncidencias;
	}

	// =======================================
	// Control del estado del panel
	// ========================================

	private void indicarQueHuboIncidencias() {
		getLabelIncidencias().setText("Ha habido incidencias");
	}

	private void reiniciarPanel() {
		getLabelIncidencias().setText("");
		getTextFieldCodOrdenTrabajo().setText("");

		modeloTablaProductos.removeAll();
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}