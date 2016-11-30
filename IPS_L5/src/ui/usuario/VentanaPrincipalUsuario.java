package ui.usuario;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.Log;
import model.Categoria;
import model.Cliente;
import model.Producto;
import model.types.MetodosPago;
import model.types.TipoCliente;
import ui.usuario.logica.LogicaVentanaPrincipalUsuario;
import ui.usuario.logica.ClasesAuxiliares.ModeloProductosPedidos;
import model.types.TipoEnvio;
import model.types.TipoTarjeta;
import javax.swing.JPasswordField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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

	private String nombre;// nombre usuario registrado

	///
	private JPanel contentPane;
	private JPanel panelPrincipal;
	private JPanel panelCesta;
	private JPanel panelProductos;
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
	private JPanel panelAceparPedido;
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
	private JComboBox<MetodosPago> comboBoxMetodoPago;
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
	private JPanel panelBotonRetroceso;
	private JPanel panelBotonRetrocesoProductos;
	private JButton btnAtrasProductos;
	private JButton btnAtrasCategorias;
	private JPanel panelDatosBaseUsuario;
	private JPanel panelTarjetaUsuario;
	private JPanel panelVacio;
	private JPanel panelDatosTrajeta;
	private JPanel panelNumeroTarjeta;
	private JPanel panelFechaCaducidad;
	private JPanel panelTipoTarjeta;
	private JPanel panelCodigoSeguridad;
	private JPanel panelTipoEnvio;
	private JLabel lblTipoEnvio;
	private JComboBox<TipoEnvio> comboBoxTipoEnvioClientes;
	private JLabel lblNumeroTarjeta;
	private JTextField textFieldNumeroTarjeta;
	private JLabel lblFechaCaducidad;
	private JTextField textFieldFechaCaducidad;
	private JLabel lblTipoTarjeta;
	private JComboBox<TipoTarjeta> comboBoxTipoTarjeta;
	private JLabel lblCodigoSeguridad;
	private JPasswordField passwordFieldCodigoSeguridad;
	private JPanel panelAceptarPedidoMinorista;
	private JPanel panelBotonesDatosMinorista;
	private JButton buttonAceptarPedidoMinorista;
	private JButton buttonCancelarMinorista;
	private JPanel panelDatosMinorista;
	private JPanel panelTipoEnvioMinorista;
	private JLabel lblTipoDeEnvioMinorista;
	private JComboBox<TipoEnvio> comboBoxTipoEnvioMinorista;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalUsuario frame = new VentanaPrincipalUsuario();
					frame.cargarCategoriasPadre();
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

			panelProductos.add(getPanelProductosContenedor(), BorderLayout.CENTER);
			panelProductos.add(getPanelAuxiliar(), BorderLayout.EAST);
			panelProductos.add(getPanelBotonRetrocesoProductos(), BorderLayout.NORTH);
		}

		return panelProductos;
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
					if (modeloListaCesta.isEmpty()) {
						JOptionPane.showMessageDialog(getBtnAceptarPedido(),
								"Para confirmar pedido, la cesta tiene que tener por lo menos un artÃ­culo", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						if (usuarioReg) {// usuario Registrado

							// =============================
							// Esta parte se tiene que volver a rediseñar
							// (Reaporvechar codigo)
							// ===============================
							// realizamos la subida de datos a la base de datos

							if (logVOUser.getTipoCliente().equals(TipoCliente.MINORISTA)) {// compra
																							// se
																							// hace
																							// como
																							// usuario
																							// minorista
								getMntmCerrarSesin().setEnabled(false);
								getMntmIniciarSesin().setEnabled(false);
								((CardLayout) panelBase.getLayout()).show(panelBase, "panelAceptarPedidoMinorista");

							} else {// compra se hace como usuario particular
									// registrado

								// inicializamos el panel
								getMntmCerrarSesin().setEnabled(false);
								getMntmIniciarSesin().setEnabled(false);

								// rellenamos los valores
								rellenarCamposTexto();

								((CardLayout) panelBase.getLayout()).show(panelBase, "panelAceptarPedido");
							}

						} else {// usuarioNoRegistrado

							// inicializamos el panel
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

				}// actionperformed
			});// add ation listener

			btnAceptarPedido.setHorizontalAlignment(SwingConstants.RIGHT);
		} // == null

		return btnAceptarPedido;
	}// clase

	private void rellenarCamposTexto() {
		Cliente cli = logVOUser.getClienteReg();
		getTextFieldDireccionCliente().setText(cli.getDireccionCompleta());
		getTextFieldNombreCliente().setText(cli.getNombre());
		getTextFieldNumeroTarjeta().setText(String.valueOf(cli.getTarjeta().getNumeroTarjeta()));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		getTextFieldFechaDeCaducidad().setText(sdf.format(cli.getTarjeta().getFechaCaducidad()));

		getPasswordFieldCodigoSeguridad().setText(String.valueOf(cli.getTarjeta().getCodigoSeguridad()));

		getComboBoxTipoTarjeta().setSelectedItem(cli.getTarjeta().getTipoTarjeta());

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
	 * 
	 */
	private JPanel getPanelBase() {
		if (panelBase == null) {
			panelBase = new JPanel();

			panelBase.setLayout(new CardLayout(0, 0));

			// se añade el panel de la tienda al card layout
			panelBase.add(getPanelPrincipal(), "panelPrincipal");

			// sen añade el panel de aceptar pedido al card layout
			panelBase.add(getPanelAceparPedido(), "panelAceptarPedido");
			panelBase.add(getPanelAceptarPedidoMinorista(), "panelAceptarPedidoMinorista");
		}

		return panelBase;
	}

	private JPanel getPanelAceparPedido() {
		if (panelAceparPedido == null) {
			panelAceparPedido = new JPanel();

			panelAceparPedido.setLayout(new BorderLayout(0, 0));

			panelAceparPedido.add(getPanelBotonesAceptarPedido(), BorderLayout.SOUTH);
			panelAceparPedido.add(getPanelAceptarPedidoBase(), BorderLayout.CENTER);
		}

		return panelAceparPedido;
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

					if (usuarioReg == true) {
						getMntmIniciarSesin().setEnabled(false);
						getMntmCerrarSesin().setEnabled(true);
					} else {
						getMntmIniciarSesin().setEnabled(true);
						getMntmCerrarSesin().setEnabled(false);
					}
					resetearCamposDeDatos(); 
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
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					// validar que los campos sean correctos
					String textoDireccion = getTextFieldDireccionCliente().getText();

					// MetodosPago comboMetodo = (MetodosPago)
					// getComboBox().getSelectedItem();

					String textoNombre = getTextFieldNombreCliente().getText();

					if ((getComboBoxMetodoPago().getSelectedItem().equals(MetodosPago.TARJETA)
							&& (getTextFieldNumeroTarjeta().getText().equals("")
									|| getTextFieldNumeroTarjeta().getText().equals("")
									|| getPasswordFieldCodigoSeguridad().getText().equals("")))
							|| textoDireccion.equals("") || textoNombre.equals("")) {
						JOptionPane.showMessageDialog(getBtnFinalizarPedido(), "Rellene los campos indicados", "Error",
								JOptionPane.ERROR_MESSAGE);

					} else {
						if (usuarioReg) {// particular registrado

							try {
								logVOUser.generarTodoParticular(textoDireccion, textoNombre,
										(MetodosPago) getComboBoxMetodoPago().getSelectedItem(),
										(TipoEnvio) getComboBoxTipoEnvioClientes().getSelectedItem(),
										Long.parseLong(getTextFieldNumeroTarjeta().getText()),
										Integer.parseInt(getPasswordFieldCodigoSeguridad().getText()),
										(TipoTarjeta) getComboBoxTipoTarjeta().getSelectedItem(),
										getTextFieldFechaDeCaducidad().getText());
							} catch (NumberFormatException | BusinessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {// particular no registrado

							if (!getComboBoxMetodoPago().getSelectedItem().equals(MetodosPago.TARJETA)) {
								try {
									logVOUser.generarTodoSinTarjeta(textoDireccion, textoNombre,
											(MetodosPago) getComboBoxMetodoPago().getSelectedItem(),
											(TipoEnvio) getComboBoxTipoEnvioClientes().getSelectedItem());
								} catch (BusinessException e1) {
									System.err.println(e1.getMessage());
								}

							} else {// pago va a ser por tarjeta
								try {
									
									logVOUser.generarTodoConTarjeta(textoDireccion, textoNombre,
											(TipoEnvio) getComboBoxTipoEnvioClientes().getSelectedItem(),
											Long.parseLong(getTextFieldNumeroTarjeta().getText()),
											Integer.parseInt(getPasswordFieldCodigoSeguridad().getText()),
											(TipoTarjeta) getComboBoxTipoTarjeta().getSelectedItem(),
											getTextFieldFechaDeCaducidad().getText());
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (BusinessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						} // else 2

						StringBuilder sb = new StringBuilder();
						sb.append("Gracias por su compra, su pedido a sido confirmado.\n");

						if (getComboBoxMetodoPago().getSelectedItem().equals(MetodosPago.TRANSFERENCIA)) {
							sb.append("Realice la transferencia la cuenta: \n TiendaIPSLiberBank2016");
						}

						JOptionPane.showMessageDialog(null, sb.toString(), "Confirmación de pedido",
								JOptionPane.INFORMATION_MESSAGE);
						modeloListaCesta = logVOUser.resetear();

						resetearCamposDeDatos(); // Se eliminan todos los campos
													// de texto de la
													// aplicación
						getListCesta().setModel(modeloListaCesta);
						
						

						if (usuarioReg == false) {
							getMntmIniciarSesin().setEnabled(true);
							getMntmCerrarSesin().setEnabled(false);
						} else {
							getMntmIniciarSesin().setEnabled(false);
							getMntmCerrarSesin().setEnabled(true);
						}

						getTextGastoTotal().setText("0.0");
						((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
						btnAtrasCategorias.setEnabled(false);
						((CardLayout) panelListasCategoriasYProductos.getLayout()).show(panelListasCategoriasYProductos,
								"Panel Categorias");

						// rehago los modelos
						modeloListaProductos.removeAllElements();
						modeloListaCategorias = logVOUser.getModeloCategoriasPadre();
						getListCategorias().setModel(modeloListaCategorias);
					} // else 1
					Log.debug("la compra ha ido chachi piruli");
				}// action performed
			});
		}

		return btnFinalizarPedido;
	}

	
	
	
	private void resetearCamposDeDatos() {
		getTextFieldDireccionCliente().setText("");
		getTextFieldNombreCliente().setText("");
		getComboBoxMetodoPago().setSelectedIndex(0);
		getComboBoxTipoEnvioClientes().setSelectedIndex(0);
		getComboBoxTipoEnvioMinorista().setSelectedIndex(0);
		getComboBoxTipoTarjeta().setSelectedIndex(0);
		getTextFieldFechaDeCaducidad().setText("");
		getTextFieldNumeroTarjeta().setText("");
		getTextFieldUnidadesProducto().setText("1");
		getPasswordFieldCodigoSeguridad().setText("");
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

			panelAceptarPedidoBase.setLayout(new GridLayout(2, 0, 0, 0));
			panelAceptarPedidoBase.add(getPanelDatosBaseUsuario());
			panelAceptarPedidoBase.add(getPanelTarjetaUsuario());
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
			panelMetodoPagoCliente.add(getComboBoxMetodoPago());
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

	private JComboBox<MetodosPago> getComboBoxMetodoPago() {// Hay que modificar
															// la
		// implementación
		if (comboBoxMetodoPago == null) {
			MetodosPago metodosPagoNoRegistrado[] = { MetodosPago.TRANSFERENCIA, MetodosPago.TARJETA,
					MetodosPago.CONTRAREEMBOLSO };

			modeloComboMetodosDePago = new DefaultComboBoxModel<MetodosPago>(metodosPagoNoRegistrado);
			comboBoxMetodoPago = new JComboBox<MetodosPago>();

			comboBoxMetodoPago.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (getComboBoxMetodoPago().getSelectedItem().equals(MetodosPago.TARJETA)) {
						((CardLayout) panelTarjetaUsuario.getLayout()).show(getPanelTarjetaUsuario(),
								"panelDatosTarjeta");
					} else {
						((CardLayout) panelTarjetaUsuario.getLayout()).show(getPanelTarjetaUsuario(), "panelVacio");
					}
				}
			});

			comboBoxMetodoPago.setModel(modeloComboMetodosDePago);
		}

		return comboBoxMetodoPago;
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
			panelCategorias.add(getPanelBotonRetroceso(), BorderLayout.NORTH);

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

	public void cargarCategoriasPadre() {
		modeloListaCategorias = logVOUser.getModeloCategoriasPadre();
		listCategorias.setModel(modeloListaCategorias);
	}

	private JList<Categoria> getListCategorias() {
		if (listCategorias == null) {
			listCategorias = new JList<Categoria>();

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
								getBtnAtrasCategoria().setEnabled(true); // contiene
																			// productos
								cargarCategoriasHijas(getListCategorias().getSelectedValue());
							} else {// si la categoria contiene productos
									// cargamos la lista de productos de esa
									// categoria

								cargarModeloListaProductos(getListCategorias().getSelectedValue());
								((CardLayout) panelListasCategoriasYProductos.getLayout())
										.show(panelListasCategoriasYProductos, "Panel Productos");
								// reiniciamos la lista de categorias
								// modeloListaCategorias =
								// logVOUser.getModeloCategoriasPadre();//no
								// reinicio para facilitar la vuelta atrás
								// listCategorias.setModel(modeloListaCategorias);//
								// probar
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
	private void cargarCategoriasHijas(Categoria categoria) {
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
					JOptionPane.showMessageDialog(getMntmIniciarSesin(),
							"Sesión " + VentanaPrincipalUsuario.this.nombre + " cerrada.", "Sesión Cerrada",
							JOptionPane.INFORMATION_MESSAGE);

					logVOUser.cerrarSesion();
					usuarioReg = false;
					getMntmIniciarSesin().setEnabled(true);
					getMntmCerrarSesin().setEnabled(false);
				}
			});

			mntmCerrarSesin.setEnabled(false);
		}

		return mntmCerrarSesin;
	}

	private JPanel getPanelBotonRetroceso() {
		if (panelBotonRetroceso == null) {
			panelBotonRetroceso = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonRetroceso.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);

			panelBotonRetroceso.add(getBtnAtrasCategoria());
		}

		return panelBotonRetroceso;
	}

	private JPanel getPanelBotonRetrocesoProductos() {
		if (panelBotonRetrocesoProductos == null) {
			panelBotonRetrocesoProductos = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonRetrocesoProductos.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);

			panelBotonRetrocesoProductos.add(getBtnAtrasProductos());
		}

		return panelBotonRetrocesoProductos;
	}

	/*
	 * Boton para dar marcha atrás a los productos
	 */
	private JButton getBtnAtrasProductos() {
		if (btnAtrasProductos == null) {
			btnAtrasProductos = new JButton("\u2190");

			btnAtrasProductos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					((CardLayout) panelListasCategoriasYProductos.getLayout()).show(panelListasCategoriasYProductos,
							"Panel Categorias");// Lo que hago es volver al otro
												// jpanel del cardlayout
					modeloListaProductos.removeAllElements();// vacio el modelo
				}
			});
		}

		return btnAtrasProductos;
	}

	/*
	 * Boton para dar marcha atrás a las categorias
	 */
	private JButton getBtnAtrasCategoria() {// boton marcha atrás en categorias
		if (btnAtrasCategorias == null) {
			btnAtrasCategorias = new JButton("\u2190");
			btnAtrasCategorias.setEnabled(false);// por defecto esta false

			btnAtrasCategorias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// si padre es null, es categoria padre
					getListCategorias().setSelectedIndex(0);
					if (getListCategorias().getSelectedValue().getCategoriaPadre().getCategoriaPadre() == null) {// categoria
																													// padre
						getBtnAtrasCategoria().setEnabled(false);
						cargarCategoriasPadre();
					} else {
						cargarCategoriasHijas(
								getListCategorias().getSelectedValue().getCategoriaPadre().getCategoriaPadre());
						getBtnAtrasCategoria().setEnabled(true);
					}
				}
			});

		}

		return btnAtrasCategorias;
	}

	private JPanel getPanelDatosBaseUsuario() {
		if (panelDatosBaseUsuario == null) {
			panelDatosBaseUsuario = new JPanel();

			panelDatosBaseUsuario.setLayout(new GridLayout(4, 1, 0, 0));

			panelDatosBaseUsuario.add(getPanelAuxAceptarPedido1());
			panelDatosBaseUsuario.add(getPanelDireccionCliente());
			panelDatosBaseUsuario.add(getPanelMetodoPagoCliente());
			panelDatosBaseUsuario.add(getPanelTipoEnvio());
		}

		return panelDatosBaseUsuario;
	}

	private JPanel getPanelTarjetaUsuario() {
		if (panelTarjetaUsuario == null) {
			panelTarjetaUsuario = new JPanel();

			panelTarjetaUsuario.setLayout(new CardLayout(0, 0));

			panelTarjetaUsuario.add(getPanelVacio(), "panelVacio");
			panelTarjetaUsuario.add(getPanelDatosTrajeta(), "panelDatosTarjeta");
		}

		return panelTarjetaUsuario;
	}

	private JPanel getPanelVacio() {
		if (panelVacio == null) {
			panelVacio = new JPanel();
		}

		return panelVacio;
	}

	private JPanel getPanelDatosTrajeta() {
		if (panelDatosTrajeta == null) {
			panelDatosTrajeta = new JPanel();
			panelDatosTrajeta.setLayout(new GridLayout(4, 0, 0, 0));
			panelDatosTrajeta.add(getPanelNumeroTarjeta());
			panelDatosTrajeta.add(getPanelFechaCaducidad());
			panelDatosTrajeta.add(getPanelTipoTarjeta());
			panelDatosTrajeta.add(getPanelCodigoSeguridad());
		}

		return panelDatosTrajeta;
	}

	private JPanel getPanelNumeroTarjeta() {
		if (panelNumeroTarjeta == null) {
			panelNumeroTarjeta = new JPanel();
			panelNumeroTarjeta.add(getLblNumeroTarjeta());
			panelNumeroTarjeta.add(getTextFieldNumeroTarjeta());
		}

		return panelNumeroTarjeta;
	}

	private JPanel getPanelFechaCaducidad() {
		if (panelFechaCaducidad == null) {
			panelFechaCaducidad = new JPanel();
			panelFechaCaducidad.add(getLblFechaCaducidad());
			panelFechaCaducidad.add(getTextFieldFechaDeCaducidad());
		}

		return panelFechaCaducidad;
	}

	private JPanel getPanelTipoTarjeta() {
		if (panelTipoTarjeta == null) {
			panelTipoTarjeta = new JPanel();
			panelTipoTarjeta.add(getLblTipoTarjeta());
			panelTipoTarjeta.add(getComboBoxTipoTarjeta());
		}

		return panelTipoTarjeta;
	}

	private JPanel getPanelCodigoSeguridad() {
		if (panelCodigoSeguridad == null) {
			panelCodigoSeguridad = new JPanel();
			panelCodigoSeguridad.add(getLblCodigoSeguridad());
			panelCodigoSeguridad.add(getPasswordFieldCodigoSeguridad());
		}

		return panelCodigoSeguridad;
	}

	private JPanel getPanelTipoEnvio() {
		if (panelTipoEnvio == null) {
			panelTipoEnvio = new JPanel();
			panelTipoEnvio.add(getLblTipoEnvio());
			panelTipoEnvio.add(getComboBoxTipoEnvioClientes());
		}

		return panelTipoEnvio;
	}

	private JLabel getLblTipoEnvio() {
		if (lblTipoEnvio == null) {
			lblTipoEnvio = new JLabel("Tipo envio : ");
		}

		return lblTipoEnvio;
	}

	private JComboBox<TipoEnvio> getComboBoxTipoEnvioClientes() {
		if (comboBoxTipoEnvioClientes == null) {
			comboBoxTipoEnvioClientes = new JComboBox<TipoEnvio>();
			comboBoxTipoEnvioClientes.setModel(new DefaultComboBoxModel<TipoEnvio>(TipoEnvio.values()));
		}

		return comboBoxTipoEnvioClientes;
	}

	private JLabel getLblNumeroTarjeta() {
		if (lblNumeroTarjeta == null) {
			lblNumeroTarjeta = new JLabel("Numero tarjeta :");
		}

		return lblNumeroTarjeta;
	}

	private JTextField getTextFieldNumeroTarjeta() {
		if (textFieldNumeroTarjeta == null) {
			textFieldNumeroTarjeta = new JTextField();
			textFieldNumeroTarjeta.setColumns(10);
		}

		return textFieldNumeroTarjeta;
	}

	private JLabel getLblFechaCaducidad() {
		if (lblFechaCaducidad == null) {
			lblFechaCaducidad = new JLabel("Fecha caducidad :");
		}

		return lblFechaCaducidad;
	}

	private JTextField getTextFieldFechaDeCaducidad() {
		if (textFieldFechaCaducidad == null) {
			textFieldFechaCaducidad = new JTextField();

			textFieldFechaCaducidad.setText("");
			textFieldFechaCaducidad.setColumns(10);
		}

		return textFieldFechaCaducidad;
	}

	private JLabel getLblTipoTarjeta() {
		if (lblTipoTarjeta == null) {
			lblTipoTarjeta = new JLabel("Tipo tarjeta :");
		}

		return lblTipoTarjeta;
	}

	private JComboBox<TipoTarjeta> getComboBoxTipoTarjeta() {
		if (comboBoxTipoTarjeta == null) {
			comboBoxTipoTarjeta = new JComboBox<TipoTarjeta>();
			comboBoxTipoTarjeta.setModel(new DefaultComboBoxModel<TipoTarjeta>(TipoTarjeta.values()));
		}

		return comboBoxTipoTarjeta;
	}

	private JLabel getLblCodigoSeguridad() {
		if (lblCodigoSeguridad == null) {
			lblCodigoSeguridad = new JLabel("CodigoSeguridad");
		}

		return lblCodigoSeguridad;
	}

	private JPasswordField getPasswordFieldCodigoSeguridad() {
		if (passwordFieldCodigoSeguridad == null) {
			passwordFieldCodigoSeguridad = new JPasswordField();
			passwordFieldCodigoSeguridad.setColumns(16);
		}

		return passwordFieldCodigoSeguridad;
	}

	private JPanel getPanelAceptarPedidoMinorista() {
		if (panelAceptarPedidoMinorista == null) {
			panelAceptarPedidoMinorista = new JPanel();

			panelAceptarPedidoMinorista.setLayout(new BorderLayout(0, 0));

			panelAceptarPedidoMinorista.add(getPanelBotonesDatosMinorista(), BorderLayout.SOUTH);
			panelAceptarPedidoMinorista.add(getPanelDatosMinorista(), BorderLayout.CENTER);
		}

		return panelAceptarPedidoMinorista;
	}

	private JPanel getPanelBotonesDatosMinorista() {
		if (panelBotonesDatosMinorista == null) {
			panelBotonesDatosMinorista = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonesDatosMinorista.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);

			panelBotonesDatosMinorista.add(getButtonCancelarMinorista());
			panelBotonesDatosMinorista.add(getButtonAceptarPedidoMinorista());
		}

		return panelBotonesDatosMinorista;
	}

	private JButton getButtonAceptarPedidoMinorista() {
		if (buttonAceptarPedidoMinorista == null) {
			buttonAceptarPedidoMinorista = new JButton("Aceptar");

			buttonAceptarPedidoMinorista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// generar pedido de usuario minorista
					try {
						logVOUser
								.generarusuarioMinorista((TipoEnvio) getComboBoxTipoEnvioMinorista().getSelectedItem());

					} catch (BusinessException e1) {
						System.err.println(e1.getMessage());
					}

					JOptionPane.showMessageDialog(null,
							"Gracias por su compra. \n Se le pasará la factura a final de mes",
							"Confirmación de pedido", JOptionPane.INFORMATION_MESSAGE);
					modeloListaCesta = logVOUser.resetear();

					resetearCamposDeDatos(); // Se eliminan todos los campos
												// de texto de la
												// aplicación

					getListCesta().setModel(modeloListaCesta);
					getMntmCerrarSesin().setEnabled(true);

					getTextGastoTotal().setText("0.0");
					((CardLayout) panelBase.getLayout()).show(panelBase, "panelPrincipal");
					btnAtrasCategorias.setEnabled(false);
					((CardLayout) panelListasCategoriasYProductos.getLayout()).show(panelListasCategoriasYProductos,
							"Panel Categorias");

					// rehago los modelos
					modeloListaProductos.removeAllElements();
					modeloListaCategorias = logVOUser.getModeloCategoriasPadre();
					listCategorias.setModel(modeloListaCategorias);
				}
			});
		}

		return buttonAceptarPedidoMinorista;
	}

	private JButton getButtonCancelarMinorista() {
		if (buttonCancelarMinorista == null) {
			buttonCancelarMinorista = new JButton("Cancelar");

			buttonCancelarMinorista.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getMntmIniciarSesin().setEnabled(false);
					getMntmCerrarSesin().setEnabled(true);// siempre vas a estar
															// registrado siendo
															// minorista
					((CardLayout) panelTarjetaUsuario.getLayout()).show(getPanelPrincipal(), "panelPrincipal");
				}
			});
		}

		return buttonCancelarMinorista;
	}

	private JPanel getPanelDatosMinorista() {
		if (panelDatosMinorista == null) {
			panelDatosMinorista = new JPanel();

			panelDatosMinorista.setLayout(new GridLayout(3, 0, 0, 0));
			panelDatosMinorista.add(getPanelTipoEnvioMinorista());
		}

		return panelDatosMinorista;
	}

	private JPanel getPanelTipoEnvioMinorista() {
		if (panelTipoEnvioMinorista == null) {
			panelTipoEnvioMinorista = new JPanel();
			panelTipoEnvioMinorista.add(getLblTipoDeEnvioMinorista());
			panelTipoEnvioMinorista.add(getComboBoxTipoEnvioMinorista());
		}

		return panelTipoEnvioMinorista;
	}

	private JLabel getLblTipoDeEnvioMinorista() {
		if (lblTipoDeEnvioMinorista == null) {
			lblTipoDeEnvioMinorista = new JLabel("Tipo de envio :");
		}
		return lblTipoDeEnvioMinorista;
	}

	private JComboBox<TipoEnvio> getComboBoxTipoEnvioMinorista() {
		if (comboBoxTipoEnvioMinorista == null) {
			comboBoxTipoEnvioMinorista = new JComboBox<TipoEnvio>();
			comboBoxTipoEnvioMinorista.setModel(new DefaultComboBoxModel<TipoEnvio>(TipoEnvio.values()));
		}

		return comboBoxTipoEnvioMinorista;
	}

}