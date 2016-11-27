package ui.administracion.paneles.informes;

import javax.swing.JPanel;

import business.exception.BusinessException;
import infrastructure.Log;
import ui.administracion.VentanaPrincipalAdministracion;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSeleccionInforme extends JPanel {

	private static final long serialVersionUID = 8723963984542621580L;

	private VentanaPrincipalAdministracion ventanaPrincipal;

	// =====================================
	// Panel norte
	// =====================================

	private JPanel panelNorte;
	private JLabel labelTitulo;

	// =====================================
	// Panel centro
	// =====================================

	private JPanel panelCentro;

	private JPanel panelOrdenesTrabajoRecogida;
	private JButton botonOrdenesTrabajoRecogida;

	private JPanel panelOrdenesTrabajoEmpaquetado;
	private JButton botonOrdenesTrabajoEmpaquetado;

	private JPanel panelTipoDePago;
	private JButton botonTipoPago;

	private JPanel panelTipoCliente;
	private JButton botonTipoCliente;

	// =====================================
	// Panel sur
	// =====================================

	private JPanel panelSur;
	private JButton botonAtras;

	/**
	 * Create the panel.
	 * 
	 */
	public PanelSeleccionInforme() {
		super();

		setPreferredSize(new Dimension(816, 646));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	// ===================================================
	// Panel norte
	// ===================================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			panelNorte.add(getLabelTitulo());
		}

		return panelNorte;
	}

	private JLabel getLabelTitulo() {
		if (labelTitulo == null) {
			labelTitulo = new JLabel("Informes");
			labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		}

		return labelTitulo;
	}

	// ===================================================
	// Panel centro
	// ===================================================

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setLayout(new GridLayout(4, 0, 0, 0));

			panelCentro.add(getPanelOrdenesTrabajoRecogida());
			panelCentro.add(getPanelOrdenesTrabajoEmpaquetado());
			panelCentro.add(getPanelTipoDePago());
			panelCentro.add(getPanelTipoCliente());
		}

		return panelCentro;
	}

	// ------------------------------------------
	// Panel ordenes de trabajo de recogida
	// ------------------------------------------

	private JPanel getPanelOrdenesTrabajoRecogida() {
		if (panelOrdenesTrabajoRecogida == null) {
			panelOrdenesTrabajoRecogida = new JPanel();

			FlowLayout fl_panelOrdenesTrabajoRecogida = (FlowLayout) panelOrdenesTrabajoRecogida.getLayout();
			fl_panelOrdenesTrabajoRecogida.setVgap(45);

			panelOrdenesTrabajoRecogida.add(getBotonOrdenesTrabajoRecogida());
		}

		return panelOrdenesTrabajoRecogida;
	}

	private JButton getBotonOrdenesTrabajoRecogida() {
		if (botonOrdenesTrabajoRecogida == null) {
			botonOrdenesTrabajoRecogida = new JButton("\u00D3rdenes de trabajo de recogida");

			botonOrdenesTrabajoRecogida.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ventanaPrincipal.mostrarInformeRecogida();
					}

					catch (BusinessException e1) {
						Log.error("Fallo a la hora de generar el 'Informe de ordenes de trabajo de recogida'", e1);
					}
				}
			});

			botonOrdenesTrabajoRecogida.setFont(new Font("Tahoma", Font.BOLD, 15));
		}

		return botonOrdenesTrabajoRecogida;
	}

	// ------------------------------------------
	// Panel ordenes de trabajo de empaquetado
	// ------------------------------------------

	private JPanel getPanelOrdenesTrabajoEmpaquetado() {
		if (panelOrdenesTrabajoEmpaquetado == null) {
			panelOrdenesTrabajoEmpaquetado = new JPanel();

			FlowLayout fl_panelOrdenesTrabajoEmpaquetado = (FlowLayout) panelOrdenesTrabajoEmpaquetado.getLayout();
			fl_panelOrdenesTrabajoEmpaquetado.setVgap(45);

			panelOrdenesTrabajoEmpaquetado.add(getBotonOrdenesTrabajoEmpaquetado());
		}

		return panelOrdenesTrabajoEmpaquetado;
	}

	private JButton getBotonOrdenesTrabajoEmpaquetado() {
		if (botonOrdenesTrabajoEmpaquetado == null) {
			botonOrdenesTrabajoEmpaquetado = new JButton("\u00D3rdenes de Trabajo de empaquetado");

			botonOrdenesTrabajoEmpaquetado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			botonOrdenesTrabajoEmpaquetado.setFont(new Font("Tahoma", Font.BOLD, 15));
		}

		return botonOrdenesTrabajoEmpaquetado;
	}

	// ------------------------------------------
	// Panel tipo de pago
	// ------------------------------------------

	private JPanel getPanelTipoDePago() {
		if (panelTipoDePago == null) {
			panelTipoDePago = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelTipoDePago.getLayout();
			flowLayout.setVgap(45);

			panelTipoDePago.add(getBotonTipoPago());
		}

		return panelTipoDePago;
	}

	private JButton getBotonTipoPago() {
		if (botonTipoPago == null) {
			botonTipoPago = new JButton("Tipo de pago");

			botonTipoPago.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			botonTipoPago.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return botonTipoPago;
	}

	// ------------------------------------------
	// Panel tipo de cliente
	// ------------------------------------------

	private JPanel getPanelTipoCliente() {
		if (panelTipoCliente == null) {
			panelTipoCliente = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelTipoCliente.getLayout();
			flowLayout.setVgap(45);

			panelTipoCliente.add(getBotonTipoCliente());
		}

		return panelTipoCliente;
	}

	private JButton getBotonTipoCliente() {
		if (botonTipoCliente == null) {
			botonTipoCliente = new JButton("Tipo de cliente");

			botonTipoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			botonTipoCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		}

		return botonTipoCliente;
	}

	// ===================================================
	// Panel sur
	// ===================================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			FlowLayout fl_panelSur = (FlowLayout) panelSur.getLayout();

			fl_panelSur.setVgap(10);
			fl_panelSur.setHgap(10);
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

	// ===================================================
	// Controlar el estado del panel
	// ===================================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}