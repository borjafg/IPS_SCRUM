package ui.administracion.paneles.transferencias;

import javax.swing.JPanel;

import ui.administracion.VentanaPrincipalAdministracion;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PanelTransferencias extends JPanel {

	private static final long serialVersionUID = -7679569757550310944L;

	private VentanaPrincipalAdministracion ventanaPrincipal;

	private JPanel panelTitulo;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonAtras;
	private JPanel panelLista;
	private JPanel panelConfirmarPago;
	private JButton btnConfirmar;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 * 
	 */
	public PanelTransferencias() {
		super();

		setPreferredSize(new Dimension(816, 646));
		setLayout(new BorderLayout(0, 0));

		add(getPanelTitulo(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
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
			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getPanelLista(), BorderLayout.CENTER);
			panelCentro.add(getPanelConfirmarPago(), BorderLayout.EAST);
		}

		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			FlowLayout fl_panelSur = (FlowLayout) panelSur.getLayout();
			fl_panelSur.setAlignment(FlowLayout.RIGHT);

			panelSur.add(getBotonAtras());
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonAtras;
	}

	// =========================================
	// Controlar el estado del panel
	// =========================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();
			panelLista.setLayout(new BorderLayout(0, 0));
			panelLista.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelLista;
	}
	private JPanel getPanelConfirmarPago() {
		if (panelConfirmarPago == null) {
			panelConfirmarPago = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelConfirmarPago.getLayout();
			flowLayout.setVgap(200);
			panelConfirmarPago.add(getBtnConfirmar());
		}
		return panelConfirmarPago;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnConfirmar;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}
		return scrollPane;
	}
}