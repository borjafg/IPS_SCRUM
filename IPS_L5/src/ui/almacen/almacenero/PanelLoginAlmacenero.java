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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Almacenero;
import ui.almacen.GestionAlmacen;

public class PanelLoginAlmacenero extends JPanel {

	private static final long serialVersionUID = -9924654007024L;

	private GestionAlmacen ventanaPrincipal;

	private JLabel labelAlmacenero;
	private JTextField textFieldAlmacenero;
	private JButton botonLogin;
	private JLabel labelResultado;

	/**
	 * Constructor sin parámetros de la clase PanelLoginAlmacenero
	 * 
	 */
	public PanelLoginAlmacenero(GestionAlmacen ventanaPrincipal) {
		super();

		this.ventanaPrincipal = ventanaPrincipal;
		
		setPreferredSize(new Dimension(374, 530));
		colocarLayout();

		// -------------------------------------
		// Añadir componentes dentro del layout
		// -------------------------------------

		// ====== labelAlmacenero ======

		GridBagConstraints gbc_labelAlmacenero = new GridBagConstraints();
		gbc_labelAlmacenero.fill = GridBagConstraints.BOTH;

		gbc_labelAlmacenero.insets = new Insets(0, 0, 5, 5);
		gbc_labelAlmacenero.gridx = 1;
		gbc_labelAlmacenero.gridy = 1;

		add(getLabelAlmacenero(), gbc_labelAlmacenero);

		// ====== textFieldAlmacenero ======

		GridBagConstraints gbc_textFieldAlmacenero = new GridBagConstraints();

		gbc_textFieldAlmacenero.fill = GridBagConstraints.BOTH;
		gbc_textFieldAlmacenero.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAlmacenero.gridx = 1;
		gbc_textFieldAlmacenero.gridy = 3;

		add(getTextFieldAlmacenero(), gbc_textFieldAlmacenero);

		// ====== botonLogin ======

		GridBagConstraints gbc_botonLogin = new GridBagConstraints();

		gbc_botonLogin.fill = GridBagConstraints.BOTH;
		gbc_botonLogin.insets = new Insets(0, 0, 5, 5);
		gbc_botonLogin.gridx = 1;
		gbc_botonLogin.gridy = 5;

		add(getBotonLogin(), gbc_botonLogin);
		
		// ====== labelResultado ======
		
		GridBagConstraints gbc_labelResultado = new GridBagConstraints();
		
		gbc_labelResultado.fill = GridBagConstraints.BOTH;
		gbc_labelResultado.insets = new Insets(0, 0, 5, 5);
		gbc_labelResultado.gridx = 1;
		gbc_labelResultado.gridy = 7;
		
		add(getLabelResultado(), gbc_labelResultado);
	}

	/**
	 * Crea el layoyut del JPanel
	 * 
	 */
	private void colocarLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 60, 220, 60, 0 };
		gridBagLayout.rowHeights = new int[] { 71, 50, 10, 50, 60, 60, 80, 80, 120, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

		setLayout(gridBagLayout);
	}

	// ===============================================
	// Componentes del Panel
	// ===============================================

	private JLabel getLabelAlmacenero() {
		if (labelAlmacenero == null) {
			labelAlmacenero = new JLabel("Nombre de usuario");
			labelAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			labelAlmacenero.setFont(new Font("Tahoma", Font.BOLD, 21));
		}

		return labelAlmacenero;
	}

	private JTextField getTextFieldAlmacenero() {
		if (textFieldAlmacenero == null) {
			textFieldAlmacenero = new JTextField();
			textFieldAlmacenero.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textFieldAlmacenero.setColumns(10);
		}

		return textFieldAlmacenero;
	}

	private JButton getBotonLogin() {
		if (botonLogin == null) {
			botonLogin = new JButton("Log in");

			botonLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String user = textFieldAlmacenero.getText();

					if (user != null && !user.equals("")) {
						try {
							Almacenero almacenero = ServiceFactory.getAlmaceneroService().login(user);

							if (almacenero != null) {
								ventanaPrincipal.login(almacenero);
								reiniciarPanel();
							}
							
							else {
								getLabelResultado().setText("Usuario incorrecto");
							}
						} catch (BusinessException excep) {
							getLabelResultado().setText("Error de conexión");
						}
					}
				}
			});

			botonLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		}

		return botonLogin;

	}

	private void reiniciarPanel() {
		getLabelResultado().setText("");
		getTextFieldAlmacenero().setText("");
	}
	
	private JLabel getLabelResultado() {
		if (labelResultado == null) {
			labelResultado = new JLabel("");
			labelResultado.setFont(new Font("Tahoma", Font.BOLD, 16));
			labelResultado.setForeground(new Color(220, 20, 60));
			labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		return labelResultado;
	}
}