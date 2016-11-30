package ui.administracion.paneles.informes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import ui.administracion.VentanaPrincipalAdministracion;
import ui.administracion.myTypes.DatosInformeMetodoPago;
import ui.administracion.myTypes.modelosTabla.ModeloTablaInformeMetodoPago;

public class PanelInformeMetodoPago extends JPanel {

	private static final long serialVersionUID = -4209369657616084983L;

	private VentanaPrincipalAdministracion ventanaPrincipal;
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton btnNewButton;
	private JLabel lblInforme;
	private JScrollPane scrollPane;
	private JTable tablaInforme;
	private ModeloTablaInformeMetodoPago modeloTabla;

	/**
	 * Create the panel.
	 */
	public PanelInformeMetodoPago() {
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

	}

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.add(getLblInforme());
		}
		return panelNorte;
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getScrollPane());
		}
		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSur.add(getBtnNewButton());
		}
		return panelSur;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Atr\u00E1s");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciarPanel();

					ventanaPrincipal.mostrarPanelSeleccionInforme();
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btnNewButton;
	}

	private JLabel getLblInforme() {
		if (lblInforme == null) {
			lblInforme = new JLabel("Informe de tipos de pago");
			lblInforme.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblInforme;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaInforme());
		}
		return scrollPane;
	}

	private JTable getTablaInforme() {
		if (tablaInforme == null) {
			modeloTabla = new ModeloTablaInformeMetodoPago();

			tablaInforme = new JTable(modeloTabla);
			tablaInforme.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaInforme.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return tablaInforme;
	}

	public void reiniciarPanel() {
		modeloTabla.reiniciar();
	}

	public void inicializar() throws BusinessException {
		List<DatosInformeMetodoPago> datosPago = ServiceFactory.getAdministracionService().generarInformeMetodoPago();

		if (!datosPago.isEmpty()) {

			// Cada fecha se corresponde con una fecha
			modeloTabla.addFechasTabla(datosPago.get(0).getFechas());

			// Rellenar con datos
			for (DatosInformeMetodoPago datos : datosPago) {
				modeloTabla.addDatosPedido(datos);
			}
		}

	}

}
