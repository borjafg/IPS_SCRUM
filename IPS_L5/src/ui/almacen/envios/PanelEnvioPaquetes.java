package ui.almacen.envios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.almacen.VentanaPrincipalAlmacenero;

public class PanelEnvioPaquetes extends JPanel {

	private static final long serialVersionUID = 1828028842807322192L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	// -----------------------
	// ----- Panel norte -----
	// -----------------------

	private JPanel panelNorte;
	private JLabel labelPaquetesSinEnvio;

	// ------------------------
	// ----- Panel centro -----
	// ------------------------

	// -----------------------
	// ------ Panel sur ------
	// -----------------------

	private JPanel panelSur;
	private JButton botonAtras;
	private JButton botonCerrarEnvio;

	public PanelEnvioPaquetes() {
		super();

		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(300, 400));
	}

	public void inicializarDatos() {

	}

	// ======================================
	// Panel norte
	// ======================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			panelNorte.setBorder(new EmptyBorder(1, 0, 5, 0));
			panelNorte.add(getLabelPaquetesSinEnvio());
		}

		return panelNorte;
	}

	private JLabel getLabelPaquetesSinEnvio() {
		if (labelPaquetesSinEnvio == null) {
			labelPaquetesSinEnvio = new JLabel("Paquetes sin env\u00EDo");

			labelPaquetesSinEnvio.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelPaquetesSinEnvio.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPaquetesSinEnvio;
	}

	// ======================================
	// Panel centro
	// ======================================

	// ======================================
	// Panel sur
	// ======================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 4, 1));

			panelSur.add(getBotonAtras());
			panelSur.add(getBotonCerrarEnvio());
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

			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return botonAtras;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public void reiniciarPanel() {

	}

	private JButton getBotonCerrarEnvio() {
		if (botonCerrarEnvio == null) {
			botonCerrarEnvio = new JButton("Cerrar env\u00EDo");
			botonCerrarEnvio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return botonCerrarEnvio;
	}
}