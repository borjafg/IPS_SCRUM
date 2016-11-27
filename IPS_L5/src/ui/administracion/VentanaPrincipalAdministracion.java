package ui.administracion;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import ui.administracion.paneles.PanelOpciones;
import ui.administracion.paneles.informes.PanelInformeRecogida;
import ui.administracion.paneles.informes.PanelSeleccionInforme;
import ui.administracion.paneles.transferencias.PanelTransferencias;

public class VentanaPrincipalAdministracion extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PanelOpciones panelOpciones;
	private PanelInformeRecogida panelInformeRecogida;
	private PanelTransferencias panelTransferencias;
	private PanelSeleccionInforme panelSeleccionInforme;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAdministracion frame = new VentanaPrincipalAdministracion();
					frame.setVisible(true);
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public VentanaPrincipalAdministracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 650);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		addPaneles();
	}

	private void addPaneles() {
		contentPane.setLayout(new CardLayout(0, 0));

		// ==================================
		// Panel de opciones
		// ==================================

		panelOpciones = new PanelOpciones();
		panelOpciones.setVentanaPrincipal(this);

		contentPane.add(panelOpciones, "panelOpciones");

		// ==================================
		// Panel transferencias
		// ==================================

		panelTransferencias = new PanelTransferencias();
		panelTransferencias.setVentanaPrincipal(this);

		contentPane.add(panelTransferencias, "panelTransferencias");

		// ==================================
		// Panel seleccion de informe
		// ==================================

		panelSeleccionInforme = new PanelSeleccionInforme();
		panelSeleccionInforme.setVentanaPrincipal(this);

		contentPane.add(panelSeleccionInforme, "panelSeleccionInforme");
		// ==================================
		// Panel informes de recogida
		// ==================================

		panelInformeRecogida = new PanelInformeRecogida();
		panelInformeRecogida.setVentanaPrincipal(this);

		contentPane.add(panelInformeRecogida, "panelInformeRecogida");

	}

	// ==========================================
	// Navegación entre paneles
	// ==========================================

	public void volverPanelOpciones() {
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOpciones");
	}

	public void mostrarInformeRecogida() throws BusinessException {
		// Procesar datos
		panelInformeRecogida.inicializar();

		// Mostrar informe
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelInformeRecogida");
	}

	public void mostrarPanelTransferencia() throws BusinessException {
		// incializar los datos
		panelTransferencias.inicializarDatos();
		// mostrar el panel
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelTransferencias");
	}
	
	public void mostrarPanelSeleccionInforme(){
		((CardLayout)contentPane.getLayout()).show(contentPane, "panelSeleccionInforme");
	}

}