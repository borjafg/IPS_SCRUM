package ui.administracion.paneles.informes;

import javax.swing.JPanel;

import business.exception.BusinessException;
import infrastructure.Log;
import ui.administracion.VentanaPrincipalAdministracion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSeleccionInforme extends JPanel {
	
	
	private VentanaPrincipalAdministracion ventanaPrincipal;
	
	
	private JPanel panelBase;
	private JPanel panelCentro;
	private JPanel panelTitulo;
	private JPanel panelBotones;
	private JButton btnAtras;
	private JLabel lblTitulo;
	private JPanel panelOrdenesTrabajo;
	private JPanel panelTipoDePago;
	private JPanel panelTipoCliente;
	private JPanel panelEmpaquetado;
	private JButton btnOrdenesTrabjo;
	private JButton btnTipoPago;
	private JButton btnTipoCliente;
	private JButton btnEmpaquetado;

	/**
	 * Create the panel.
	 */
	public PanelSeleccionInforme() {
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
			panelBase.add(getPanelCentro());
			panelBase.add(getPanelTitulo(), BorderLayout.NORTH);
			panelBase.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelBase;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(4, 0, 0, 0));
			panelCentro.add(getPanelOrdenesTrabajo());
			panelCentro.add(getPanelTipoDePago());
			panelCentro.add(getPanelTipoCliente());
			panelCentro.add(getPanelEmpaquetado());
		}
		return panelCentro;
	}
	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.add(getLblTitulo());
		}
		return panelTitulo;
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
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Informes");
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblTitulo;
	}
	private JPanel getPanelOrdenesTrabajo() {
		if (panelOrdenesTrabajo == null) {
			panelOrdenesTrabajo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelOrdenesTrabajo.getLayout();
			flowLayout.setVgap(45);
			panelOrdenesTrabajo.add(getBtnOrdenesTrabjo());
		}
		return panelOrdenesTrabajo;
	}
	private JPanel getPanelTipoDePago() {
		if (panelTipoDePago == null) {
			panelTipoDePago = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelTipoDePago.getLayout();
			flowLayout.setVgap(45);
			panelTipoDePago.add(getBtnTipoPago());
		}
		return panelTipoDePago;
	}
	private JPanel getPanelTipoCliente() {
		if (panelTipoCliente == null) {
			panelTipoCliente = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelTipoCliente.getLayout();
			flowLayout.setVgap(45);
			panelTipoCliente.add(getBtnTipoCliente());
		}
		return panelTipoCliente;
	}
	private JPanel getPanelEmpaquetado() {
		if (panelEmpaquetado == null) {
			panelEmpaquetado = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelEmpaquetado.getLayout();
			flowLayout.setVgap(45);
			panelEmpaquetado.add(getBtnEmpaquetado());
		}
		return panelEmpaquetado;
	}
	private JButton getBtnOrdenesTrabjo() {
		if (btnOrdenesTrabjo == null) {
			btnOrdenesTrabjo = new JButton("Ordenes de trabajo");
			btnOrdenesTrabjo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						ventanaPrincipal.mostrarInformeRecogida();
					} catch (BusinessException e1) {
						Log.error("Fallo a la hora de generar el panel de 'Informe de ordenes de trabajo'", e1);
					}
				}
			});
			btnOrdenesTrabjo.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnOrdenesTrabjo;
	}
	private JButton getBtnTipoPago() {
		if (btnTipoPago == null) {
			btnTipoPago = new JButton("Tipo de pago");
			btnTipoPago.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnTipoPago.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnTipoPago;
	}
	private JButton getBtnTipoCliente() {
		if (btnTipoCliente == null) {
			btnTipoCliente = new JButton("Tipo de cliente");
			btnTipoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnTipoCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnTipoCliente;
	}
	private JButton getBtnEmpaquetado() {
		if (btnEmpaquetado == null) {
			btnEmpaquetado = new JButton("Empaquetado");
			btnEmpaquetado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnEmpaquetado.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return btnEmpaquetado;
	}
}
