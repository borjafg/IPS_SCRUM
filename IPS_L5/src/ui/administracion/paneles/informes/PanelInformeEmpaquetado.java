package ui.administracion.paneles.informes;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import ui.administracion.VentanaPrincipalAdministracion;
import ui.administracion.myTypes.DatosInformeAlmacenero;
import ui.administracion.myTypes.DatosInformeEmpaquetado;
import ui.administracion.myTypes.modelosTabla.ModeloTablaInformeEmpaquetado;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PanelInformeEmpaquetado extends JPanel {
	
	private VentanaPrincipalAdministracion ventanaPrincipal;
	
	//modelo tabla
	private ModeloTablaInformeEmpaquetado modeloTablaEmpaquetado;
	
	
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelCentro;
	private JButton btnAtras;
	private JScrollPane scrollPaneInforme;
	private JTable tablaInforme;
	private JLabel lblInformeGeneracinDe;

	/**
	 * Create the panel.
	 */
	public PanelInformeEmpaquetado() {
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelSur(), BorderLayout.SOUTH);
		add(getPanelCentro(), BorderLayout.CENTER);

	}
	
	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.add(getLblInformeGeneracinDe());
		}
		return panelNorte;
	}
	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout fl_panelSur = (FlowLayout) panelSur.getLayout();
			fl_panelSur.setAlignment(FlowLayout.RIGHT);
			panelSur.add(getBtnAtras());
		}
		return panelSur;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getScrollPaneInforme());
		}
		return panelCentro;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciarPanel();
					
					ventanaPrincipal.mostrarPanelSeleccionInforme();
				}
			});
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btnAtras;
	}
	private JScrollPane getScrollPaneInforme() {
		if (scrollPaneInforme == null) {
			scrollPaneInforme = new JScrollPane();
			scrollPaneInforme.setViewportView(getTablaInforme());
		}
		return scrollPaneInforme;
	}
	private JTable getTablaInforme() {
		if (tablaInforme == null) {
			
			modeloTablaEmpaquetado = new ModeloTablaInformeEmpaquetado();
			tablaInforme = new JTable(modeloTablaEmpaquetado);
			
			tablaInforme.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaInforme.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return tablaInforme;
	}
	private JLabel getLblInformeGeneracinDe() {
		if (lblInformeGeneracinDe == null) {
			lblInformeGeneracinDe = new JLabel("Informe generaci\u00F3n de paquetes");
			lblInformeGeneracinDe.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblInformeGeneracinDe;
	}
	
	
	//=====================================
	//Control de la tabla
	//========================================
	
	public void inicializar() throws BusinessException {
		List<DatosInformeEmpaquetado> datosinforme = ServiceFactory.getAdministracionService().generarInformeEmpaquetado();

		// Si la lista no está vacía
		if (!datosinforme.isEmpty()) {

			// Cada fecha se corresponde con una fecha
			modeloTablaEmpaquetado.addFechasTabla(datosinforme.get(0).getFechas());

			// Rellenar con datos
			for (DatosInformeEmpaquetado datos : datosinforme) {
				modeloTablaEmpaquetado.addDatosAlmacenero(datos);
			}
		}
	}

	public void reiniciarPanel() {
		modeloTablaEmpaquetado.reiniciar();
	}
	
}
