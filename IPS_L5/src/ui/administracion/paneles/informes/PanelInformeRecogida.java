package ui.administracion.paneles.informes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import ui.administracion.VentanaPrincipalAdministracion;
import ui.administracion.myTypes.DatosInformeAlmacenero;
import ui.administracion.myTypes.modelosTabla.ModeloTablaInformeAlmacenero;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class PanelInformeRecogida extends JPanel {

	private static final long serialVersionUID = -3189575166024701660L;

	private VentanaPrincipalAdministracion ventanaPrincipal;

	// ----------------------------------
	// Panel norte
	// ----------------------------------

	private JPanel panelNorte;
	private JLabel labelInforme;

	// ----------------------------------
	// Panel centro
	// ----------------------------------

	private JScrollPane scrollPaneCentro;
	private JTable tablaInformeRecogida;
	private ModeloTablaInformeAlmacenero modeloTablaInformeAlmacenero;

	// ----------------------------------
	// Panel sur
	// ----------------------------------

	private JPanel panelSur;
	private JButton botonAtras;

	public PanelInformeRecogida() {
		super();

		setPreferredSize(new Dimension(816, 646));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getScrollPaneCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	// =========================================
	// Panel norte
	// =========================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();

			flowLayout.setVgap(9);
			flowLayout.setHgap(0);

			panelNorte.add(getLabelInforme());
		}

		return panelNorte;
	}

	private JLabel getLabelInforme() {
		if (labelInforme == null) {
			labelInforme = new JLabel("Informe de recogida de productos");
			labelInforme.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return labelInforme;
	}

	// =========================================
	// Panel centro
	// =========================================

	private JScrollPane getScrollPaneCentro() {
		if (scrollPaneCentro == null) {
			scrollPaneCentro = new JScrollPane(getTablaInformeRecogida());
			scrollPaneCentro.setBorder(new EmptyBorder(0, 3, 0, 3));
			scrollPaneCentro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCentro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneCentro;
	}

	private JTable getTablaInformeRecogida() {
		if (tablaInformeRecogida == null) {
			modeloTablaInformeAlmacenero = new ModeloTablaInformeAlmacenero();

			tablaInformeRecogida = new JTable(modeloTablaInformeAlmacenero);

			tablaInformeRecogida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaInformeRecogida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return tablaInformeRecogida;
	}

	// =========================================
	// Panel sur
	// =========================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setBorder(new EmptyBorder(8, 0, 8, 10));

			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();

			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			flowLayout.setAlignment(FlowLayout.RIGHT);

			panelSur.add(getBotonAtras());
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciarPanel();

					ventanaPrincipal.mostrarPanelSeleccionInforme();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonAtras;
	}

	// ===============================================
	// Controlar el estado del panel
	// ===============================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public void inicializar() throws BusinessException {
		List<DatosInformeAlmacenero> datosinforme = ServiceFactory.getAdministracionService().generarInformeRecogida();

		// Si la lista no está vacía
		if (!datosinforme.isEmpty()) {

			// Cada fecha se corresponde con una fecha
			modeloTablaInformeAlmacenero.addFechasTabla(datosinforme.get(0).getFechas());

			// Rellenar con datos
			for (DatosInformeAlmacenero datos : datosinforme) {
				modeloTablaInformeAlmacenero.addDatosAlmacenero(datos);
			}
		}
	}

	public void reiniciarPanel() {
		modeloTablaInformeAlmacenero.reiniciar();
	}

}