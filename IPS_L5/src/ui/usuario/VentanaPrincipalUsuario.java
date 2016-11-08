package ui.usuario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.types.MetodosPago;
import persistence.CategoriaFinder;
import ui.usuario.logica.LogicaVentanaPrincipalUsuario;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;
import business.exception.BusinessException;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class VentanaPrincipalUsuario extends JFrame {

	private static final long serialVersionUID = -457381L;

	// Hace todas las llamadas a los métodos de la lógica
	private LogicaVentanaPrincipalUsuario logVOUser;

	// Modelos de las listas
	private DefaultListModel<Producto> modeloListaProductos;
	private DefaultListModel<Categoria> modeloListaCategorias;

	// modelo Combo box metodos de pago
	private DefaultComboBoxModel<MetodosPago> modeloComboMetodosDePago;

	// modelo de la cesta
	private DefaultListModel<ModeloProductosPedidos> modeloListaCesta;

	/// Valor que nos dice si un usuario está registrado
	private boolean usuarioReg;
	
	
	
	///
	
	private String nombre ;//nombre usuario registrado

	///
	private JPanel contentPane;
	private JPanel panelPrincipal;
	private JPanel panelCesta;
	private JPanel panelProductos;
	private JLabel lblProductos;
	private JLabel lblCesta;
	private JPanel panelSurBotones;
	private JPanel panelNorteTitulo;
	private JScrollPane scrollPaneCesta;
	private JButton btnAceptarPedido;
	private JPanel panelCentro;
	private JScrollPane scrollPaneProductos;
	private JLabel lblTitulo;
	private JPanel panelGastosTotales;
	private JLabel lblGastoTotal;
	private JTextField textGastoTotal;
	private JPanel panelBase;
	private JPanel panelAceparPedidoNoRegistrados;
	private JPanel panelBotonesAceptarPedido;
	private JButton btnCancelarPedido;
	private JButton btnFinalizarPedido;
	private JList<ModeloProductosPedidos> listCesta;
	private JList<Producto> listProductos;
	private JPanel panelAceptarPedidoBase;
	private JPanel panelAuxAceptarPedido1;
	private JPanel panelDireccionCliente;
	private JPanel panelMetodoPagoCliente;
	private JLabel lblDireccionCliente;
	private JTextField textFieldDireccionCliente;
	private JLabel lblMetodoPagoCliente;

	private JPanel panelProductosContenedor;
	private JLabel lblNombreCliente;
	private JTextField textFieldNombreCliente;
	private JComboBox<MetodosPago> comboBox;
	private JPanel panelCesta2;
	private JPanel panelBotonQuitar;
	private JButton btnQuitar;
	private JPanel panelAñadirProductos;
	private JButton btnAñadir;
	private JTextField textFieldUnidadesProducto;
	private JPanel panelAuxiliar;
	private JPanel panelListasCategoriasYProductos;
	private JPanel panelCategorias;
	private JScrollPane scrollPaneCategorias;
	private JList<Categoria> listCategorias;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenuItem mntmIniciarSesin;
	private JSeparator separator;
	private JMenuItem mntmCerrarSesin;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalUsuario frame = new VentanaPrincipalUsuario();

					frame.setVisible(true); // lo ultimo
					System.out.println("pase por set visible");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public VentanaPrincipalUsuario() {

		usuarioReg = false;

		logVOUser = new LogicaVentanaPrincipalUsuario();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 914, 580);
		setJMenuBar(getMenuBar_1());

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelBase(), BorderLayout.CENTER);

		setTitle("Tienda");
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new JPanel();

			panelPrincipal.setLayout(new BorderLayout(0, 0));
			panelPrincipal.add(getPanel_4(), BorderLayout.NORTH);
			panelPrincipal.add(getPanel_1_1(), BorderLayout.CENTER);

			panelPrincipal.add(getPanel_3(), BorderLayout.SOUTH);
		}

		return panelPrincipal;
	}

	private JPanel getPanelCesta() {
		if (panelCesta == null) {
			panelCesta = new JPanel();

			panelCesta.setLayout(new BorderLayout(0, 0));
			panelCesta.add(getLblCesta(), BorderLayout.NORTH);

			panelCesta.add(getPanelGastosTotales(), BorderLayout.SOUTH);
			panelCesta.add(getPanelCesta2(), BorderLayout.WEST);
		}

		return panelCesta;
	}

	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();

			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getLblProductos(), BorderLayout.NORTH);
			panelProductos.add(getPanelProductosContenedor(), BorderLayout.CENTER);
			panelProductos.add(getPanelAuxiliar(), BorderLayout.EAST);
		}

		return panelProductos;
	}

	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos");
		}

		return lblProductos;
	}

	private JLabel getLblCesta() {
		if (lblCesta == null) {
			lblCesta = new JLabel("Cesta");
		}

		return lblCesta;
	}

	private JPanel getPanel_3() {
		if (panelSurBotones == null) {
			panelSurBotones = new JPanel();

			panelSurBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panelSurBotones.add(getBtnAceptarPedido());
		}

		return panelSurBotones;
	}

	private JPanel getPanel_4() {
		if (panelNorteTitulo == null) {
			panelNorteTitulo = new JPanel();
			panelNorteTitulo.add(getLblTitulo());
		}

		return panelNorteTitulo;
	}

	private JScrollPane getScrollPaneCesta() {
		if (scrollPaneCesta == null) {
			scrollPaneCesta = new JScrollPane(getListCesta());

			scrollPaneCesta.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCesta.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneCesta;
	}

	/**
	 * El action Performed, nos permite pasar a la pestaña para aceptar el
	 * pedido El boton aceptar, se pondra enbled cuando el carrito tenga al
	 * menos un elemento
	 * 
	 * @return
	 * 
	 */
	private JButton getBtnAceptarPedido() {
		if (btnAceptarPedido == null) {
			btnAceptarPedido = new JButton("Comprar");

			btnAceptarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Mientras tanto, para probar todo
					// poner un if para ver si la cesta esta vacia
					if (modeloListaCesta.isEmpty()) {
						JOptionPane.showMessageDialog(getBtnAceptarPedido(),
								"Para confirmar pedido, la cesta tiene que tener por lo menos un artÃ­culo", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// En caso de que el usuario este registrado, se hace la
						// compra directamente

						// Aqui luego ira si se a registrado un usuario
						if(usuarioReg){//usuario Registrado
							
							//realizamos la subida de datos a la base de datos
							
							
							//borramos las acciones
							modeloListaCesta = logVOUser.resetear();

							resetearCamposDeTexto(); // Se eliminan todos los campos
														// de texto de la
														// aplicación

							getListCesta().setModel(modeloListaCesta);
						}else{
						
						
						getMntmCerrarSesin().setEnabled(false);
						getMntmIniciarSesin().setEnabled(false);
						((CardLayout) panelBase.getLayout()).show(panelBase, "panelAceptarPedido");
						}

					}
					// Esto se hace para evitar que alguien escriba una cantidad
					// y se pueda pasar ese valor a un siguiente usuario o a una
					// nueva iteración
					// se pone a uno el nÃºmero de unidades tras la compra
					getTextFieldUnidadesProducto().setText("1");
					// se eliminan las selecciones de la lista
					getListProductos().clearSelection();
					getListCesta().clearSelection();
				}
			});

			btnAceptarPedido.setHorizontalAlignment(SwingConstants.RIGHT);
		}

		return btnAceptarPedido;
	}

	private JPanel getPanel_1_1() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setLayout(new BorderLayout(0, 0));
			panelCentro.add(getPanelCesta(), BorderLayout.EAST);
			// panelCentro.add(getPanelProductos(), BorderLayout.CENTER);
			panelCentro.add(getPanelListasCategoriasYProductos(), BorderLayout.CENTER);
		}

		return panelCentro;
	}

	private JScrollPane getScrollPaneProductos() {// meter card layout
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane(getListProductos());

			scrollPaneProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneProductos;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Tienda de compras");
		}

		return lblTitulo;
	}

	private JPanel getPanelGastosTotales() {
		if (panelGastosTotales == null) {
			panelGastosTotales = new JPanel();

			panelGastosTotales.add(getLblGastoTotal());
			panelGastosTotales.add(getTextGastoTotal());
		}

		return panelGastosTotales;
	}

	private JLabel getLblGastoTotal() {
		if (lblGastoTotal == null) {
			lblGastoTotal = new JLabel("Gasto :");
		}

		return lblGastoTotal;
	}

	private JTextField getTextGastoTotal() {
		if (textGastoTotal == null) {
			textGastoTotal = new JTextField();
			textGastoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
			textGastoTotal.setText("0.0");
			textGastoTotal.setEditable(false);
			textGastoTotal.setColumns(10);
		}

		return textGastoTotal;
	}

	/**
	 * Este es el panel sobre el que se colocan los paneles de la tienda y el de
	 * aceptar pedido
	 * 
	 * @return
	 */
	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();

			panelBase.setLayout(new CardLayout(0, 0));

			// se añade el panel de la tienda al card layout
			panelBase.add(getPanelPrincipal(), "panelPrincipal");

			// sen añade el panel de aceptar pedido al card layout
			panelBase.add(getPanelAceparPedidoNoRegistrados(), "panelAceptarPedido");
		}

		return panelBase;
	}

	private JPanel getPanelAceparPedidoNoRegistrados() {
		if (panelAceparPedidoNoRegistrados == null) {
			panelAceparPedidoNoRegistrados = new JPanel();

			panelAceparPedidoNoRegistrados.setLayout(new BorderLayout(0, 0));

			panelAceparPedidoNoRegistrados.add(getPanelBotonesAceptarPedido(), BorderLayout.SOUTH);
			panelAceparPedidoNoRegistrados.add(getPanelAceptarPedidoBase(), BorderLayout.CENTER);
		}

		return panelAceparPedidoNoRegistrados;
	}

	private JPanel getPanelBotonesAceptarPedido() {
		if (panelBotonesAceptarPedido == null) {
			panelBotonesAceptarPedido = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonesAceptarPedido.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);

			panelBotonesAceptarPedido.add(getBtnCancelarPedido());
			panelBotonesAceptarPedido.add(getBtnFinalizarPedido());
		}

		return panelBotonesAceptarPedido;
	}

	/**
	 * 
	 * El action performed, nos devuelve a la pestaña de la tienda.
	 * 
	 * @return JButton
	 * 
	 */
	private JButton getBtnCancelarPedido() {
		if (btnCancelarPedido == null) {
			btnCancelarPedido = new JButton("Cancelar");

			btnCancelarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					getMntmIniciarSesin().setEnabled(true);
					((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
				}
			});
		}

		return btnCancelarPedido;
	}

	private JButton getBtnFinalizarPedido() {
		if (btnFinalizarPedido == null) {
			btnFinalizarPedido = new JButton("Aceptar");

			btnFinalizarPedido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// validar que los campos sean correctos
					String textoDireccion = getTextFieldDireccionCliente().getText();
					// MetodosPago comboMetodo = (MetodosPago)
					// getComboBox().getSelectedItem();
					String textoNombre = getTextFieldNombreCliente().getText();
					if (textoDireccion.equals("") || textoNombre.equals("")) {
						JOptionPane.showMessageDialog(getBtnFinalizarPedido(), "Rellene los campos indicados", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// subir a la base de datos y reiniciar la aplicacion;
						try {
							logVOUser.generarTodo(textoDireccion, textoNombre, (MetodosPago) getComboBox().getSelectedItem());
						} catch (BusinessException e1) {
							System.err.println(e1.getMessage());
						}
						modeloListaCesta = logVOUser.resetear();

						resetearCamposDeTexto(); // Se eliminan todos los campos
													// de texto de la
													// aplicación

						getListCesta().setModel(modeloListaCesta);

						// Cuando este el log-in, se "cerrarÃ¡ la sesión"

						((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
					}
				}
			});
		}

		return btnFinalizarPedido;
	}

	private void resetearCamposDeTexto() {
		getTextFieldDireccionCliente().setText("");
		getTextFieldNombreCliente().setText("");
		getTextGastoTotal().setText("0.0");
	}

	private JList<ModeloProductosPedidos> getListCesta() {
		if (listCesta == null) {
			modeloListaCesta = new DefaultListModel<ModeloProductosPedidos>();

			listCesta = new JList<ModeloProductosPedidos>(modeloListaCesta);

			listCesta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCesta.putClientProperty("List.isFileList", Boolean.TRUE);
		}

		return listCesta;
	}

	/*
	 * La lista que contiene los productos de la categoría seleccionada
	 * 
	 */
	private JList<Producto> getListProductos() {
		if (listProductos == null) {

			// La lista se crea sin modelo, para que una vez que se selecciona
			// una categoria que contiene productos, se cargue un nuevo modelo
			// modeloListaProductos = logVOUser.getModeloListaProductos();
			modeloListaProductos = new DefaultListModel<Producto>();

			listProductos = new JList<Producto>(modeloListaProductos);

			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listProductos.putClientProperty("List.isFileList", Boolean.TRUE);
		}

		return listProductos;
	}

	private JPanel getPanelAceptarPedidoBase() {
		if (panelAceptarPedidoBase == null) {
			panelAceptarPedidoBase = new JPanel();

			panelAceptarPedidoBase.setLayout(new GridLayout(5, 0, 0, 0));

			panelAceptarPedidoBase.add(getPanelAuxAceptarPedido1());
			panelAceptarPedidoBase.add(getPanelDireccionCliente());
			panelAceptarPedidoBase.add(getPanelMetodoPagoCliente());
		}

		return panelAceptarPedidoBase;
	}

	private JPanel getPanelAuxAceptarPedido1() {
		if (panelAuxAceptarPedido1 == null) {
			panelAuxAceptarPedido1 = new JPanel();

			panelAuxAceptarPedido1.add(getLblNombreCliente());
			panelAuxAceptarPedido1.add(getTextFieldNombreCliente());
		}

		return panelAuxAceptarPedido1;
	}

	private JPanel getPanelDireccionCliente() {
		if (panelDireccionCliente == null) {
			panelDireccionCliente = new JPanel();

			panelDireccionCliente.add(getLblDireccionCliente());
			panelDireccionCliente.add(getTextFieldDireccionCliente());
		}

		return panelDireccionCliente;
	}

	private JPanel getPanelMetodoPagoCliente() {
		if (panelMetodoPagoCliente == null) {
			panelMetodoPagoCliente = new JPanel();
			panelMetodoPagoCliente.add(getLblMetodoPagoCliente());
			panelMetodoPagoCliente.add(getComboBox());
		}

		return panelMetodoPagoCliente;
	}

	private JLabel getLblDireccionCliente() {
		if (lblDireccionCliente == null) {
			lblDireccionCliente = new JLabel("Direccion de envio : ");
		}

		return lblDireccionCliente;
	}

	private JTextField getTextFieldDireccionCliente() {
		if (textFieldDireccionCliente == null) {
			textFieldDireccionCliente = new JTextField();
			textFieldDireccionCliente.setColumns(10);
		}

		return textFieldDireccionCliente;
	}

	private JLabel getLblMetodoPagoCliente() {
		if (lblMetodoPagoCliente == null) {
			lblMetodoPagoCliente = new JLabel("M\u00E9todo de pago : ");
		}

		return lblMetodoPagoCliente;
	}

	private JPanel getPanelProductosContenedor() {
		if (panelProductosContenedor == null) {
			panelProductosContenedor = new JPanel();

			panelProductosContenedor.setLayout(new BorderLayout(0, 0));
			panelProductosContenedor.add(getScrollPaneProductos());
			panelProductosContenedor.add(getPanelAñadirProductos(), BorderLayout.SOUTH);
		}

		return panelProductosContenedor;
	}

	private JLabel getLblNombreCliente() {
		if (lblNombreCliente == null) {
			lblNombreCliente = new JLabel("Nombre :");
		}

		return lblNombreCliente;
	}

	private JTextField getTextFieldNombreCliente() {
		if (textFieldNombreCliente == null) {
			textFieldNombreCliente = new JTextField();
			textFieldNombreCliente.setColumns(10);
		}

		return textFieldNombreCliente;
	}

	private JComboBox<MetodosPago> getComboBox() {// Hay que modificar la
													// implementación
		if (comboBox == null) {
			MetodosPago metodosPagoNoRegistrado[] = { MetodosPago.METALICO, MetodosPago.TRANSFERENCIA};
			modeloComboMetodosDePago = new DefaultComboBoxModel<MetodosPago>(metodosPagoNoRegistrado);
			comboBox = new JComboBox<MetodosPago>();
			comboBox.setModel(modeloComboMetodosDePago);
		}

		return comboBox;
	}

	private JPanel getPanelCesta2() {
		if (panelCesta2 == null) {
			panelCesta2 = new JPanel();

			panelCesta2.setLayout(new BorderLayout(0, 0));

			panelCesta2.add(getScrollPaneCesta());
			panelCesta2.add(getPanelBotonQuitar(), BorderLayout.SOUTH);
		}

		return panelCesta2;
	}

	private JPanel getPanelBotonQuitar() {
		if (panelBotonQuitar == null) {
			panelBotonQuitar = new JPanel();
			panelBotonQuitar.add(getBtnQuitar());
		}

		return panelBotonQuitar;
	}

	private JButton getBtnQuitar() {
		if (btnQuitar == null) {
			btnQuitar = new JButton("Quitar");

			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ModeloProductosPedidos cestaSeleccionado = (ModeloProductosPedidos) getListCesta()
							.getSelectedValue();

					// puede que esto sea lo que funciona
					getListProductos().setSelectedValue(null, false);

					if (cestaSeleccionado == null) {
						JOptionPane.showMessageDialog(getBtnQuitar(),
								"Se tiene que seleccionar un producto de la cesta", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						modeloListaCesta = logVOUser.EliminarProducto(getListCesta().getSelectedIndex());

						getListCesta().setModel(modeloListaCesta);
						getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));
						getListCesta().setSelectedValue(null, false);
					}
					// se eliminan las selecciones de las listas
					getListProductos().clearSelection();
					getListCesta().clearSelection();
					// se pone a uno el valor de el campo de texto
					getTextFieldUnidadesProducto().setText("1");
				}
			});
		}

		return btnQuitar;
	}

	private JPanel getPanelAñadirProductos() {
		if (panelAñadirProductos == null) {
			panelAñadirProductos = new JPanel();
			panelAñadirProductos.add(getTextFieldUnidadesProducto());
			panelAñadirProductos.add(getBtnAñadir());

		}

		return panelAñadirProductos;
	}

	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");

			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index = getListProductos().getSelectedIndex();

					if (index == -1) {
						JOptionPane.showMessageDialog(getBtnAñadir(), "Seleccione un producto", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (!logVOUser.isnumber(getTextFieldUnidadesProducto().getText())) {
						JOptionPane.showMessageDialog(getBtnAñadir(),
								"Se tiene que escribir un nÃºmero entero positivo", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						ModeloProductosPedidos productopedido = new ModeloProductosPedidos(
								(Producto) getListProductos().getSelectedValue(),
								Integer.parseInt(getTextFieldUnidadesProducto().getText()));

						modeloListaCesta = logVOUser.sumarProductoACesta(productopedido, modeloListaCesta);
						getListCesta().setModel(modeloListaCesta);
						getTextGastoTotal().setText(String.valueOf(logVOUser.calcularPrecioTotal()));

						// Post añadir producto
						((CardLayout) panelListasCategoriasYProductos.getLayout()).show(panelListasCategoriasYProductos,
								"Panel Categorias");
						modeloListaProductos.removeAllElements();
						// se pone a uno el nÃºmero de unidades tras la compra
						getTextFieldUnidadesProducto().setText("1");
						// se eliminan las selecciones de la lista
						getListProductos().clearSelection();
						getListCesta().clearSelection();
					}

				}
			});
		}

		return btnAñadir;
	}

	private JTextField getTextFieldUnidadesProducto() {
		if (textFieldUnidadesProducto == null) {
			textFieldUnidadesProducto = new JTextField();

			textFieldUnidadesProducto.setText("1");
			textFieldUnidadesProducto.setColumns(10);
		}

		return textFieldUnidadesProducto;
	}

	private JPanel getPanelAuxiliar() {// Panel para hacer de separador
		if (panelAuxiliar == null) {
			panelAuxiliar = new JPanel();
		}
		return panelAuxiliar;
	}

	/*
	 * Panel cardLayout que incluye los paneles de los productos y el panel de
	 * las categorias
	 * 
	 * 
	 */
	private JPanel getPanelListasCategoriasYProductos() {
		if (panelListasCategoriasYProductos == null) {
			panelListasCategoriasYProductos = new JPanel();
			panelListasCategoriasYProductos.setLayout(new CardLayout(0, 0));
			// Añadimos el panel de las categorias
			panelListasCategoriasYProductos.add(getPanelCategorias(), "Panel Categorias");
			// añadimos el panel de los productos de las categorias
			panelListasCategoriasYProductos.add(getPanelProductos(), "Panel Productos");

		}
		return panelListasCategoriasYProductos;
	}

	private JPanel getPanelCategorias() {
		if (panelCategorias == null) {
			panelCategorias = new JPanel();
			panelCategorias.setLayout(new BorderLayout(0, 0));
			panelCategorias.add(getScrollPaneCategorias(), BorderLayout.CENTER);
			panelCategorias.add(getPanel(), BorderLayout.EAST);

		}
		return panelCategorias;
	}

	private JScrollPane getScrollPaneCategorias() {
		if (scrollPaneCategorias == null) {
			scrollPaneCategorias = new JScrollPane();
			scrollPaneCategorias.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCategorias.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPaneCategorias.setViewportView(getListCategorias());
		}
		return scrollPaneCategorias;
	}

	private JList<Categoria> getListCategorias() {
		if (listCategorias == null) {
			modeloListaCategorias = logVOUser.getModeloCategoriasPadre();
			listCategorias = new JList<Categoria>(modeloListaCategorias);
			listCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCategorias.putClientProperty("List.isFileList", Boolean.TRUE);
			listCategorias.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// Seleccionamos la categoria a la que se clica dos veces
					if (getListCategorias().getSelectedIndex() != -1) {
						if (arg0.getClickCount() == 2) {
							if (!logVOUser
									.comprobarQueNoHayProductosEnCategoria(getListCategorias().getSelectedValue())) {// si
																														// la
																														// categoria
																														// no
																														// contiene
																														// productos
								cargarCategorias(getListCategorias().getSelectedValue());
							} else {// si la categoria contiene productos
									// cargamos la lista de productos de esa
									// categoria
								cargarModeloListaProductos(getListCategorias().getSelectedValue());
								((CardLayout) panelListasCategoriasYProductos.getLayout())
										.show(panelListasCategoriasYProductos, "Panel Productos");
								// reiniciamos la lista de categorias
								modeloListaCategorias = logVOUser.getModeloCategoriasPadre();
								listCategorias.setModel(modeloListaCategorias);// probar
																				// sin
																				// esto
							}
							getListCategorias().clearSelection();

						}
					}
				}
			});
		}
		return listCategorias;
	}

	// Metodo que usamos para cargar las categorias
	private void cargarCategorias(Categoria categoria) {
		modeloListaCategorias.removeAllElements();
		List<Categoria> listaCategorias = logVOUser.getListaCategoriasHijas(categoria);
		for (Categoria cat : listaCategorias) {
			modeloListaCategorias.addElement(cat);
		}
	}

	private void cargarModeloListaProductos(Categoria categoria) {
		List<Producto> listaProductos = logVOUser.getListaProductos(categoria);
		for (Producto prod : listaProductos) {
			modeloListaProductos.addElement(prod);
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnUsuario());
		}
		return menuBar;
	}

	private JMenu getMnUsuario() {
		if (mnUsuario == null) {
			mnUsuario = new JMenu("Usuario");
			mnUsuario.add(getMntmIniciarSesin());
			mnUsuario.add(getSeparator());
			mnUsuario.add(getMntmCerrarSesin());
		}
		return mnUsuario;
	}

	private JMenuItem getMntmIniciarSesin() {
		if (mntmIniciarSesin == null) {
			mntmIniciarSesin = new JMenuItem("Iniciar Sesi\u00F3n");
			mntmIniciarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Sale opción para escoger el nombre del usuario registrado
					// y se inicia Sesión
					if (usuarioReg == true) {
						JOptionPane.showMessageDialog(getMntmIniciarSesin(), "Sesión ya iniciada", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String nombre = JOptionPane.showInputDialog("Introduzca su nombre");
						try {
							if (logVOUser.isUsuarioEnBase(nombre)) {
								usuarioReg = true;
								logVOUser.iniciarSesion(nombre);
								VentanaPrincipalUsuario.this.nombre = nombre;
								JOptionPane.showMessageDialog(getMntmIniciarSesin(), "Sesión iniciada como " + nombre,
										"Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
								getMntmIniciarSesin().setEnabled(false);
								getMntmCerrarSesin().setEnabled(true);
							} else {
								JOptionPane.showMessageDialog(getMntmIniciarSesin(),
										"Fallo al iniciar sesión, revise el nombre de usuario", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						} catch (HeadlessException e) {
							System.err.println(e.getMessage());
						} catch (BusinessException e) {
							System.err.println(e.getMessage());
						}
					}
				}
			});
		}
		return mntmIniciarSesin;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmCerrarSesin() {
		if (mntmCerrarSesin == null) {
			mntmCerrarSesin = new JMenuItem("Cerrar Sesi\u00F3n");
			mntmCerrarSesin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(getMntmIniciarSesin(), "Sesión "  + VentanaPrincipalUsuario.this.nombre+" cerrada.",
							"Sesión Cerrada", JOptionPane.INFORMATION_MESSAGE);
					logVOUser.cerrarSesion();
					getMntmIniciarSesin().setEnabled(true);
					getMntmCerrarSesin().setEnabled(false);
				}
			});
			mntmCerrarSesin.setEnabled(false);
		}
		return mntmCerrarSesin;
	}
}
