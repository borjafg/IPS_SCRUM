package ui.almacen.almacenero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import business.exception.BusinessException;
import ui.almacen.VentanaPrincipalAlmacenero;

public class PanelOpcionesAlmacenero extends JPanel {

	private static final long serialVersionUID = -150397767532128L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private JButton botonGenerar;
	private JButton botonRetomar;
	private JButton botonEmpaquetar;
	private JButton botonLogout;
	private JLabel labelErrores;

	public PanelOpcionesAlmacenero() {
		super();

		setPreferredSize(new Dimension(374, 530));
		colocarLayout();

		// -------------------------------------
		// Añadir componentes dentro del layout
		// -------------------------------------

		// ====== labelErrores ======

		GridBagConstraints gbc_labelErrores = new GridBagConstraints();

		gbc_labelErrores.fill = GridBagConstraints.BOTH;
		gbc_labelErrores.insets = new Insets(0, 0, 5, 5);
		gbc_labelErrores.gridx = 1;
		gbc_labelErrores.gridy = 1;

		add(getLabelErrores(), gbc_labelErrores);

		// ====== botonGenerar ======

		GridBagConstraints gbc_botonGenerar = new GridBagConstraints();

		gbc_botonGenerar.fill = GridBagConstraints.BOTH;
		gbc_botonGenerar.insets = new Insets(0, 0, 5, 5);
		gbc_botonGenerar.gridx = 1;
		gbc_botonGenerar.gridy = 3;

		add(getBotonGenerar(), gbc_botonGenerar);

		// ====== botonRetomar ======

		GridBagConstraints gbc_botonRetomar = new GridBagConstraints();

		gbc_botonRetomar.fill = GridBagConstraints.BOTH;
		gbc_botonRetomar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRetomar.gridx = 1;
		gbc_botonRetomar.gridy = 5;

		add(getBotonRetomar(), gbc_botonRetomar);

		// ====== botonEmpaquetar ======

		GridBagConstraints gbc_botonEmpaquetar = new GridBagConstraints();

		gbc_botonEmpaquetar.fill = GridBagConstraints.BOTH;
		gbc_botonEmpaquetar.insets = new Insets(0, 0, 5, 5);
		gbc_botonEmpaquetar.gridx = 1;
		gbc_botonEmpaquetar.gridy = 7;

		add(getBotonEmpaquetar(), gbc_botonEmpaquetar);

		// ====== botonLogout ======

		GridBagConstraints gbc_botonLogout = new GridBagConstraints();

		gbc_botonLogout.fill = GridBagConstraints.BOTH;
		gbc_botonLogout.insets = new Insets(0, 0, 5, 5);
		gbc_botonLogout.gridx = 1;
		gbc_botonLogout.gridy = 9;

		add(getBotonLogout(), gbc_botonLogout);
	}

	private void colocarLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 30, 260, 30, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 40, 20, 50, 50, 50, 50, 50, 50, 50, 93, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };

		setLayout(gridBagLayout);
	}

	private JLabel getLabelErrores() {
		if (labelErrores == null) {
			labelErrores = new JLabel();

			labelErrores.setForeground(new Color(165, 42, 42));
			labelErrores.setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelErrores.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelErrores;
	}

	private JButton getBotonGenerar() {
		if (botonGenerar == null) {
			botonGenerar = new JButton("Generar Orden de Trabajo");

			botonGenerar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						ventanaPrincipal.mostrarPanelSeleccionPedidos();
						reiniciar();
				}
			});

			botonGenerar.setFont(new Font("Tahoma", Font.BOLD, 17));
		}

		return botonGenerar;
	}

	private JButton getBotonRetomar() {
		if (botonRetomar == null) {
			botonRetomar = new JButton("Retomar Orden de Trabajo");

			botonRetomar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ventanaPrincipal.mostrarPanelOrdenesTrabajoRetomar();
						reiniciar();
					} catch (BusinessException excep) {
						mostrarErrorConexion();
					}
				}
			});

			botonRetomar.setFont(new Font("Tahoma", Font.BOLD, 17));
		}

		return botonRetomar;
	}

	private JButton getBotonEmpaquetar() {
		if (botonEmpaquetar == null) {
			botonEmpaquetar = new JButton("Empaquetar Orden de Trabajo");

			botonEmpaquetar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ventanaPrincipal.mostrarPanelOrdenesTrabajoEmpaquetar();
						reiniciar();
					} catch (BusinessException excep) {
						mostrarErrorConexion();
					}
				}
			});

			botonEmpaquetar.setFont(new Font("Tahoma", Font.BOLD, 17));
		}

		return botonEmpaquetar;
	}

	private JButton getBotonLogout() {
		if (botonLogout == null) {
			botonLogout = new JButton("Cerrar sesi\u00F3n");

			botonLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciar();
					ventanaPrincipal.logout();
				}
			});

			botonLogout.setFont(new Font("Tahoma", Font.BOLD, 17));
		}

		return botonLogout;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public void mostrarErrorConexion() {
		getLabelErrores().setText("Error de conexi\u00F3n");
	}

	private void reiniciar() {
		getLabelErrores().setText("");
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}