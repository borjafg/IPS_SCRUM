package ui.administracion;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import ui.administracion.paneles.PanelOpciones;
import ui.administracion.paneles.informes.PanelInformeRecogida;

public class VentanaPrincipalAdministracion extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PanelOpciones panelOpciones;
	private PanelInformeRecogida panelInformeRecogida;

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
		((CardLayout) contentPane.getLayout()).show(contentPane, "panelOpciones");
	}

}