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
import ui.administracion.myTypes.DatosInformeTipoCliente;
import ui.administracion.myTypes.modelosTabla.ModeloTablaInformeTipoUsuario;

public class PanelInformeUsuarios extends JPanel {

	private static final long serialVersionUID = 886082235304262380L;

	private VentanaPrincipalAdministracion ventanaPrincipal;
	private JPanel panelNorte;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton btnAtras;
	private JLabel lblTitulo;
	private JScrollPane scrollPane;
	private JTable table;
	
	private ModeloTablaInformeTipoUsuario modeloTablaUsuario;

	/**
	 * Create the panel.
	 */
	public PanelInformeUsuarios() {
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
			panelNorte.add(getLblTitulo());
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
			panelSur.add(getBtnAtras());
		}
		return panelSur;
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

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Informe tipo cliente");
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblTitulo;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			modeloTablaUsuario = new ModeloTablaInformeTipoUsuario();
			
			table = new JTable(modeloTablaUsuario);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		
		return table;
	}
	
	public void reiniciarPanel() {
		modeloTablaUsuario.reiniciar();
	}
	
	
	public void inicializar() throws BusinessException {
		List<DatosInformeTipoCliente > datosCliente = ServiceFactory.getAdministracionService().generarInformeTipoCliente();
		
		if (!datosCliente.isEmpty()) {

			// Cada fecha se corresponde con una fecha
			modeloTablaUsuario.addFechasTabla(datosCliente.get(0).getFechas());

			// Rellenar con datos
			for (DatosInformeTipoCliente datos : datosCliente) {
				modeloTablaUsuario.addDatosPedido(datos);
			}
		}
		
		
		
		
	}
	
	
	
	
	
}
