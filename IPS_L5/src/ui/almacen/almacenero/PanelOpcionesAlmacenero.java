package ui.almacen.almacenero;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Almacenero;
import ui.almacen.VentanaPrincipalAlmacenero;

public class PanelOpcionesAlmacenero extends JPanel {

	private static final long serialVersionUID = -150397767532128L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private JButton botonGenerar;
	private JButton botonRetomar;
	private JTextField textField;
	private JButton botonLogIn;
	private JButton botonEnviar;

	public PanelOpcionesAlmacenero() {
		super();

		setPreferredSize(new Dimension(300, 400));
		colocarLayout();

		GridBagConstraints gbc_textField = new GridBagConstraints();

		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;

		add(getTextField(), gbc_textField);

		GridBagConstraints gbc_botonLogIn = new GridBagConstraints();

		gbc_botonLogIn.fill = GridBagConstraints.BOTH;
		gbc_botonLogIn.insets = new Insets(0, 0, 5, 5);
		gbc_botonLogIn.gridx = 3;
		gbc_botonLogIn.gridy = 1;

		add(getBotonLogIn(), gbc_botonLogIn);

		// ====== botonGenerar ======

		GridBagConstraints gbc_botonGenerar = new GridBagConstraints();
		gbc_botonGenerar.gridwidth = 3;

		gbc_botonGenerar.fill = GridBagConstraints.BOTH;
		gbc_botonGenerar.insets = new Insets(0, 0, 5, 5);
		gbc_botonGenerar.gridx = 1;
		gbc_botonGenerar.gridy = 3;

		add(getBotonGenerar(), gbc_botonGenerar);

		// ====== botonRetomar ======

		GridBagConstraints gbc_botonRetomar = new GridBagConstraints();
		gbc_botonRetomar.gridwidth = 3;

		gbc_botonRetomar.fill = GridBagConstraints.BOTH;
		gbc_botonRetomar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRetomar.gridx = 1;
		gbc_botonRetomar.gridy = 5;

		add(getBotonRetomar(), gbc_botonRetomar);
		GridBagConstraints gbc_botonEnviar = new GridBagConstraints();
		gbc_botonEnviar.fill = GridBagConstraints.BOTH;
		gbc_botonEnviar.gridwidth = 3;
		gbc_botonEnviar.insets = new Insets(0, 0, 5, 5);
		gbc_botonEnviar.gridx = 1;
		gbc_botonEnviar.gridy = 7;
		add(getBotonEnviar(), gbc_botonEnviar);
	}

	private void colocarLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 10, 110, 10, 100, 10, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 40, 20, 50, 50, 50, 50, 50, 93, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

		setLayout(gridBagLayout);
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
			textField.setHorizontalAlignment(SwingConstants.LEFT);
			textField.setColumns(10);
		}
		return textField;
	}

	private JButton getBotonLogIn() {
		if (botonLogIn == null) {
			botonLogIn = new JButton("log in");

			botonLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						// Quiere loguearse
						if (ventanaPrincipal.getAlmacenero() == null) {

							if (getTextField().getText() != null && getTextField().getText() != "") {
								Almacenero almacenero = ServiceFactory.getAlmaceneroService()
										.login(getTextField().getText());

								if (almacenero != null) {
									login(almacenero);
								}
							}
						}

						// Quiere hacer logout
						else {
							logout();
						}
					}

					catch (BusinessException excep) {
						ventanaPrincipal.gestionarErrorConexion(excep);
					}
				}
			});

			botonLogIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonLogIn;
	}

	private void login(Almacenero almacenero) {
		ventanaPrincipal.login(almacenero);
		getTextField().setEditable(false);

		botonesBloqueados(true);
		botonLogIn.setText("Logout");
	}

	private void logout() {
		ventanaPrincipal.logout();
		getTextField().setEditable(true);

		botonesBloqueados(false);
		botonLogIn.setText("Login");
	}

	public void botonesBloqueados(boolean bloquear) {
		botonGenerar.setEnabled(bloquear);
		botonRetomar.setEnabled(bloquear);
		botonEnviar.setEnabled(bloquear);
	}

	// =================================================

	// =================================================

	private JButton getBotonGenerar() {
		if (botonGenerar == null) {
			botonGenerar = new JButton("Generar Orden de Trabajo");
			botonGenerar.setEnabled(false);

			botonGenerar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ventanaPrincipal.mostrarPanelSeleccionPedidos();
				}
			});

			botonGenerar.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonGenerar;
	}

	private JButton getBotonRetomar() {
		if (botonRetomar == null) {
			botonRetomar = new JButton("Retomar Orden de Trabajo");
			botonRetomar.setEnabled(false);

			botonRetomar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ventanaPrincipal.mostrarPanelOrdenesTrabajoRetomar();
					} catch (BusinessException excep) {
						ventanaPrincipal.gestionarErrorConexion(excep);
					}
				}
			});

			botonRetomar.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonRetomar;
	}

	private JButton getBotonEnviar() {
		if (botonEnviar == null) {
			botonEnviar = new JButton("Preparar env\u00EDo");
			
			botonEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ventanaPrincipal.mostrarPanelEnvioPaquetes();
					}
					
					catch (BusinessException excep) {
						ventanaPrincipal.gestionarErrorConexion(excep);
					}
				}
			});

			botonEnviar.setEnabled(false);
			botonEnviar.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonEnviar;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}