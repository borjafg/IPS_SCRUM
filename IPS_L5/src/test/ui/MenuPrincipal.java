package test.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import test.business.TestExecutor;
import test.business.acciones.ProbarAccesoMapeador;
import test.business.logica.CargadorComponentes;
import test.ui.panelesCentro.PanelCreacionCategorias;
import test.ui.panelesCentro.PanelCreacionProductos;
import test.ui.panelesCentro.PanelCreacionUsuarios;

import javax.swing.JTextField;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	
	//====================
	// Logica para los test
	//======================
	private CargadorComponentes cargadorComp;
	
	
	// =====================
	// Opciones test
	// =====================

	private TestExecutor procesadorTest;

	public static final String TEST_1_ACCESO = "Probar acceso a la base de datos";
	public static final String TEST_2_CREAR_PRODUCTO = "Crear un nuevo producto";
	public static final String TEST_3_NUEVO_CLIENTE = "Añadir un nuevo cliente";

	// =====================
	// Content pane
	// =====================

	private JPanel contentPane;

	// =====================
	// Panel norte
	// =====================

	private JPanel panelEste;

	// =====================
	// Paneles centro
	// =====================

	private JPanel panelCentro;
	private PanelCreacionProductos panelCreacionProductos;
	private PanelCreacionUsuarios panelCreacionUsuarios;
	private PanelCreacionCategorias panelCreacionCategorias;


	// Panel resultados

	private JPanel panelResultados;
	private JPanel panelResultados_norte;
	private JTextArea textAreaResultados;
	private JLabel labelResultado;
	private JButton botonAccesoBaseDatos;
	private JButton botonAñadirUsuario;
	private JButton botonConfirmarPago;
	private JButton botonAñadirProducto;
	private JScrollPane scrollPaneResultados;
	private JButton botonAñadirCategorias;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		procesadorTest = new TestExecutor();
		cargadorComp = new CargadorComponentes();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 620);

		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelEste(), BorderLayout.EAST);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
	}
	
	public CargadorComponentes getCargadorComponentes(){
		return cargadorComp;
	}
	
	public TestExecutor getProcesadorTest(){
		return procesadorTest;
	}

	public JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBorder(new LineBorder(new Color(192, 192, 192)));
			panelCentro.setLayout(new CardLayout(5, 0));

			añadirPanelesCentro();
		}

		return panelCentro;
	}

	/**
	 * Añade al panel del centro los paneles que se asocian con test
	 * 
	 */
	private void añadirPanelesCentro() {
		panelCentro.add(getPanelResultados(), "panelResultados");
		panelCentro.add(getPanelCreacionProductos(), "panelCreacionProductos");
		panelCentro.add(getPanelCreacionUsuarios(),"panelCreacionUsuarios");
		panelCentro.add(getPanelCreacionCategorias(), "panelCreacionCategorias");
	}

	// =============================
	// Panel de resultados
	// =============================

	private JLabel getLabelResultado() {
		if (labelResultado == null) {
			labelResultado = new JLabel("Resultado de la ejecuci\u00F3n:");

			labelResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelResultado.setHorizontalTextPosition(SwingConstants.CENTER);
			labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
			labelResultado.setFont(new Font("Tahoma", Font.BOLD, 22));
		}

		return labelResultado;
	}

	private JPanel getPanelResultados_norte() {
		if (panelResultados_norte == null) {
			panelResultados_norte = new JPanel();
			panelResultados_norte.setBorder(null);
			FlowLayout flowLayout = (FlowLayout) panelResultados_norte.getLayout();
			flowLayout.setVgap(10);
			panelResultados_norte.add(getLabelResultado());
		}

		return panelResultados_norte;
	}

	private JPanel getPanelResultados() {
		if (panelResultados == null) {
			panelResultados = new JPanel();
			panelResultados.setLayout(new BorderLayout(0, 0));
			panelResultados.add(getPanelResultados_norte(), BorderLayout.NORTH);
			panelResultados.add(getScrollPaneResultados(), BorderLayout.CENTER);
		}

		return panelResultados;
	}

	private JScrollPane getScrollPaneResultados() {
		if (scrollPaneResultados == null) {
			scrollPaneResultados = new JScrollPane(getTextAreaResultados(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneResultados;
	}

	public JTextArea getTextAreaResultados() {
		if (textAreaResultados == null) {
			textAreaResultados = new JTextArea();

			textAreaResultados.setWrapStyleWord(true);
			textAreaResultados.setLineWrap(true);
			textAreaResultados.setName("");
			textAreaResultados.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textAreaResultados.setText("Todav\u00EDa no se ha realizado ninguna operaci\u00F3n");
			textAreaResultados.setFont(new Font("Calibri", Font.BOLD, 23));
		}

		return textAreaResultados;
	}

	// =============================
	// Paneles del centro
	// =============================

	private PanelCreacionProductos getPanelCreacionProductos() {
		if (panelCreacionProductos == null) {
			panelCreacionProductos = new PanelCreacionProductos();
			panelCreacionProductos.setVentanaPrincipal(this);
		}

		return panelCreacionProductos;
	}
	
	
	private PanelCreacionUsuarios getPanelCreacionUsuarios(){
		if(panelCreacionUsuarios == null){
			panelCreacionUsuarios = new PanelCreacionUsuarios();
			panelCreacionUsuarios.setVentanaPrincipal(this);
		}
		return panelCreacionUsuarios;
	}
	
	
	private PanelCreacionCategorias getPanelCreacionCategorias(){
		if(panelCreacionCategorias == null){
			panelCreacionCategorias = new PanelCreacionCategorias();
			panelCreacionCategorias.setVentanaPrincipal(this);
		}
		return panelCreacionCategorias;
	}
	
	
	

	// =============================
	// Panel de acciones
	// =============================

	private JPanel getPanelEste() {
		if (panelEste == null) {
			panelEste = new JPanel();

			panelEste.setPreferredSize(new Dimension(450, 450));
			panelEste.setMinimumSize(new Dimension(450, 450));
			panelEste.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelEste.setLayout(new GridLayout(0, 1, 0, 0));
			panelEste.add(getBotonAccesoBaseDatos());
			panelEste.add(getBotonAñadirUsuario());
			panelEste.add(getBotonAñadirProducto());
			panelEste.add(getBotonAñadirCategorias());
			panelEste.add(getBotonConfirmarPago());
		}

		return panelEste;
	}

	private JButton getBotonAccesoBaseDatos() {
		if (botonAccesoBaseDatos == null) {
			botonAccesoBaseDatos = new JButton(TEST_1_ACCESO);
			botonAccesoBaseDatos.setFont(new Font("Tahoma", Font.BOLD, 21));

			botonAccesoBaseDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String resultado = procesadorTest.ejecutarPrueba(new ProbarAccesoMapeador());
					textAreaResultados.setText(resultado);
				}
			});

			botonAccesoBaseDatos.setBorder(null);
			botonAccesoBaseDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		return botonAccesoBaseDatos;
	}

	private JButton getBotonAñadirUsuario() {
		if (botonAñadirUsuario == null) {
			botonAñadirUsuario = new JButton("A\u00F1adir nuevo usuario");
			botonAñadirUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					((CardLayout) panelCentro.getLayout()).show(panelCentro, "panelCreacionUsuarios");
					
				}
			});
			botonAñadirUsuario.setFont(new Font("Tahoma", Font.BOLD, 21));
		}

		return botonAñadirUsuario;
	}

	private JButton getBotonConfirmarPago() {
		if (botonConfirmarPago == null) {
			botonConfirmarPago = new JButton("Confirmar pago de un pedido");
			botonConfirmarPago.setFont(new Font("Tahoma", Font.BOLD, 21));
			botonConfirmarPago.setEnabled(false);
		}

		return botonConfirmarPago;
	}

	private JButton getBotonAñadirProducto() {
		if (botonAñadirProducto == null) {
			botonAñadirProducto = new JButton("A\u00F1adir nuevo producto");
			botonAñadirProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//carga de datos
					
					panelCreacionProductos.cargarDatos();//cargo datos de la base de datos
					
					
					((CardLayout) panelCentro.getLayout()).show(panelCentro, "panelCreacionProductos");
					
					
				}
			});
			botonAñadirProducto.setFont(new Font("Tahoma", Font.BOLD, 21));
		}

		return botonAñadirProducto;
	}
	private JButton getBotonAñadirCategorias() {
		if (botonAñadirCategorias == null) {
			botonAñadirCategorias = new JButton("A\u00F1adir Categorias");
			botonAñadirCategorias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					((CardLayout) panelCentro.getLayout()).show(panelCentro, "panelCreacionCategorias");
					
					
				}
			});
			botonAñadirCategorias.setFont(new Font("Tahoma", Font.BOLD, 21));
		}
		return botonAñadirCategorias;
	}
}