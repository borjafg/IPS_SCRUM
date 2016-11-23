package ui.administracion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class VentanaPrincipalAdministracion extends JFrame {

	private JPanel contentPane;
	private JPanel panelBase;
	private JPanel panelMenu1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAdministracion frame = new VentanaPrincipalAdministracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipalAdministracion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanelBase(), BorderLayout.CENTER);
	}

	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();
			panelBase.setLayout(new CardLayout(0, 0));
			panelBase.add(getPanelMenu1(), "name_189015599111308");
		}
		return panelBase;
	}
	private JPanel getPanelMenu1() {
		if (panelMenu1 == null) {
			panelMenu1 = new JPanel();
		}
		return panelMenu1;
	}
}
