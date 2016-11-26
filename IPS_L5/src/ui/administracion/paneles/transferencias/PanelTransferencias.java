package ui.administracion.paneles.transferencias;

import javax.swing.JPanel;

import ui.administracion.VentanaPrincipalAdministracion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelTransferencias extends JPanel {

	private VentanaPrincipalAdministracion ventanaPrincipal;

	private JPanel panelBase;
	private JPanel panelTitulo;
	private JPanel panelCentro;
	private JPanel panelBotones;
	private JButton btnAtras;

	/**
	 * Create the panel.
	 */
	public PanelTransferencias() {
		setLayout(new BorderLayout(0, 0));
		add(getPanelBase());

	}

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();
			panelBase.setLayout(new BorderLayout(0, 0));
			panelBase.add(getPanelTitulo(), BorderLayout.NORTH);
			panelBase.add(getPanelCentro(), BorderLayout.SOUTH);
			panelBase.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelBase;
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
		}
		return panelTitulo;
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
		}
		return panelCentro;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBotones.add(getBtnAtras());
		}
		return panelBotones;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaPrincipal.volverPanelOpciones();

				}
			});
			btnAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return btnAtras;
	}
}
