package ui.almacen.envios;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Transportista;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaSeleccionTransportista;

public class PanelSeleccionTransportista extends JPanel {

	private static final long serialVersionUID = -6586893706682937775L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private ModeloTablaSeleccionTransportista modeloTablaTransportistas;

	// -------------------
	// --- Panel norte ---
	// -------------------

	private JPanel panelNorte;
	private JLabel labelTransportistas;

	// --------------------
	// --- Panel centro ---
	// --------------------

	private JScrollPane scrollPaneTransportistas;
	private JTable tablaTransportistas;

	// ---------------------
	// ----- Panel sur -----
	// ---------------------

	private JPanel panelSur;
	private JButton botonAtras;

	public PanelSeleccionTransportista() {
		super();

		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getScrollPaneTransportistas(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(300, 400));
	}

	public void inicializarDatos() throws BusinessException {
		List<Transportista> transportistas = ServiceFactory.getEnvioService().obtenerListaTransportistas();

		modeloTablaTransportistas.setTransportistas(transportistas);
	}

	// =====================================
	// Panel norte
	// =====================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			panelNorte.setBorder(new EmptyBorder(1, 0, 5, 0));
			panelNorte.add(getLabelTransportistas());
		}

		return panelNorte;
	}

	private JLabel getLabelTransportistas() {
		if (labelTransportistas == null) {
			labelTransportistas = new JLabel("Transportistas");

			labelTransportistas.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelTransportistas.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelTransportistas;
	}

	// =====================================
	// Panel centro
	// =====================================

	private JScrollPane getScrollPaneTransportistas() {
		if (scrollPaneTransportistas == null) {
			scrollPaneTransportistas = new JScrollPane(getTablaTransportistas());

			scrollPaneTransportistas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scrollPaneTransportistas.setBorder(new EmptyBorder(0, 2, 0, 2));

			scrollPaneTransportistas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneTransportistas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneTransportistas;
	}

	private JTable getTablaTransportistas() {
		if (tablaTransportistas == null) {
			modeloTablaTransportistas = new ModeloTablaSeleccionTransportista();
			tablaTransportistas = new JTable(modeloTablaTransportistas);

			tablaTransportistas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int filaSeleccionada = tablaTransportistas.getSelectedRow();

					if (filaSeleccionada != -1) {
						Transportista transportista = modeloTablaTransportistas.getTransportista(filaSeleccionada);

						procesarTransportista(transportista);
					}

				}
			});

			tablaTransportistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaTransportistas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

			tablaTransportistas.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tablaTransportistas.setFont(new Font("Tahoma", Font.BOLD, 14));
		}

		return tablaTransportistas;
	}

	private void procesarTransportista(Transportista transportista) {
		if (transportista != null) {
			try {
				reiniciarPanel();

				ventanaPrincipal.setTransportista(transportista);
				ventanaPrincipal.mostrarPanelEnvioPaquetes();

			} catch (BusinessException excep) {
				reiniciarPanel();
				ventanaPrincipal.gestionarErrorConexion(excep);
			}
		}
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(8, 8, 8, 8));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 200, 80, 0 };
			gbl_panelSur.rowHeights = new int[] { 34, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.gridx = 1;
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

	private void reiniciarPanel() {
		modeloTablaTransportistas.removeAll();
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}